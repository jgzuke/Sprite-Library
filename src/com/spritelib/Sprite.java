/**
 * behavior for all sprites
 */
package com.spritelib;
abstract public class Sprite
{
	protected double x;
	protected double y;
	protected double rotation;
	protected double width;
	protected double height;
	protected boolean isVideo;
	protected int frame = 0;
	protected boolean playing;
	public Sprite(double X, double Y, double Width, double Height, double Rotation, int Frame,
			boolean IsVideo, boolean Playing)
	{
		x=X;
		y=Y;
		width=Width;
		height=Height;
		rotation=Rotation;
		frame=Frame;
		isVideo=IsVideo;
		playing=Playing;
	}
	/**
	 * called every frame, performs desired actions
	 */
	protected void frameCall()
	{
		if(isVideo&&playing) frame++;
	}
}