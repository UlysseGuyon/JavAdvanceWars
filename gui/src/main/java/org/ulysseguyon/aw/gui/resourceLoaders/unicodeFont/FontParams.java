package org.ulysseguyon.aw.gui.resourceLoaders.unicodeFont;

import java.awt.Color;

import org.ulysseguyon.aw.gui.utils.GuiConsts;


public class FontParams {

	private GuiConsts.Font	fontName;
	private int				height;
	private Color			color;

	public FontParams ( GuiConsts.Font fontName, int height, Color color ) {
		this.fontName = fontName;
		this.height = height;
		this.color = color;
	}

	public GuiConsts.Font getFontName () { return fontName; }

	public int getHeight () { return height; }

	public Color getColor () { return color; }

	@Override
	public boolean equals ( Object obj ) {
		if ( obj instanceof FontParams ) {
			FontParams fp = ( FontParams ) obj;
			return fp.color.equals( color ) && fp.fontName == fontName && fp.height == height;
		} else return false;
	}

	// TODO maybe find a better hash code, just in case
	@Override
	public int hashCode () { return this.fontName.hashCode() + this.height + this.color.hashCode(); }

}
