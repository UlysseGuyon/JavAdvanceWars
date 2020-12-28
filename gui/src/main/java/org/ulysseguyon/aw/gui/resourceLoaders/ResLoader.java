package org.ulysseguyon.aw.gui.resourceLoaders;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class used as template for loading resources in hash-maps
 * 
 * @author     Ulysse Guyon
 * @param  <E> Enumeration type used as key in the record
 * @param  <R> Resource type that needs loading
 */
public abstract class ResLoader< E, R > {

	/**
	 * The hash map used for keeping resources loaded
	 */
	private Map< E, R > resourceMap;

	/**
	 * Constructor initializing the resource hash map
	 */
	protected ResLoader () { resourceMap = new HashMap< E, R >(); }

	/**
	 * Abstract method used for loading a resource in memory
	 * 
	 * @param  name The name used as key to identify the resource
	 * @return      The resource object that has been loaded
	 */
	protected abstract R loadResource ( E name );

	/**
	 * Abstract method used for unloading a resource from memory
	 * 
	 * @param name The name used as key to identify the resource
	 */
	protected abstract void unloadResource ( E name );

	/**
	 * Method used to trigger the loading of a resource and add it in the hash map
	 * if it is not already in it.
	 * 
	 * @param  name The name used as key to identify the resource
	 * @return      The newly loaded resource
	 */
	public R load ( E name ) {
		R res = resourceMap.get( name );

		if ( res == null ) res = loadResource( name );

		if ( res != null ) resourceMap.put( name, res );

		return res;
	}

	/**
	 * Method used for removing a resource from the hash map and from memory if
	 * needed
	 * 
	 * @param name The name used as key to identify the resource
	 */
	public void unload ( E name ) {
		unloadResource( name );
		resourceMap.remove( name );
	}

	/**
	 * Method used to get a specific resource by key.
	 * <br>
	 * Identical to <code>load()<code>
	 * 
	 * @see         #load
	 * @param  name The name used as key to identify the resource
	 * @return      The
	 */
	public R get ( E name ) { return load( name ); }

}
