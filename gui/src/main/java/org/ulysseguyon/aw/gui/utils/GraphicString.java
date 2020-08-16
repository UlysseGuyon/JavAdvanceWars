package org.ulysseguyon.aw.gui.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.ulysseguyon.aw.gui.resourceLoaders.FontLoader;


public class GraphicString implements SizedRenderable {

	private static final double RESOLUTION_HEIGHT_RATIO = 72.0;

	public static int getSizeFromHeight ( int height ) {
		return height * ( int ) Math
			.ceil( Toolkit.getDefaultToolkit().getScreenResolution() / GraphicString.RESOLUTION_HEIGHT_RATIO );
	}

	protected UnicodeFont		font;
	protected GuiConsts.Font	fontName;
	protected Color				color;
	protected String			text;
	protected int				askedHeight;

	public GraphicString ( String text, GuiConsts.Font fontName, int height ) {
		this.text = text;
		this.color = Color.white;
		askedHeight = height;

		loadFont( fontName, height, Color.white );
	}

	public GraphicString ( String text, GuiConsts.Font fontName, int height, Color color ) {
		this.text = text;
		this.color = color;
		askedHeight = height;

		loadFont( fontName, height, color );
	}

	@SuppressWarnings( "unchecked" )
	private void loadFont ( GuiConsts.Font fontName, int height, Color color ) {
		this.fontName = fontName;
		Font awtFont = FontLoader.getInstance().get( fontName, GraphicString.getSizeFromHeight( height ) );
		font = new UnicodeFont( awtFont );
		font.getEffects().add( new ColorEffect( color ) );
		font.addAsciiGlyphs();
		try {
			font.loadGlyphs();
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

	public String getText () { return text; }

	public Color getColor () { return color; }

	public void setFont ( GuiConsts.Font newFontName ) {
		if ( fontName == newFontName ) return;

		loadFont( newFontName, askedHeight, color );
	}

	public void setText ( String newText ) { text = newText; }

	public void setColor ( Color newColor ) {
		color = newColor;

		loadFont( fontName, askedHeight, newColor );
	}

	public void setHeight ( int newHeight ) {
		askedHeight = newHeight;

		loadFont( fontName, newHeight, color );
	}

	@Override
	public void draw ( float x, float y ) { font.drawString( x, y, text ); }

	@Override
	public void draw ( float x, float y, org.newdawn.slick.Color filter ) { draw( x, y ); }

	@Override
	public void draw ( float x, float y, float width, float height ) { draw( x, y ); }

	@Override
	public void draw ( float x, float y, float width, float height, org.newdawn.slick.Color filter ) { draw( x, y ); }

	@Override
	public int getWidth () { return font.getWidth( text ); }

	@Override
	public int getHeight () { return font.getHeight( text ); }

}
