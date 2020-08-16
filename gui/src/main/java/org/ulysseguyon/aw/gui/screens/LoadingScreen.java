package org.ulysseguyon.aw.gui.screens;

import org.newdawn.slick.Color;
import org.ulysseguyon.aw.gui.resourceLoaders.ImageLoader;
import org.ulysseguyon.aw.gui.utils.GraphicString;
import org.ulysseguyon.aw.gui.utils.GuiConsts;
import org.ulysseguyon.aw.gui.utils.SizedRenderable;
import org.ulysseguyon.aw.gui.utils.SmoothAnimation;


public class LoadingScreen implements SizedRenderable {

	public static enum ObjectPos {
		UP ( 1.0f / 4.0f ),
		CENTER ( 1.0f / 2.0f ),
		DOWN ( 3.0f / 4.0f );

		private float screenRatio;

		ObjectPos ( float ratio ) { screenRatio = ratio; }

		public float getScreenRatio () { return screenRatio; }
	}

	SizedRenderable	background;
	SizedRenderable	centerObject, upObject, downObject;

	boolean			adaptSizes;

	public LoadingScreen () {
		background = new SmoothAnimation(
			ImageLoader.getInstance().get( GuiConsts.SimpleImage.BACK_MAIN_MENU ), -1, -1, 1
		);
		upObject = ImageLoader.getInstance().get( GuiConsts.SimpleImage.LOGO_GAME_BIG_ICON );
		centerObject = new GraphicString( GuiConsts.Strings.LOADING, GuiConsts.Font.TITLE, 40 );
		downObject = null;
		adaptSizes = true;
	}

	public LoadingScreen ( boolean adaptive ) {
		background = new SmoothAnimation(
			ImageLoader.getInstance().get( GuiConsts.SimpleImage.BACK_MAIN_MENU ), -1, -1, 1
		);
		upObject = ImageLoader.getInstance().get( GuiConsts.SimpleImage.LOGO_GAME_BIG_ICON );
		centerObject = new GraphicString( GuiConsts.Strings.LOADING, GuiConsts.Font.TITLE, 40 );
		downObject = null;
		adaptSizes = adaptive;
	}

	public LoadingScreen ( SizedRenderable back ) {
		background = back;
		upObject = null;
		centerObject = null;
		downObject = null;
		adaptSizes = true;
	}

	public LoadingScreen ( SizedRenderable back, boolean adaptive ) {
		background = back;
		upObject = null;
		centerObject = null;
		downObject = null;
		adaptSizes = adaptive;
	}

	public LoadingScreen ( SizedRenderable back, SizedRenderable center ) {
		background = back;
		upObject = null;
		centerObject = center;
		downObject = null;
		adaptSizes = true;
	}

	public LoadingScreen ( SizedRenderable back, SizedRenderable center, boolean adaptive ) {
		background = back;
		upObject = null;
		centerObject = center;
		downObject = null;
		adaptSizes = adaptive;
	}

	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = null;
		adaptSizes = true;
	}

	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up, boolean adaptive ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = null;
		adaptSizes = adaptive;
	}

	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up, SizedRenderable down ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = down;
		adaptSizes = true;
	}

	public LoadingScreen (
		SizedRenderable back, SizedRenderable center, SizedRenderable up, SizedRenderable down, boolean adaptive
	) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = down;
		adaptSizes = adaptive;
	}

	protected SizedRenderable fromPos ( ObjectPos pos ) {
		SizedRenderable obj = null;

		switch ( pos ) {
		case CENTER:
			obj = centerObject;
			break;
		case DOWN:
			obj = downObject;
			break;
		case UP:
			obj = upObject;
			break;
		default:
			System.err.println( "[ERROR] Unknown object position : " + pos );
			break;
		}

		return obj;
	}

	public void setAdaptive ( boolean adaptive ) { adaptSizes = adaptive; }

	public void setObject ( ObjectPos pos, SizedRenderable obj ) {
		switch ( pos ) {
		case CENTER:
			centerObject = obj;
			break;
		case DOWN:
			downObject = obj;
			break;
		case UP:
			upObject = obj;
			break;
		default:
			System.err.println( "[ERROR] Unknown object position : " + pos );
			break;
		}
	}

	public void drawObject ( ObjectPos pos, float width, float height, Color filter ) {
		SizedRenderable obj = fromPos( pos );

		if ( obj != null ) {
			final float centerX = width / 2;
			final float centerY = height * pos.getScreenRatio();

			if ( adaptSizes ) {
				final int newW = ( int ) ( width * 1 / 4.0 );
				final int newH = ( int ) ( height * newW / width );

				if ( filter != null ) obj.draw( centerX - newW / 2, centerY - newH / 2, newW, newH, filter );
				else obj.draw( centerX - newW / 2, centerY - newH / 2, newW, newH );
			} else {
				if ( filter != null ) obj.draw( centerX - obj.getCenterX(), centerY - obj.getCenterY(), filter );
				else obj.draw( centerX - obj.getCenterX(), centerY - obj.getCenterY() );
			}
		}
	}

	public void drawObjects ( float width, float height, Color filter ) {
		for ( ObjectPos pos : ObjectPos.values() ) drawObject( pos, width, height, filter );
	}

	@Override
	public void draw ( float x, float y ) {
		if ( background != null ) background.draw( x, y );
		drawObjects( background.getWidth(), background.getHeight(), null );
	}

	@Override
	public void draw ( float x, float y, Color filter ) {
		if ( background != null ) background.draw( x, y, filter );
		drawObjects( background.getWidth(), background.getHeight(), filter );
	}

	@Override
	public void draw ( float x, float y, float width, float height ) {
		if ( background != null ) background.draw( x, y, width, height );
		drawObjects( width, height, null );
	}

	@Override
	public void draw ( float x, float y, float width, float height, Color filter ) {
		if ( background != null ) background.draw( x, y, width, height, filter );
		drawObjects( width, height, filter );
	}

	@Override
	public int getWidth () { return background.getWidth(); }

	@Override
	public int getHeight () { return background.getHeight(); }

}
