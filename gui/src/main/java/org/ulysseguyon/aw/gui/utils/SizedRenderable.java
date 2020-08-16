package org.ulysseguyon.aw.gui.utils;

import org.newdawn.slick.Renderable;


public interface SizedRenderable extends Renderable {

	int getWidth ();

	int getHeight ();

	default int getCenterX () { return getWidth() / 2; }

	default int getCenterY () { return getHeight() / 2; }

}
