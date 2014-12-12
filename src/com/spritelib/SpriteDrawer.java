/**
 * behavior for all sprites
 */
package com.spritelib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

abstract public class SpriteDrawer
{
	private Matrix rotateImages = new Matrix();
	private Paint p;
	public SpriteDrawer(Paint P)
	{
		p=P;
	}
	/**
	 * updates the paint to use
	 */
	protected void updatePaint(Paint P)
	{
		p=P;
	}
	/**
	 * draws a sprite
	 */
	protected void draw(Sprite sprite, int levelX, int levelY, Canvas g)
	{
		int width = sprite.width;
		int height = sprite.height;
		int lowX = (int) sprite.x - (width / 2);
		int lowY = (int) sprite.y - (height / 2);
		if(inView(lowX, lowY, width, height, levelX, levelY))
		{
			rotateImages.reset();
			rotateImages.postTranslate(-width / 2, -height / 2);
			rotateImages.postRotate((float) sprite.rotation);
			rotateImages.postTranslate((float) sprite.x, (float) sprite.y);
			g.drawBitmap(sprite.image, rotateImages, p);
			sprite = null;
		}
	}
	/**
	 * checks whether object is in view
	 * @param lowx objects low x
	 * @param lowy objects low y
	 * @param width objects width
	 * @param height objects height
	 * @return whether object is in view
	 */
	private boolean inView(int lowx, int lowy, int width, int height, int levelX, int levelY)
	{
		lowx += levelX - 10;
		int highx = lowx + width + 20;
		lowy += levelY - 10;
		int highy = lowy + height + 20;
		return !(lowx > 300 || highx < 0 || lowy > 300 || highy < 0);
	}
}