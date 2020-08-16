package org.ulysseguyon.aw.gui.redef;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;
import org.ulysseguyon.aw.gui.utils.SizedRenderable;


public class Image extends org.newdawn.slick.Image implements SizedRenderable {
	public Image () { super(); }

	protected Image ( Image other ) { super( other ); }

	public Image ( ImageData data ) { super( data ); }

	public Image ( ImageData data, int f ) { super( data, f ); }

	public Image ( java.io.InputStream in, java.lang.String ref, boolean flipped ) throws SlickException {
		super( in, ref, flipped );
	}

	public Image ( java.io.InputStream in, java.lang.String ref, boolean flipped, int filter ) throws SlickException {
		super( in, ref, flipped, filter );
	}

	public Image ( int width, int height ) throws SlickException { super( width, height ); }

	public Image ( int width, int height, int f ) throws SlickException { super( width, height, f ); }

	public Image ( java.lang.String ref ) throws SlickException { super( ref ); }

	public Image ( java.lang.String ref, boolean flipped ) throws SlickException { super( ref, flipped ); }

	public Image ( java.lang.String ref, boolean flipped, int filter ) throws SlickException {
		super( ref, flipped, filter );
	}

	public Image ( java.lang.String ref, boolean flipped, int f, Color transparent ) throws SlickException {
		super( ref, flipped, f, transparent );
	}

	public Image ( java.lang.String ref, Color trans ) throws SlickException { super( ref, trans ); }

	public Image ( Texture texture ) { super( texture ); }

}
