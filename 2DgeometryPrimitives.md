# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) 2D geometry primitives #

The goal for these new classes for 2D geometry primitives is:
  * Simple classes for doing affine 2D geometry that works as closely with standard libraries as possible
  * Builds on top of Java AWT Point classes

For anything more complicated use these comprehensive existing mathematics libraries:
  * [commons-math](http://commons.apache.org/math/)
  * [colt](http://dsd.lbl.gov/~hoschek/colt/)

# 2D geometry primitives main classes #
  * **CPointInt** Point based in int
  * **CPointDouble** Point based in double
  * **CLine** A straight line with only start and end point
  * **MultiLine** A curve made up of straight lines
  * **Polygon** Collection of point and lines
  * **MultiLinePolygon** Collection of point, lines and multi lines

# 2D geometry primitives main interfaces #
  * **IPoint2D** implemented by CPointInt and CPointDouble

# Annotations #
There are annotations for both points and lines.

Annotations for points live in 2 enumerations:
  * PixelType used when working on pixels in a bitmap
  * PointType used when working on points as coordinates

## PointType enumeration ##
```
	/** Before a type is determined */
	UNKNOWN,
	
	/** If the point has a sharp angle for now 30 degrees is set as the limit */
	HARD_CORNER,

	/** If it is not a hard point */
	SOFT_POINT,
	
	/** 3 or more lines meets, U is for unknown */
	U_JUNCTION,
	
	/** 3 lines meet 2 are collinear and the last is somewhat orthogonal */
	T_JUNCTION,

	/** 3 lines meet less than 180 degrees between all of them */
	ARROW_JUNCTION,

	/** 3 lines meet, not a T junction */
	Y_JUNCTION,
	
	/** point is an end point, 
	 * maybe later there should be a distinction between end points and have 
	 * nothing else close by and end points that have close neighbors */
	END_POINT,
```

## LineType enumeration ##
Annotations for lines live in the LineType enumeration:

```
	/** Before a type is determined */
	UNKNOWN,
	
	/** Not completely straight */
	STRAIGHT, //only a start and an end point

	/** only on one side of a line between the start and end point */
	CURVE_ARCH,

	/** On both sides of a line between the start and end point 
	 * this is the rest category for lines */
	WAVE,

	/** Means that this is an arch and that it is concave compared to 1 or both 
	 * neighbor points, meaning they are on the same side of the line as the 
	 * arch */
	CONCAVE_ARCH,
	
	/** This line contains an inflection point, meaning the end points have 
	 * different signed direction changes */
	INFLECTION_POINT,
	
	/** For multi lines, if 2 lines are added together */
	STRAIGHT_MULTI_LINE, 
	
	/** Multi lines or polygon, is a circle */
	WHOLE_CIRCLE,
```