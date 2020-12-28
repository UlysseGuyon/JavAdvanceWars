package org.ulysseguyon.aw.gui.utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;


/**
 * Class used for creating rectangle graphics in which the image is moving
 * linearly at a given speed
 * 
 * @author Ulysse Guyon
 */
public class SmoothAnimation implements SizedRenderable {

	protected static final int	MILLIS_PER_STEP	= 50;

	/**
	 * The image moving in this animation
	 */
	protected Image				img;
	/**
	 * The sign of the movement on the x axis, 1 or -1
	 */
	protected int				xOffset;
	/**
	 * The sign of the movement on the y axis, 1 or -1
	 */
	protected int				yOffset;
	/**
	 * The speed factor of the movement
	 */
	protected int				speed;

	/**
	 * The point in the image that is currently used as pivot. Each of the four
	 * parts of the image delimited by this pivot is exchanged with the part of the
	 * opposite side to form the new image
	 */
	protected Point				pivot;

	/**
	 * Last time at which the value was updated
	 */
	protected long				lastXUpdate, lastYUpdate;

	/**
	 * Last size value applied to the image
	 */
	protected int				lastWidth, lastHeight;

	/**
	 * Constructor initializing the animated image
	 * 
	 * @param img   The image to use as animation
	 * @param xDir  The direction of the animation on the x axis
	 * @param yDir  The direction of the animation on the y axis
	 * @param speed The speed of the animation
	 */
	public SmoothAnimation ( Image img, int xDir, int yDir, int speed ) {
		this.img = img;

		xOffset = ( int ) Math.signum( xDir );
		yOffset = ( int ) Math.signum( yDir );
		this.speed = speed;

		pivot = new Point( 0, 0 );

		lastXUpdate = System.currentTimeMillis();
		lastYUpdate = lastXUpdate;
	}

	/**
	 * Method used for calculating the distance traveled by the pivot on each axis
	 * 
	 * @param  offsetPerStep The direction to take for the current axis
	 * @param  timeDiff      The difference of time since the last calculation
	 * @return               The distance traveled by the pivot since last
	 *                       calculation
	 */
	protected int getNewPos ( int offsetPerStep, long timeDiff ) {
		return offsetPerStep
			* ( int ) ( Math.floor( ( timeDiff * speed ) / ( float ) SmoothAnimation.MILLIS_PER_STEP ) );
	}

	/**
	 * Static utilitary method for bounding a value using modulos
	 * 
	 * @param  firstVal   The value to bound
	 * @param  lowerBound The value of the lower bound
	 * @param  upperBound The value of the upper bound
	 * @return            The bounded value
	 */
	private static float boundNewPoint ( float firstVal, int lowerBound, int upperBound ) {
		if ( upperBound < lowerBound ) return firstVal;

		float boundedNewVal = firstVal;

		while ( boundedNewVal < lowerBound || boundedNewVal >= upperBound ) {
			boundedNewVal -= Math.signum( boundedNewVal ) * upperBound;
		}

		return boundedNewVal;
	}

	/**
	 * Method used for updating the pivots position
	 */
	protected void updatePivot () {
		// Update the time
		final long newUpdateTime = System.currentTimeMillis();

		final long xTimeDiff = newUpdateTime - lastXUpdate;
		final long yTimeDiff = newUpdateTime - lastYUpdate;

		// Get the traveled distance for each axis
		final int xDiff = getNewPos( xOffset, xTimeDiff );
		final int yDiff = getNewPos( yOffset, yTimeDiff );

		// Change the x position of the pivot
		if ( Math.abs( xDiff ) > 0 ) {
			pivot.setX( SmoothAnimation.boundNewPoint( pivot.getX() + xDiff, 0, img.getWidth() ) );
			lastXUpdate = newUpdateTime;
		}

		// Change the Y position of the pivot
		if ( Math.abs( yDiff ) > 0 ) {
			pivot.setY( SmoothAnimation.boundNewPoint( pivot.getY() + yDiff, 0, img.getHeight() ) );
			lastYUpdate = newUpdateTime;
		}
	}

	/**
	 * Method used for drawing the adjusted image on screen
	 * 
	 * @param onImage  The rectangle of the image to draw
	 * @param onScreen The portion of the screen to draw on
	 * @param filter   The color filter to apply on the image
	 */
	protected void drawImagePart ( Rectangle onImage, Rectangle onScreen, Color filter ) {
		if ( filter == null ) {
			img.draw(
				onScreen.getMinX(), onScreen.getMinY(), onScreen.getMaxX(), onScreen.getMaxY(), onImage.getMinX(),
				onImage.getMinY(), onImage.getMaxX(), onImage.getMaxY()
			);
		} else {
			img.draw(
				onScreen.getMinX(), onScreen.getMinY(), onScreen.getMaxX(), onScreen.getMaxY(), onImage.getMinX(),
				onImage.getMinY(), onImage.getMaxX(), onImage.getMaxY(), filter
			);
		}
	}

	@Override
	public void draw ( float x, float y ) { draw( x, y, null ); }

	@Override
	public void draw ( float x, float y, Color filter ) { draw( x, y, img.getWidth(), img.getHeight(), filter ); }

	@Override
	public void draw ( float x, float y, float width, float height ) { draw( x, y, width, height, null ); }

	@Override
	public void draw ( float x, float y, float width, float height, Color filter ) {
		updatePivot();

		lastWidth = ( int ) width;
		lastHeight = ( int ) height;

		final float xScale = width / img.getWidth();
		final float yScale = height / img.getHeight();

		Rectangle nw = new Rectangle( 0, 0, pivot.getX(), pivot.getY() );
		Rectangle ne = new Rectangle( pivot.getX(), 0, img.getWidth() - pivot.getX(), pivot.getY() );
		Rectangle sw = new Rectangle( 0, pivot.getY(), pivot.getX(), img.getHeight() - pivot.getY() );
		Rectangle se = new Rectangle(
			pivot.getX(), pivot.getY(), img.getWidth() - pivot.getX(), img.getHeight() - pivot.getY()
		);

		Rectangle sse = new Rectangle( x, y, se.getWidth() * xScale, se.getHeight() * yScale );
		Rectangle ssw = new Rectangle( x + sse.getWidth(), y, sw.getWidth() * xScale, sw.getHeight() * yScale );
		Rectangle sne = new Rectangle( x, y + sse.getHeight(), ne.getWidth() * xScale, ne.getHeight() * yScale );
		Rectangle snw = new Rectangle(
			x + sse.getWidth(), y + sse.getHeight(), nw.getWidth() * xScale, nw.getHeight() * yScale
		);

		// draw the nw part of the img on the se part of the screen (scaled)
		drawImagePart( nw, snw, filter );
		// draw the ne part of the img on the sw part of the screen (scaled)
		drawImagePart( ne, sne, filter );
		// draw the sw part of the img on the ne part of the screen (scaled)
		drawImagePart( sw, ssw, filter );
		// draw the se part of the img on the nw part of the screen (scaled)
		drawImagePart( se, sse, filter );
	}

	@Override
	public int getWidth () { return lastWidth; }

	@Override
	public int getHeight () { return lastHeight; }

}
