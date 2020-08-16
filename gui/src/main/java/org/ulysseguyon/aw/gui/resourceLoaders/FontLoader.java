package org.ulysseguyon.aw.gui.resourceLoaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import org.newdawn.slick.util.ResourceLoader;
import org.ulysseguyon.aw.gui.utils.GuiConsts;


public final class FontLoader extends ResLoader< GuiConsts.Font, Font > {

	private static final class FontLoaderHolder {
		private static final FontLoader INSTANCE = new FontLoader();
	}

	public static FontLoader getInstance () { return FontLoaderHolder.INSTANCE; }

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

	public Font get ( GuiConsts.Font fontName, float size ) { return get( fontName ).deriveFont( Font.PLAIN, size ); }

}
