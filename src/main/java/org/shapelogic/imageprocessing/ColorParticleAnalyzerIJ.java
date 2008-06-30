package org.shapelogic.imageprocessing;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.measure.ResultsTable;
import ij.plugin.filter.ExtendedPlugInFilter;
import ij.plugin.filter.PlugInFilterRunner;
import ij.process.ImageProcessor;

import org.shapelogic.color.IColorAndVariance;
import org.shapelogic.imageprocessing.ColorParticleAnalyzer;
import org.shapelogic.imageutil.IJGui;
import org.shapelogic.imageutil.IJImage;
import org.shapelogic.imageutil.PixelArea;
import org.shapelogic.imageutil.SLImage;
import org.shapelogic.polygon.Polygon;
import org.shapelogic.util.Headings;

/** Automatic particle counter for 24 bit RGB and 8 bit Gray.
 * <br />
 * 
 * Works with rectangular ROIs<br />
 * 
 * @author Sami Badawi
 *
 */
public class ColorParticleAnalyzerIJ extends ColorParticleAnalyzer implements ExtendedPlugInFilter {

	protected ResultsTable _rt;
    protected GenericDialog _gd;
    
    protected static int _iterationsStatic = ITERATIONS_DEFAULTS;
    protected static double _maxDistanceStatic = MAX_DISTANCE_DEFAULTS;
    protected static int _minPixelsInAreaStatic = MIN_PIXELS_IN_AREA_DEFAULTS;
    protected static int _maxPixelsInAreaStatic = MAX_PIXELS_IN_AREA_DEFAULTS;
    
	@Override
	public void run(ImageProcessor ip) {
		run();
	}

	@Override
	public int setup(String arg, ImagePlus imp) {
		_guiWrapper = IJGui.INSTANCE;
		if (imp == null)
			return setup(arg, (SLImage)null);
		return setup(arg, new IJImage(imp));
		
	}
	
	@Override
	protected void displayResultsTable() {
    	_rt.show("Particle properties");
	}

	@Override
	public void setNPasses(int passes) {
	}

	@Override
	public int showDialog(ImagePlus imp, String command, PlugInFilterRunner pfr) {
        _iterations = 3;
        _maxDistance = 70;
        _minPixelsInArea = 10;
        _gd = new GenericDialog(getClass().getSimpleName(), IJ.getInstance());
        _gd.addNumericField("Max_distance: ", _maxDistanceStatic, 0);
        _gd.addNumericField("Min_pixels: ", _minPixelsInAreaStatic, 0);
        _gd.addNumericField("Max_pixels: ", _maxPixelsInAreaStatic, 0);
        _gd.addNumericField("Iterations: ", _iterationsStatic, 0);
        _gd.showDialog();
        if (_gd.wasCanceled()) {
            return DONE;
        }
        _maxDistance = _maxDistanceStatic = _gd.getNextNumber();
        _minPixelsInArea = _minPixelsInAreaStatic = (int)_gd.getNextNumber();
        _maxPixelsInArea = _maxPixelsInAreaStatic = (int)_gd.getNextNumber();
        _iterations = _iterationsStatic = (int)_gd.getNextNumber();
        return IJ.setupDialog(imp, _setupReturnValue);
	}

	@Override
	protected void defaultColumnDefinitions() {
	    _rt = new ResultsTable();
		_rt.setDefaultHeadings();
		_rt.getFreeColumn(Headings.COLOR);
		_rt.getFreeColumn(Headings.PERIMETER);
		_rt.getFreeColumn(Headings.CIRCULARITY);
		_rt.getFreeColumn(Headings.ASPECT_RATIO);
	}

	@Override
	protected boolean populateResultsTableRow(int index) {
		try {
    		IColorAndVariance particle = _particleStream.get(index);
        	if (particle == null)
        		return false;
        	_rt.incrementCounter();
        	_rt.addValue(ResultsTable.AREA, particle.getArea());
        	_rt.addValue(ResultsTable.STD_DEV, particle.getStandardDeviation());
        	_rt.addValue(Headings.COLOR, particle.getMeanColor());
        	PixelArea pixelArea = particle.getPixelArea();
        	if (pixelArea != null) {
            	_rt.addValue(ResultsTable.X_CENTER_OF_MASS, pixelArea.getCenterPoint().getX());
            	_rt.addValue(ResultsTable.Y_CENTER_OF_MASS, pixelArea.getCenterPoint().getY());
        	}
			_rt.addValue(Headings.ASPECT_RATIO, _aspectRatioStream.get(index));
			Polygon polygon = _polygonStream.get(index); 
			if (polygon == null) {
				_rt.addLabel("Label", "Missing polygon.");
			}
			else {
				int perimeter = polygon.getPerimeter();
				_rt.addValue(Headings.PERIMETER, perimeter);
				double circularity = perimeter==0?0.0:4.0*Math.PI*particle.getArea()/(perimeter*perimeter);
				_rt.addValue(Headings.CIRCULARITY, circularity);
			}
			if (_categorizer != null) {
				String category = _categorizer.get(index);
				if (category != null && !"".equals(category.trim()))
					_rt.addLabel("Label", category);
				else
					_rt.addLabel("Label", "NA");
			}
			return true;
		} catch (RuntimeException e) {
			String errorMessage = "Error: " + e.getMessage();
			_rt.addLabel("Label", errorMessage);
			System.out.println(errorMessage);
			e.printStackTrace();
			return false;
		}
	}
}
