package org.ulysseguyon.aw.gui.screens;

import org.newdawn.slick.Color;
import org.ulysseguyon.aw.gui.resourceLoaders.ImageLoader;
import org.ulysseguyon.aw.gui.utils.GraphicString;
import org.ulysseguyon.aw.gui.utils.GuiConsts;
import org.ulysseguyon.aw.gui.utils.SizedRenderable;
import org.ulysseguyon.aw.gui.utils.SmoothAnimation;


/**
 * Class representing the organization of a loading screen
 * 
 * @author Ulysse Guyon
 */
public class LoadingScreen implements SizedRenderable {

	/**
	 * Enumeration of possible positions of images on the screen
	 */
	public static enum ObjectPos {
		UP ( 1.0f / 4.0f ),
		CENTER ( 1.0f / 2.0f ),
		DOWN ( 3.0f / 4.0f );

		private float screenRatio;

		ObjectPos ( float ratio ) { screenRatio = ratio; }

		public float getScreenRatio () { return screenRatio; }
	}

	/**
	 * The background image to display as loading screen
	 */
	SizedRenderable	background;
	/**
	 * An image that can be rendered on the loading screen
	 */
	SizedRenderable	centerObject, upObject, downObject;

	/**
	 * Boolean telling if we have to adapt the sizes of the floating images on the
	 * screen, depending on the screen ratio
	 */
	boolean			adaptSizes;

	/**
	 * Base constructor for default loading screen
	 */
	public LoadingScreen () {
		background = new SmoothAnimation(
			ImageLoader.getInstance().get( GuiConsts.SimpleImage.BACK_MAIN_MENU ), -1, -1, 1
		);
		upObject = ImageLoader.getInstance().get( GuiConsts.SimpleImage.LOGO_GAME_BIG_ICON );
		centerObject = new GraphicString( GuiConsts.Strings.LOADING, GuiConsts.Font.TITLE, 40 );
		downObject = null;
		adaptSizes = true;
	}

	/**
	 * Base constructor for default loading screen
	 * 
	 * @param adaptive Tells if the floating images are adaptive
	 */
	public LoadingScreen ( boolean adaptive ) {
		background = new SmoothAnimation(
			ImageLoader.getInstance().get( GuiConsts.SimpleImage.BACK_MAIN_MENU ), -1, -1, 1
		);
		upObject = ImageLoader.getInstance().get( GuiConsts.SimpleImage.LOGO_GAME_BIG_ICON );
		centerObject = new GraphicString( GuiConsts.Strings.LOADING, GuiConsts.Font.TITLE, 40 );
		downObject = null;
		adaptSizes = adaptive;
	}

	/**
	 * Constructor for loading screen with only a background
	 * 
	 * @param back The background image of the loading screen
	 */
	public LoadingScreen ( SizedRenderable back ) {
		background = back;
		upObject = null;
		centerObject = null;
		downObject = null;
		adaptSizes = true;
	}

	/**
	 * Constructor for loading screen with only a background
	 * 
	 * @param back     The background image of the loading screen
	 * @param adaptive Tells if the floating images are adaptive
	 */
	public LoadingScreen ( SizedRenderable back, boolean adaptive ) {
		background = back;
		upObject = null;
		centerObject = null;
		downObject = null;
		adaptSizes = adaptive;
	}

	/**
	 * Constructor for loading screen with a background and centered object
	 * 
	 * @param back   The background image of the loading screen
	 * @param center The centered floating image
	 */
	public LoadingScreen ( SizedRenderable back, SizedRenderable center ) {
		background = back;
		upObject = null;
		centerObject = center;
		downObject = null;
		adaptSizes = true;
	}

	/**
	 * Constructor for loading screen with a background and centered object
	 * 
	 * @param back     The background image of the loading screen
	 * @param center   The centered floating image
	 * @param adaptive Tells if the floating images are adaptive
	 */
	public LoadingScreen ( SizedRenderable back, SizedRenderable center, boolean adaptive ) {
		background = back;
		upObject = null;
		centerObject = center;
		downObject = null;
		adaptSizes = adaptive;
	}

	/**
	 * Constructor for loading screen with a background, centered and top objects
	 * 
	 * @param back   The background image of the loading screen
	 * @param center The centered floating image
	 * @param up     The floating image on the top
	 */
	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = null;
		adaptSizes = true;
	}

	/**
	 * Constructor for loading screen with a background, centered and top objects
	 * 
	 * @param back     The background image of the loading screen
	 * @param center   The centered floating image
	 * @param up       The floating image on the top
	 * @param adaptive Tells if the floating images are adaptive
	 */
	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up, boolean adaptive ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = null;
		adaptSizes = adaptive;
	}

	/**
	 * Constructor for loading screen with a background, centered, top and bottom
	 * objects
	 * 
	 * @param back   The background image of the loading screen
	 * @param center The centered floating image
	 * @param up     The floating image on the top
	 * @param down   The floating image on the bottom
	 */
	public LoadingScreen ( SizedRenderable back, SizedRenderable center, SizedRenderable up, SizedRenderable down ) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = down;
		adaptSizes = true;
	}

	/**
	 * Constructor for loading screen with a background, centered, top and bottom
	 * objects
	 * 
	 * @param back     The background image of the loading screen
	 * @param center   The centered floating image
	 * @param up       The floating image on the top
	 * @param down     The floating image on the bottom
	 * @param adaptive Tells if the floating images are adaptive
	 */
	public LoadingScreen (
		SizedRenderable back, SizedRenderable center, SizedRenderable up, SizedRenderable down, boolean adaptive
	) {
		background = back;
		upObject = up;
		centerObject = center;
		downObject = down;
		adaptSizes = adaptive;
	}

	/**
	 * Method used to get the floating object corresponding to a given position
	 * 
	 * @param  pos The position of the wanted object
	 * @return     The image at the given position
	 */
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

	/**
	 * Setter for the adaptive attribute
	 * 
	 * @param adaptive The new value of the adaptive attribute
	 */
	public void setAdaptive ( boolean adaptive ) { adaptSizes = adaptive; }

	/**
	 * Setter for the floating images
	 * 
	 * @param pos The position of the image to set
	 * @param obj The new object of the image
	 */
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

	/**
	 * Method used for drawing one floating image
	 * 
	 * @param pos    The position of the image to draw
	 * @param width  The width of the loading screen
	 * @param height The height of the loading screen
	 * @param filter The color filter to apply on the image
	 */
	public void drawObject ( ObjectPos pos, float width, float height, Color filter ) {
		// Get the right object
		SizedRenderable obj = fromPos( pos );

		if ( obj != null ) {
			// Get the center of the image
			final float centerX = width / 2;
			final float centerY = height * pos.getScreenRatio();

			if ( adaptSizes ) {
				// Adjust the size of the image
				final int newW = ( int ) ( width * 1 / 4.0f );
				final int newH = ( int ) ( height * newW / width );
				final int newX = ( int ) ( centerX - newW / 2 );
				final int newY = ( int ) ( centerY - newH / 2 );

				if ( filter != null ) obj.draw( newX, newY, newW, newH, filter );
				else obj.draw( newX, newY, newW, newH );
			} else {
				final int newX = ( int ) ( centerX - obj.getCenterX() );
				final int newY = ( int ) ( centerY - obj.getCenterY() );

				if ( filter != null ) obj.draw( newX, newY, filter );
				else obj.draw( newX, newY );
			}
		}
	}

	/**
	 * Method used for drawing each floating image
	 * 
	 * @param width  The width of the loading screen
	 * @param height The height of the loading screen
	 * @param filter The color filter to apply on the image
	 */
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
