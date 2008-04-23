package org.shapelogic.imageprocessing;

import org.shapelogic.color.ColorAndVarianceI;
import org.shapelogic.mathematics.StorelessDiscriptiveStatistic;

/** ColorArea holds the information.
 * <br />
 * 
 * Should this have getter and setters?<br />
 * 
 * Maybe start without.<br />
 * 
 * Should this be split in color and gray?<br />
 * 
 * I think that maybe it should.<br />
 * 
 * @author Sami Badawi
 *
 */
public class ColorArea extends PixelArea {

	protected int _startColor;
	
	protected StorelessDiscriptiveStatistic[] _colorStatistics = new StorelessDiscriptiveStatistic[3];

	private int[] splitColors = new int[3];  
	
    public ColorArea(int x, int y, int startColor) {
    	super(x,y);
    	_startColor = startColor;
    	ColorUtil.splitColor(startColor, splitColors);
    	for (int i = 0; i < 3; i++) {
    		_colorStatistics[i] = new StorelessDiscriptiveStatistic();
    		_colorStatistics[i].increment(splitColors[i]);
    	}
    }
    
    public void putPixel(int x, int y, int color) {
    	addPoint(x, y);
    	ColorUtil.splitColor(color, splitColors);
    	for (int i = 0; i < 3; i++) {
    		_colorStatistics[i].increment(splitColors[i]);
    	}
    }
    
    public int getMeanRed() {
    	return (int) _colorStatistics[ColorUtil.RED_POS].getMean();
    }
    
    public int getMeanGreen() {
    	return (int) _colorStatistics[ColorUtil.GREEN_POS].getMean();
    }
    
    public int getMeanBlue() {
    	return (int) _colorStatistics[ColorUtil.BLUE_POS].getMean();
    }
    
    public int getMeanColor() {
    	return ColorUtil.packColors(
    			(int)_colorStatistics[ColorUtil.RED_POS].getMean(),
    			(int)_colorStatistics[ColorUtil.GREEN_POS].getMean(),
    			(int)_colorStatistics[ColorUtil.BLUE_POS].getMean());
    }

	@Override
	public double getStandardDeviation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void merge(ColorAndVarianceI colorAndVariance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getColorChannels() {
		// TODO Auto-generated method stub
		return null;
	}
}
