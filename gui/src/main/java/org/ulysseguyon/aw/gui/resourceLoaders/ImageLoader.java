package org.ulysseguyon.aw.gui.resourceLoaders;

import org.newdawn.slick.SlickException;
import org.ulysseguyon.aw.gui.redef.Image;
import org.ulysseguyon.aw.gui.utils.GuiConsts;


public final class ImageLoader extends ResLoader< GuiConsts.SimpleImage, Image > {

	private static final class ImageLoaderHolder {
		private static final ImageLoader INSTANCE = new ImageLoader();
	}

	public static ImageLoader getInstance () { return ImageLoaderHolder.INSTANCE; }

	private ImageLoader () { super(); }

	@Override
	protected Image loadResource ( GuiConsts.SimpleImage name ) {
		Image i = null;

		try {
			i = new Image( name.getPath() );
		} catch ( SlickException e ) {
			e.printStackTrace();
		}

		return i;
	}

}
