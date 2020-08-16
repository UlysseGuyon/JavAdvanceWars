package org.ulysseguyon.aw.gui.utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;


public class SmoothAnimation implements Renderable {

	protected static final int MILLIS_PER_STEP = 50;

	protected int getNewPos ( int offsetPerStep, long timeDiff ) {
		return offsetPerStep
				* ( int ) ( Math.floor( ( timeDiff * speed ) / ( float ) SmoothAnimation.MILLIS_PER_STEP ) );
	}

	protected Image img;
	protected int xOffset;
	protected int yOffset;
	protected int speed;

	protected Point pivot;

	protected long lastXUpdate;
	protected long lastYUpdate;

	public SmoothAnimation ( Image img, int xDir, int yDir, int speed ) {
		this.img = img;

		xOffset = ( int ) Math.signum( xDir );
		yOffset = ( int ) Math.signum( yDir );
		this.speed = speed;

		pivot = new Point( 0, 0 );

		lastXUpdate = System.currentTimeMillis();
		lastYUpdate = lastXUpdate;
	}

	protected void updatePivot () {
		final long newUpdateTime = System.currentTimeMillis();

		final long xTimeDiff = newUpdateTime - lastXUpdate;
		final long yTimeDiff = newUpdateTime - lastYUpdate;

		final int xDiff = getNewPos( xOffset, xTimeDiff );
		final int yDiff = getNewPos( yOffset, yTimeDiff );

		if ( Math.abs( xDiff ) > 0 ) {
			float boundedNewX = pivot.getX() + xDiff;
			while ( boundedNewX < 0 || boundedNewX >= img.getWidth() ) {
				boundedNewX -= Math.signum( boundedNewX ) * img.getWidth();
			}
			pivot.setX( boundedNewX );
			lastXUpdate = newUpdateTime;
		}

		if ( Math.abs( yDiff ) > 0 ) {
			float boundedNewY = pivot.getY() + yDiff;
			while ( boundedNewY < 0 || boundedNewY >= img.getHeight() ) {
				boundedNewY -= Math.signum( boundedNewY ) * img.getHeight();
			}
			pivot.setY( boundedNewY );
			lastYUpdate = newUpdateTime;
		}
	}

	protected void drawImagePart ( Rectangle onImage, Rectangle onScreen, Color filter ) {
		if ( filter == null ) {
			img.draw( onScreen.getMinX(), onScreen.getMinY(), onScreen.getMaxX(), onScreen.getMaxY(), onImage.getMinX(),
					onImage.getMinY(), onImage.getMaxX(), onImage.getMaxY() );
		}
		else {
			img.draw( onScreen.getMinX(), onScreen.getMinY(), onScreen.getMaxX(), onScreen.getMaxY(), onImage.getMinX(),
					onImage.getMinY(), onImage.getMaxX(), onImage.getMaxY(), filter );
		}
	}

	@Override
	public void draw ( float x, float y ) {
		draw( x, y, null );
	}

	@Override
	public void draw ( float x, float y, Color filter ) {
		draw( x, y, img.getWidth(), img.getHeight(), filter );
	}

	@Override
	public void draw ( float x, float y, float width, float height ) {
		draw( x, y, width, height, null );
	}

	@Override
	public void draw ( float x, float y, float width, float height, Color filter ) {
		updatePivot();

		final float xScale = width / img.getWidth();
		final float yScale = height / img.getHeight();

		Rectangle nw = new Rectangle( 0, 0, pivot.getX(), pivot.getY() );
		Rectangle ne = new Rectangle( pivot.getX(), 0, img.getWidth() - pivot.getX(), pivot.getY() );
		Rectangle sw = new Rectangle( 0, pivot.getY(), pivot.getX(), img.getHeight() - pivot.getY() );
		Rectangle se = new Rectangle( pivot.getX(), pivot.getY(), img.getWidth() - pivot.getX(),
				img.getHeight() - pivot.getY() );

		Rectangle sse = new Rectangle( x, y, se.getWidth() * xScale, se.getHeight() * yScale );
		Rectangle ssw = new Rectangle( x + sse.getWidth(), y, sw.getWidth() * xScale, sw.getHeight() * yScale );
		Rectangle sne = new Rectangle( x, y + sse.getHeight(), ne.getWidth() * xScale, ne.getHeight() * yScale );
		Rectangle snw = new Rectangle( x + sse.getWidth(), y + sse.getHeight(), nw.getWidth() * xScale,
				nw.getHeight() * yScale );

		// draw the nw part of the img on the se part of the screen (scaled)
		drawImagePart( nw, snw, filter );
		// draw the ne part of the img on the sw part of the screen (scaled)
		drawImagePart( ne, sne, filter );
		// draw the sw part of the img on the ne part of the screen (scaled)
		drawImagePart( sw, ssw, filter );
		// draw the se part of the img on the nw part of the screen (scaled)
		drawImagePart( se, sse, filter );
	}

}
