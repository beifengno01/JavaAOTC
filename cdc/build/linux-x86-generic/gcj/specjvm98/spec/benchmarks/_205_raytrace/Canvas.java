/*
 * @(#)Canvas.java	1.8 06/17/98
 *
 * Canvas.java
 * The offscreen framebuffer class for drawing into and then displaying on
 * the screen.
 * Nov 11, 1997, Nik, toook uout zero prints from validation
 *
 * Copyright (c) 1998 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 * Copyright (c) 1996,1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks._205_raytrace;


/**
 * class Canvas
 */
public class Canvas  {
    private int Width;
    private int Height;
    private int[] Pixels;

    /**
     * SetRed
     * @param x
     * @param y
     * @param component
     */
    private 
    void SetRed(int x, int y, float component) { 

	 int index = y * Width + x;
	 Pixels[index] = Pixels[index] & 0x00FFFF00 | ((int) component);
    }

    /**
     * SetGreen
     * @param x
     * @param y
     * @param component
     */
    private 
    void SetGreen(int x, int y, float component) { 

	 int index = y * Width + x;
	 Pixels[index] = Pixels[index] & 0x00FF00FF | (((int) component) << 8);
    }

    /**
     * SetBlue
     * @param x
     * @param y
     * @param component
     */
    private 
    void SetBlue(int x, int y, float component) { 

	 int index = y * Width + x;
	 Pixels[index] = Pixels[index] & 0x0000FFFF | (((int) component)
							<< 16);
    }

    /**
     * Canvas
     * @param width
     * @param height
     */
    public
    Canvas(int width, int height) { 
	Width=width;
	Height=height;
	

	 if (Width < 0 || Height < 0) {
		System.err.print("Invalid window size!" + "\n");
		//System.exit(-1);
	 }
	 Pixels = new int[Width * Height];
    }

    /**
     * GetWidth
     * @return int
     */
    public
    int GetWidth() { 

	 return (Width);
    }

    /**
     * GetHeight
     * @return int
     */
    public
    int GetHeight() { 

	 return (Height);
    }

    /**
     * Write
     * @param brightness
     * @param x
     * @param y
     * @param color
     */
    public
    void Write(float brightness, int x, int y, Color color) { 

	 color.Scale(brightness);
	 float max = color.FindMax();
	 if (max > 1.0f) color.Scale(1.0f / max);
	 SetRed(x, y, color.GetRed() * 255);
	 SetGreen(x, y, color.GetGreen() * 255);
	 SetBlue(x, y, color.GetBlue() * 255);
//System.out.println( "Set "+x+","+y+"="+Pixels[y * GetWidth() + x] );	 
    }

    /**
     * Draw
     * @param theGraphics
     */
    public
    void Draw(/*Graphics theGraphics*/) { 
	 int x, y;

	 for (y = 0; y < GetHeight(); y++) {
		for (x = 0; x < GetWidth(); x++) {
		    int index = y * GetWidth() + x;
		}
	 }
    }

    /**
     * WriteDiag
     *
     * Write out the diagonal values from the bottom left corner to the top right.
     * Used only to write validity check information. **NS**
     */
    public
    void WriteDiag() { 
	 int min = Math.min(GetHeight(), GetWidth());

	 for (int i = (min*min)-min; i>0; i -= min-1) {
	     spec.io.PrintStream p = (spec.io.PrintStream)spec.harness.Context.out;
	     if (Pixels [i] != 0) p.println('1', ""+Pixels[i]);  // NS
	 }
    }


};
