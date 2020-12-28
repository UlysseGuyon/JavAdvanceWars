package org.ulysseguyon.aw.gui.resourceLoaders;

import org.newdawn.slick.SlickException;
import org.ulysseguyon.aw.gui.redef.Image;
import org.ulysseguyon.aw.gui.utils.GuiConsts.SimpleImage;


/**
 * Class used as loader and memory for image objects
 * 
 * @author Ulysse Guyon
 */
public final class ImageLoader extends ResEnumLoader< SimpleImage, Image > {

	/**
	 * Internal class used for singleton pattern
	 */
	private static final class ImageLoaderHolder {
		private static final ImageLoader INSTANCE = new ImageLoader();
	}

	/**
	 * Method used to access the only instance of this class
	 * 
	 * @return The singleton instance of this class
	 */
	public static ImageLoader getInstance () { return ImageLoaderHolder.INSTANCE; }

	/**
	 * Constructor for image loader
	 */
	private ImageLoader () { super(); }

	@Override
	protected Image loadResource ( SimpleImage name ) {
		Image i = null;

		try {
			i = new Image( name.getPath() );
		} catch ( SlickException e ) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public void unloadResource ( SimpleImage name ) {
		try {
			get( name ).destroy();
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

}
