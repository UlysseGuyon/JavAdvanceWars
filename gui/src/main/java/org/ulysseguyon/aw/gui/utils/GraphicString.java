package org.ulysseguyon.aw.gui.utils;

import java.awt.Color;
import java.awt.Toolkit;

import org.newdawn.slick.UnicodeFont;
import org.ulysseguyon.aw.gui.resourceLoaders.unicodeFont.FontParams;
import org.ulysseguyon.aw.gui.resourceLoaders.unicodeFont.UnicodeFontLoader;


/**
 * Class used for controlling strings drawn on screen
 * 
 * @author Ulysse Guyon
 */
public class GraphicString implements SizedRenderable {

	/**
	 * Number of pixels per inch
	 */
	private static final double RESOLUTION_HEIGHT_RATIO = 72.0;

	/**
	 * Static method used for getting the inches height according to a given height
	 * in pixels
	 * 
	 * @param  height The height in pixels
	 * @return        The height in inches
	 */
	public static int getSizeFromHeight ( int height ) {
		return height * ( int ) Math
			.ceil( Toolkit.getDefaultToolkit().getScreenResolution() / GraphicString.RESOLUTION_HEIGHT_RATIO );
	}

	/**
	 * The unicode font object used to render the string
	 */
	protected UnicodeFont		font;
	/**
	 * The key/name of the used font
	 */
	protected GuiConsts.Font	fontName;
	/**
	 * The color of the string to display
	 */
	protected Color				color;
	/**
	 * The string to display
	 */
	protected String			text;
	/**
	 * The height (in pixels) of the text we want
	 */
	protected int				askedHeight;

	/**
	 * Constructor building the string from the font
	 * 
	 * @param text     The string to show on screen
	 * @param fontName The key/name of the font to use on the string
	 * @param height   The height (in pixels) of the string
	 */
	public GraphicString ( String text, GuiConsts.Font fontName, int height ) {
		this.text = text;
		this.color = Color.white;
		askedHeight = height;

		loadFont( fontName, height, Color.white );
	}

	/**
	 * Constructor building the string from the font
	 * 
	 * @param text     The string to show on screen
	 * @param fontName The key/name of the font to use on the string
	 * @param height   The height (in pixels) of the string
	 * @param color    The color of the string to display
	 */
	public GraphicString ( String text, GuiConsts.Font fontName, int height, Color color ) {
		this.text = text;
		this.color = color;
		askedHeight = height;

		loadFont( fontName, height, color );
	}

	/**
	 * Method used for loading the unicode font
	 * 
	 * @param fontName The key/name of the font to load
	 * @param height   The height of the font
	 * @param color    The color to use as effect on the font
	 */
	private void loadFont ( GuiConsts.Font fontName, int height, Color color ) {
		font = UnicodeFontLoader.getInstance().get( new FontParams( fontName, height, color ) );
	}

	/**
	 * Getter for the string to display
	 * 
	 * @return The string to display
	 */
	public String getText () { return text; }

	/**
	 * Getter for the color of the string
	 * 
	 * @return The color (awt) of the string
	 */
	public Color getColor () { return color; }

	/**
	 * Setter for the font. This also reloads the unicode font
	 * 
	 * @param newFontName The key/name of the font we want to use
	 */
	public void setFont ( GuiConsts.Font newFontName ) {
		if ( fontName == newFontName ) return;

		loadFont( newFontName, askedHeight, color );
	}

	/**
	 * Setter for the string to display
	 * 
	 * @param newText The new string to display
	 */
	public void setText ( String newText ) { text = newText; }

	/**
	 * Setter for the color of the displayed string. This reloads the unicode font
	 * 
	 * @param newColor The new color to apply to the string
	 */
	public void setColor ( Color newColor ) {
		if ( color.equals( newColor ) ) return;

		color = newColor;

		loadFont( fontName, askedHeight, newColor );
	}

	/**
	 * Setter for the height of the string. This reloads the unicode font
	 * 
	 * @param newHeight The new height of the string
	 */
	public void setHeight ( int newHeight ) {
		if ( askedHeight == newHeight ) return;

		askedHeight = newHeight;

		loadFont( fontName, newHeight, color );
	}

	@Override
	public void draw ( float x, float y ) { font.drawString( x, y, text ); }

	@Override
	public void draw ( float x, float y, org.newdawn.slick.Color filter ) {
		setColor( new Color( filter.r, filter.g, filter.b ) );
		draw( x, y );
	}

	@Override
	public void draw ( float x, float y, float width, float height ) { draw( x, y ); }

	@Override
	public void draw ( float x, float y, float width, float height, org.newdawn.slick.Color filter ) {
		setColor( new Color( filter.r, filter.g, filter.b ) );
		draw( x, y );
	}

	@Override
	public int getWidth () { return font.getWidth( text ); }

	@Override
	public int getHeight () { return font.getHeight( text ); }

}
