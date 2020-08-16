package org.ulysseguyon.aw.gui.resourceLoaders;

import java.util.HashMap;
import java.util.Map;


public abstract class ResLoader< E extends Enum< ? >, R > {

	private Map< E, R > resourceMap;

	protected ResLoader () { resourceMap = new HashMap< E, R >(); }

	protected abstract R loadResource ( E name );

	public R load ( E name ) {
		R res = resourceMap.get( name );

		if ( res == null ) res = loadResource( name );

		return res;
	}

	public R get ( E name ) { return load( name ); }

}
