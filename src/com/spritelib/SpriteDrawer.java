/**
 * behavior for all sprites
 */
package com.spritelib;
abstract public class SpriteDrawer
{
	protected double x;
	protected double y;
	protected double rotation;
	protected double width;
	protected double height;
	protected boolean isVideo;
	protected int frame = 0;
	protected boolean playing;
	/**
	 * called every frame, performs desired actions
	 */
	protected void frameCall()
	{
		if(isVideo&&playing) frame++;
	}
}