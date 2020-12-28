package org.ulysseguyon.aw.gui.resourceLoaders.unicodeFont;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.ulysseguyon.aw.gui.resourceLoaders.FontLoader;
import org.ulysseguyon.aw.gui.resourceLoaders.ResLoader;
import org.ulysseguyon.aw.gui.utils.GraphicString;


/**
 * Class used as loader and memory for UnicodeFont objects
 * 
 * @author Ulysse Guyon
 */
public class UnicodeFontLoader extends ResLoader< FontParams, UnicodeFont > {

	/**
	 * Internal class used for singleton pattern
	 */
	private static final class UnicodeFontLoaderHolder {
		private static final UnicodeFontLoader INSTANCE = new UnicodeFontLoader();
	}

	/**
	 * Method used to access the only instance of this class
	 * 
	 * @return The singleton instance of this class
	 */
	public static UnicodeFontLoader getInstance () { return UnicodeFontLoaderHolder.INSTANCE; }

	/**
	 * Constructor for unicode font loader
	 */
	private UnicodeFontLoader () { super(); }

	@Override
	@SuppressWarnings( "unchecked" )
	protected UnicodeFont loadResource ( FontParams key ) {
		Font awtFont = FontLoader.getInstance()
			.get( key.getFontName(), GraphicString.getSizeFromHeight( key.getHeight() ) );
		UnicodeFont font = new UnicodeFont( awtFont );
		font.getEffects().add( new ColorEffect( key.getColor() ) );
		font.addAsciiGlyphs();
		try {
			// This part can take some non negligible time
			font.loadGlyphs();
		} catch ( SlickException e ) {
			e.printStackTrace();
		}

		return font;
	}

	@Override
	protected void unloadResource ( FontParams key ) { get( key ).destroy(); }

}
