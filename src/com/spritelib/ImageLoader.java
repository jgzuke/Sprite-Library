/**
 * Loads, stores and resizes all graphics
 */
package com.spritelib;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
abstract public class ImageLoader
{
	public String getting;
	public Resources res;
	public String packageName;
	public BitmapFactory.Options opts;
	/**
	 * loads in images and optimizes settings for loading
	 * @param contextSet start activity for getting resources etc
	 * @param controlSet control object
	 */
	public ImageLoader(Context contextSet)
	{
		opts = new BitmapFactory.Options();
		opts.inDither = false;
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		opts.inTempStorage = new byte[16 * 1024];
		packageName = contextSet.getPackageName();
		res = contextSet.getResources();
	}
	/**
	 * recycles desired array of images
	 * @param length length of array
	 * @param array array to recycle
	 */
	protected void recycleArray(Bitmap[] array)
	{
		int length = array.length;
		for(int i = 0; i < length; i++)
		{
			if(array[i] != null)
			{
				array[i].recycle();
				array[i] = null;
			}
		}
	}
	/**
	 * recycles desired array of images
	 * @param length length of array
	 * @param array array to recycle
	 */
	protected void recycleArray(Bitmap[][] array)
	{
		int length = array.length;
		for(int i = 0; i < length; i++)
		{
			if(array[i] != null)
			{
				int length2 = array[i].length;
				for(int j = 0; j < length2; j++)
				{
					if(array[i][j] != null)
					{
						array[i][j].recycle();
						array[i][j] = null;
					}
				}
			}
		}
	}
	/**
	 * Loads and resizes array of images
	 * @param length Length of array to load
	 * @param start Starting string which precedes array index to match resource name
	 * @param width End width of image being loaded
	 * @param height End height of image being loaded
	 */
	public Bitmap[] loadArray1D(int length, String start, int width, int height)
	{
		Bitmap[] newArray = new Bitmap[length];
		for(int i = 0; i < length; i++)
		{
			getting = start + correctDigits(i + 1, 4);
			newArray[i] = loadImage(getting, width, height);
		}
		return newArray;
	}
	/**
	 * Loads and resizes array of images
	 * @param length Length of array to load
	 * @param start Starting string which precedes array index to match resource name
	 * @param width End width of image being loaded
	 * @param height End height of image being loaded
	 */
	public Bitmap[][] loadArray2D(int length, int length2, String start, int width, int height)
	{
		Bitmap[][] newArray = new Bitmap[length][length2];
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length2; j++)
			{
				getting = start + correctDigits(i + (j*length) + 1, 4);
				newArray[i][j] = loadImage(getting, width, height);
			}
		}
		return newArray;
	}
	/**
	 * Adds 0's before string to make it four digits long
	 * Animations done in flash which when exporting .png sequence end file name with four character number
	 * @return Returns four character version of number
	 */
	protected String correctDigits(int start, int digits)
	{
		String end = Integer.toString(start);
		while(end.length() < digits)
		{
			end = "0" + end;
		}
		return end;
	}
	/**
	 * Loads image of name given from resources and scales to specified width and height
	 * @return Returns bitmap loaded and resized
	 */
	public Bitmap loadImage(String imageName, int width, int height)
	{
		int imageNumber = res.getIdentifier(imageName, "drawable", packageName);
		return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, imageNumber, opts), width, height, false);
	}
}