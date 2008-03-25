package org.shapelogic.imageprocessing;

import junit.framework.TestCase;

/** Test GrayArea.
 * <br />
 * 
 * @author Sami Badawi
 *
 */
public class GrayAreaTest extends TestCase {
	
	public void test1Points() {
		GrayArea grayArea = new GrayArea(10,20,100);
		assertEquals(1, grayArea.getArea());
		assertEquals(10., grayArea.getCenterPoint().getX());
		assertEquals(20., grayArea.getCenterPoint().getY());
		assertEquals(10., grayArea.getBoundingBox().minVal.getX());
		assertEquals(20., grayArea.getBoundingBox().minVal.getY());
		assertEquals(10., grayArea.getBoundingBox().maxVal.getX());
		assertEquals(20., grayArea.getBoundingBox().maxVal.getY());
		assertEquals(100, grayArea.getMeanGray());
	}

	public void test2Points() {
		GrayArea grayArea = new GrayArea(10,20,100);
		grayArea.addPoint(40, 30, 0);
		assertEquals(2, grayArea.getArea());
		assertEquals(25., grayArea.getCenterPoint().getX());
		assertEquals(25., grayArea.getCenterPoint().getY());
		assertEquals(10., grayArea.getBoundingBox().minVal.getX());
		assertEquals(20., grayArea.getBoundingBox().minVal.getY());
		assertEquals(40., grayArea.getBoundingBox().maxVal.getX());
		assertEquals(30., grayArea.getBoundingBox().maxVal.getY());
		assertEquals(50, grayArea.getMeanGray());
	}

	public void testFactory() {
		GrayAreaFactory grayAreaFactory = new GrayAreaFactory();
		GrayArea grayArea = (GrayArea) grayAreaFactory.makePixelArea(10,20,100);
		assertEquals(1, grayArea.getArea());
		assertEquals(10., grayArea.getCenterPoint().getX());
		assertEquals(20., grayArea.getCenterPoint().getY());
		assertEquals(10., grayArea.getBoundingBox().minVal.getX());
		assertEquals(20., grayArea.getBoundingBox().minVal.getY());
		assertEquals(10., grayArea.getBoundingBox().maxVal.getX());
		assertEquals(20., grayArea.getBoundingBox().maxVal.getY());
		assertEquals(100, grayArea.getMeanGray());
	}

}
