package org.shapelogic.imageprocessing;

import org.shapelogic.color.IColorAndVariance;
import org.shapelogic.color.ValueAreaFactory;
import org.shapelogic.imageutil.SLImage;
import org.shapelogic.logic.CommonLogicExpressions;
import org.shapelogic.polygon.BBox;
import org.shapelogic.streams.NumberedStream;
import org.shapelogic.streams.StreamFactory;

import static org.shapelogic.imageutil.ImageUtil.runPluginFilterOnBufferedImage;

/** Test ParticleCounter. <br />
 * 
 * The difference of this from BaseParticleCounterTest is that this should test
 * more customized ParticleCounters, while BaseParticleCounterTest should test
 * the basic cases.
 * 
 * @author Sami Badawi
 *
 */
public class ColorParticleAnalyzerTest extends AbstractImageProcessingTests {
	BaseParticleCounter _particleCounter;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		_dirURL = "./src/test/resources/images/particles";
		_fileFormat = ".gif";
        _particleCounter = new ColorParticleAnalyzerIJ();
        _particleCounter.setDisplayTable(false);
	}
	
	public void testWhitePixelGray() {
		String fileName = "oneWhitePixelGray";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName), _particleCounter);
		assertEquals(1,bp.getWidth());
		assertTrue(bp.isInvertedLut());
		int pixel = bp.get(0,0);
		assertEquals(0,pixel); //So this is a white background pixel
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(1,factory.getStore().size());
		assertTrue(_particleCounter.isParticleImage());
		assertEquals(0,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = factory.getStore().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(0.,bBox.minVal.getX());
		assertEquals(0.,bBox.minVal.getY());
		assertEquals(0.,bBox.maxVal.getX());
		assertEquals(0.,bBox.maxVal.getY());
	}

	/** This shows that when you save and image as PNG it will always be opened 
	 * as a color image.
	 */
	public void testOneWhitePixelGrayPng() {
		String fileName = "oneWhitePixelGray";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertTrue(bp.isEmpty());
	}

	public void testBlobsGif() {
		String fileName = "blobs";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(7);
        _particleCounter.setIterations(3);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(65024,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(40,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(65,factory.getStore().size()); 
//		assertTrue(_particleCounter.isParticleImage()); 
//		assertEquals(30,_particleCounter.getParticleCount()); 
//		assertTrue(bp.isInvertedLut());
	}

	public void testEmbryos() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(20);
        _particleCounter.setIterations(3);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(61,factory.getStore().size()); //XXX should be 2
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(5,_particleCounter.getParticleCount()); 
		NumberedStream<Number> ns = StreamFactory.findNumberedStream(CommonLogicExpressions.ASPECT_RATIO);
		assertClose(0.9, ns.get(0).doubleValue(), 0.1);
		assertClose(0.77, ns.get(1).doubleValue(), 0.1);
	}
	
	public void assertClose(double expected, double found, double precision) {
		assertTrue(Math.abs(expected-found) < precision);
	}

	public void testEmbryosWithParameters() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(20);
        _particleCounter.setIterations(3);
        String parameters = "iterations=4 maxDistance=90 minPixelsInArea=70";
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter,parameters);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
//		assertEquals(383,factory.getStore().size()); //XXX should be 2
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(5,_particleCounter.getParticleCount()); 
	}

	public void testEmbryosMoreIterations() {
		String fileName = "embryos6";
        _particleCounter.setMaxDistance(100);
        _particleCounter.setMinPixelsInArea(20);
        _particleCounter.setIterations(4);
		SLImage bp = runPluginFilterOnBufferedImage(filePath(fileName,".jpg"), _particleCounter);
		assertEquals(256,bp.getWidth());
		assertEquals(52480,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(12561501,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(66,factory.getStore().size()); 
		assertTrue(_particleCounter.isParticleImage()); 
		assertEquals(7,_particleCounter.getParticleCount()); 
	}

	/** This gets opened as a byte interleaved and not as an int RGB
	 */
	public void testCleanSpotPng() {
		String fileName = "spot1Clean";
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(900,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(8.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(22.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(15.,bBox.getCenter().getY());
		assertEquals(1.,bBox.getAspectRatio());
	}

	/** This gets opened as a byte interleaved and not as an int RGB
	 */
	public void testOvalCleanPng() {
		String fileName = "oval1Clean";
		SLImage  bp = runPluginFilterOnBufferedImage(filePath(fileName,".png"), _particleCounter);
		assertEquals(30,bp.getWidth());
		assertEquals(1800,bp.getPixelCount());
		int pixel = bp.get(0,0);
		assertEquals(0xffffff,pixel);
		ValueAreaFactory factory = _particleCounter.getSegmentation().getSegmentAreaFactory();
		assertNotNull(factory);
		assertEquals(2,factory.getStore().size()); 
		assertEquals(1,_particleCounter.getParticleFiltered().size());
		IColorAndVariance particle = _particleCounter.getParticleFiltered().get(0);
		BBox bBox = particle.getPixelArea().getBoundingBox();
		assertEquals(8.,bBox.minVal.getX());
		assertEquals(16.,bBox.minVal.getY());
		assertEquals(22.,bBox.maxVal.getX());
		assertEquals(45.,bBox.maxVal.getY());
		assertEquals(15.,bBox.getCenter().getX());
		assertEquals(30.,bBox.getCenter().getY());
		assertEquals(0.4827586206896552,bBox.getAspectRatio());
	}
}
