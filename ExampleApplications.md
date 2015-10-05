# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) Example Applications showing how to use the ShapeLogic framework #

This is a description of example applications that comes with the ShapeLogic framework.
They should facilitate getting started.

# Recognition of Straight capital letters in ShapeLogic 0.2 #

This was a proof of concept used under the development of the logic engine.

This is using very simple properties of a polygon with only straight lines.
It is counting number of points, lines, vertical line and horizontal lines.
This is enough to distinguish the different capital letters, but it is pretty brittle.

It works on both letters in both SVN and bitmap format.

## Rules for the letter A ##

```
new NumericRule("A", POINT_COUNT, polygon, POINT_COUNT_EX, 5.),
new NumericRule("A", LINE_COUNT, polygon, LINE_COUNT_EX, 5.),
new NumericRule("A", HORIZONTAL_LINE_COUNT, polygon, HORIZONTAL_LINE_COUNT_EX, 1.),
new NumericRule("A", VERTICAL_LINE_COUNT, polygon, VERTICAL_LINE_COUNT_EX, 0.),
new NumericRule("A", END_POINT_COUNT, polygon, END_POINT_COUNT_EX, 2.),
```

## How to see letter recognition in action ##

  * Install ShapeLogic under ImageJ
  * Draw a straight capital letter
  * Make sure that it is in binary format: inverse sorted gray scale
  * Call the skeletonizer / thinner
  * Go to the ShapeLogic menu select Maximum distance vectorizer
  * A dialog will appear with the letter that was matched if any

# Recognition of all capital letters in ShapeLogic 0.3 #

The approach to handle curves was not to match with circle arch or Bézier curve.
Instead line and point are annotated.
And they can be filtered according both position in the bounding box for a polygon and what annotations the point and line has. The is a substantially richer structure that you can use for your rules.

## Line annotation in LineType ##
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

```

## Point annotation in PointType ##
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