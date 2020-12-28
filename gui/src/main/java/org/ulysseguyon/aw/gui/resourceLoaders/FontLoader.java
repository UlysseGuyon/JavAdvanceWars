package org.ulysseguyon.aw.gui.resourceLoaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.util.ResourceLoader;
import org.ulysseguyon.aw.gui.utils.GuiConsts;


/**
 * Class used as loader and memory for font objects
 * 
 * @author Ulysse Guyon
 */
public final class FontLoader extends ResEnumLoader< GuiConsts.Font, Font > {

	/**
	 * Internal class used for singleton pattern
	 */
	private static final class FontLoaderHolder {
		private static final FontLoader INSTANCE = new FontLoader();
	}

	/**
	 * Method used to access the only instance of this class
	 * 
	 * @return The singleton instance of this class
	 */
	public static FontLoader getInstance () { return FontLoaderHolder.INSTANCE; }

	/**
	 * Constructor for font loader
	 */
	private FontLoader () { super(); }

	@Override
	protected Font loadResource ( GuiConsts.Font name ) {
		Font f = null;

		try {
			f = Font.createFont( Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream( name.getPath() ) );
		} catch ( FontFormatException | IOException e ) {
			e.printStackTrace();
		}

		return f;
	}

	/**
	 * Method used for accessing a specific font customized with a given font size
	 * 
	 * @param  fontName The key/name of the font we want to access
	 * @param  size     The size at which we want the font to be
	 * @return          The font customized object
	 */
	public Font get ( GuiConsts.Font fontName, float size ) {
		return get( fontName ).deriveFont( Font.PLAIN, size );
	}

	@Override
	public void unloadResource ( GuiConsts.Font name ) { return; }

}
