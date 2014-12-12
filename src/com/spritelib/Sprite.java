/**
 * behavior for all sprites
 */
package com.spritelib;

import android.graphics.Bitmap;

abstract public class Sprite
{
	protected double x;
	protected double y;
	protected double rotation;
	protected int width;
	protected int height;
	protected boolean isVideo;
	protected int frame = 0;
	protected boolean playing;
	protected Bitmap image = null;
	protected boolean visible;
	
	public Sprite(double X, double Y, int Width, int Height, double Rotation,
	int Frame, boolean IsVideo, boolean Playing, boolean Visible, Bitmap Image)
	{
		x=X;
		y=Y;
		width=Width;
		height=Height;
		rotation=Rotation;
		frame=Frame;
		isVideo=IsVideo;
		playing=Playing;
		visible = Visible;
		image = Image;
		sizeImage();
	}
	protected void swapImage(Bitmap newImage)
	{
		image = newImage;
	}
	protected void sizeImage()
	{
		width = image.getWidth();
		height = image.getHeight();
	}
	/**
	 * called every frame, performs desired actions
	 */
	protected void frameCall()
	{
		if(isVideo&&playing) frame++;
	}
}