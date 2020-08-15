import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public final class NativeLoader {

	private enum SupportedOS {
		WIN ( new String[] {
				"win" } ),
		MAC ( new String[] {
				"mac" } ),
		UNIX ( new String[] {
				"nix", "nux", "aix" } );

		private String[] osNames;

		SupportedOS ( String[] names ) {
			osNames = names;
		}

		private boolean isOS ( String name ) {
			boolean isOs = false;
			for ( String osName : osNames )
				isOs |= name.indexOf( osName ) >= 0;
			return isOs;
		}

		public static boolean isSupported ( String name ) {
			boolean supported = false;
			for ( SupportedOS os : SupportedOS.values() )
				supported |= os.isOS( name );
			return supported;
		}
	}

	private enum Architecture {
		_32 ( "x32", "x32" ), _64 ( "x64", "x64" ), NOT_NEEDED ( "nn", "x32", "x64", "nn" ), UNKNOWN ( "unknown" );

		private String id;
		private String[] compatibles;

		Architecture ( String id, String... compatibles ) {
			this.id = id;
			this.compatibles = compatibles;
		}

		public boolean usableOn ( Architecture currentArch ) {
			boolean isCompatible = false;
			for ( String idComp : compatibles )
				isCompatible |= currentArch.id == idComp;
			return isCompatible;
		}

		public static Architecture getCurrentArch () {
			if ( SupportedOS.MAC.isOS( System.getProperty( "os.name" ) ) )
				return Architecture.NOT_NEEDED;

			final String ARCH = System.getProperty( "os.arch" );

			if ( ARCH.indexOf( "64" ) >= 0 )
				return Architecture._64;

			if ( ARCH.indexOf( "86" ) >= 0 || ARCH.indexOf( "86" ) >= 0 )
				return Architecture._32;

			return Architecture.UNKNOWN;
		}
	}

	private enum NativeFile {
		JINPUT_DX8_DLL ( "jinput-dx8.dll", SupportedOS.WIN, Architecture._32 ),
		JINPUT_DX8_64_DLL ( "jinput-dx8_64.dll", SupportedOS.WIN, Architecture._64 ),
		JINPUT_RAW_DLL ( "jinput-raw.dll", SupportedOS.WIN, Architecture._32 ),
		JINPUT_RAW_64_DLL ( "jinput-raw_64.dll", SupportedOS.WIN, Architecture._64 ),
		JINPUT_WINTAB_DLL ( "jinput-wintab.dll", SupportedOS.WIN, Architecture._32 ),
		LIBJINPUT_LINUX_SO ( "libjinput-linux.so", SupportedOS.UNIX, Architecture._32 ),
		LIBJINPUT_LINUX_64_SO ( "libjinput-linux64.so", SupportedOS.UNIX, Architecture._64 ),
		LIBJINPUT_OSX_JNILIB ( "libjinput-osx.jnilib", SupportedOS.MAC, Architecture.NOT_NEEDED ),
		LIBLWJGL_DYLIB ( "liblwjgl.dylib", SupportedOS.MAC, Architecture.NOT_NEEDED ),
		LIBLWJGL_SO ( "liblwjgl.so", SupportedOS.UNIX, Architecture._32 ),
		LIBLWJGL_64_SO ( "liblwjgl64.so", SupportedOS.UNIX, Architecture._64 ),
		LIBOPENAL_SO ( "libopenal.so", SupportedOS.UNIX, Architecture._32 ),
		LIBOPENAL_64_SO ( "libopenal64.so", SupportedOS.UNIX, Architecture._64 ),
		LWJGL_DLL ( "lwjgl.dll", SupportedOS.WIN, Architecture._32 ),
		LWJGL_64_DLL ( "lwjgl64.dll", SupportedOS.WIN, Architecture._64 ), // ERROR not loadable on WIN32
		OPENAL_DYLIB ( "openal.dylib", SupportedOS.MAC, Architecture.NOT_NEEDED ),
		OPENAL_32_DLL ( "OpenAL32.dll", SupportedOS.WIN, Architecture._32 ),
		OPENAL_64_DLL ( "OpenAL64.dll", SupportedOS.WIN, Architecture._64 ); // ERROR file not accessible

		public SupportedOS osLib;
		public Architecture archLib;
		public String fileName;

		NativeFile ( String name, SupportedOS os, Architecture arch ) {
			osLib = os;
			archLib = arch;
			fileName = name;
		}

		public static List< NativeFile > getLibsFromOSAndArch () {
			List< NativeFile > usedNatives = new ArrayList< NativeFile >();

			final String OS = System.getProperty( "os.name" ).toLowerCase();
			final Architecture ARCH = Architecture.getCurrentArch();

			for ( NativeFile file : NativeFile.values() ) {
				if ( file.osLib.isOS( OS ) && file.archLib.usableOn( ARCH ) )
					usedNatives.add( file );
			}

			return usedNatives;
		}
	}

	public static void loadNatives () throws UnsatisfiedLinkError, IOException {
		final String OS = System.getProperty( "os.name" );
		final String ARCH = System.getProperty( "os.arch" );
		if ( !SupportedOS.isSupported( OS.toLowerCase() ) )
			throw new UnsatisfiedLinkError( "OS '" + OS + "' is not supported" );

		System.out.println( "[INFO] Loading libraries for " + OS + " " + ARCH + " ..." );
		List< NativeFile > libs = NativeFile.getLibsFromOSAndArch();

		File tmpFolder = new File( System.getProperty( "java.io.tmpdir" ) + File.separator + "AWLibTemp" );
		tmpFolder.mkdir();
		if ( !tmpFolder.exists() || !tmpFolder.isDirectory() || !tmpFolder.canWrite() )
			throw new IOException( "Temp folder for libraries is not created correctly" );
		for ( File tmpFile : tmpFolder.listFiles() )
			tmpFile.delete();

		for ( NativeFile lib : libs ) {
			try {
				System.out.println( "[INFO] Loading library '" + lib.fileName + "' ..." );
				System.loadLibrary( lib.fileName );
				System.out.println( "[INFO] Library loaded" );
			} catch ( UnsatisfiedLinkError e ) {
				InputStream in = NativeLoader.class.getResourceAsStream( lib.fileName );
				File fileOut = new File( tmpFolder.getAbsolutePath() + File.separator + lib.fileName );
				OutputStream out = new FileOutputStream( fileOut );

				System.out.println( "[INFO] Writing library to : " + fileOut.getAbsolutePath() );

				byte[] buffer = new byte[1024];
				while ( in.read( buffer ) != -1 )
					out.write( buffer );

				in.close();
				out.close();

				System.out.println( "[INFO] Library written ... Loading library ..." );
				System.load( fileOut.getPath() );
				System.out.println( "[INFO] Library loaded" );
			}
		}
		System.out.println( "[INFO] All libraries loaded in memory !" );
	}

}