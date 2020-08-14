VERSION_SUFFIX="SNAPSHOT"
VERSION_TAG_PREFIX="V"

VERSION_XML_TAG_OPEN="<version>"
VERSION_XML_TAG_CLOSE="</version>"
VERSION_XML_TAG_CLOSE_ESC="<\/version>"

VERSION_REGEX_NUM="[1-9]\.[0-9]+"
VERSION_REGEX_NUM_ESC="[1-9]\.[0-9]\+"
VERSION_REGEX="$VERSION_REGEX_NUM(-$VERSION_SUFFIX)?"
VERSION_REGEX_ESC="$VERSION_REGEX_NUM_ESC(-$VERSION_SUFFIX)?"

POM_FILE_NAME="pom.xml"

file_path_list=($(find . -type f -name "$POM_FILE_NAME" ! -path "*/target/*"))

find_current_version () {
  local parent_pom_path=./$POM_FILE_NAME
  local ver_tag=$(cat $parent_pom_path | grep -m 1 -P "$VERSION_XML_TAG_OPEN[ ]*$VERSION_REGEX[ ]*$VERSION_XML_TAG_CLOSE")
  local sed_regex="s/$VERSION_XML_TAG_OPEN\($VERSION_REGEX_NUM_ESC.*\)$VERSION_XML_TAG_CLOSE_ESC/\1/p"
  local ver=$(echo $ver_tag | sed -n -e "$sed_regex")
  echo $ver
}

current_version=$(find_current_version)
new_version=""

if [[ $current_version == *$VERSION_SUFFIX ]]
then
  # current is snapshot -> transform in release
  new_version=$(echo $current_version | cut -d'-' -f1)
else
  # current is release -> transform in next version snapshot
  new_version="${current_version:0:2}$(expr ${current_version:2} + 1)-$VERSION_SUFFIX"
fi

for file_name in ${file_path_list[@]}
do
  sed -i "s/$VERSION_XML_TAG_OPEN$current_version$VERSION_XML_TAG_CLOSE_ESC/$VERSION_XML_TAG_OPEN$new_version$VERSION_XML_TAG_CLOSE_ESC/g" $file_name
done

echo $VERSION_TAG_PREFIX$new_version
