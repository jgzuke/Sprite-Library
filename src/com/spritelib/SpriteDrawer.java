/**
 * behavior for all sprites
 */
package com.spritelib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

abstract public class SpriteDrawer
{
	private Matrix rotateImages = new Matrix();
	public SpriteDrawer()
	{
		
	}
	/**
	 * draws a sprite
	 */
	public void drawFlat(Sprite sprite, Canvas g, Paint p)
	{
		if(sprite!=null && onScreen(sprite.x, sprite.y, sprite.width, sprite.height))
		{
			g.drawBitmap(sprite.image, (int)sprite.x-(sprite.width/2), (int)sprite.y-(sprite.height/2), p);
		}
	}
	/**
	 * draws a sprite
	 */
	public void drawFlat(Sprite sprite, Bitmap image, Canvas g, Paint p)
	{
		int w = image.getWidth();
		int h  = image.getHeight();
		if(sprite!=null && onScreen(sprite.x, sprite.y, w, h))
		{
			g.drawBitmap(image, (int)sprite.x-(w/2), (int)sprite.y-(h/2), p);
		}
	}
	/**
	 * draws a sprite
	 */
	public void draw(Sprite sprite, Canvas g, Paint p)
	{
		if(sprite!=null&&onScreen(sprite.x, sprite.y, sprite.width, sprite.height))
		{
				rotateImages.reset();
				rotateImages.postTranslate(-sprite.width / 2, -sprite.height / 2);
				rotateImages.postRotate((float) sprite.rotation);
				rotateImages.postTranslate((float) sprite.x, (float) sprite.y);
				g.drawBitmap(sprite.image, rotateImages, p);
		}
	}
	/**
	 * Replaces canvas.drawBitmap(Bitmap, Rect, Rect, Paint) and auto scales
	 */
	public void drawRect(Bitmap image, Rect r, Canvas g, Paint p)
	{
		if(onScreen((r.left+r.right)/2, (r.top+r.bottom)/2, r.right - r.left, r.bottom - r.top))
		{
			g.drawBitmap(image, null, r, p);
		}
	}
	abstract protected boolean onScreen(double x, double y, int width, int height);
}