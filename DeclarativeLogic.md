# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) Declarative logic #

ShapeLogic has a declarative goal driven logic engine.

## Influences ##
  * Prolog language
  * AI choice tree
  * Oz language

# Important ideas #
  * Tree of task and sub tasks
  * Hierarchical contexts that is binding objects to name
  * Object hypothesis called OH in the code. OH = any match result

## Hierarchical contexts ##
So the context makes it possible to have the string "Letter" bound to both "A" and "B" at the same time in difference context. The context set up as a hierarchy, like in most programming languages. So names that I bound in a higher context can be seen in a lower one.
This rely on [Java Expression Language (JEXL)](http://commons.apache.org/jexl/)

## Tree of tasks ##
ShapeLogic is driven by a tree of tasks. The super class is called BaseTask.
A task can have sub tasks, but also have a context that inherits from the context of the parent task. But not all tasks have their own context.

## Example of task hierarchy in letter matching ##
For example of simple rules and task hierarchy look in the classes
  * LetterTaskFactory
  * LetterTaskTest

At the top level of the tree there is an Exclusive or task, XOrTask.
Under there there is an AndTask for each letter.
For each AndTask there are several simple property rules, called
  * SimpleTask
  * SimpleNumericalTask

Each AndTask have its own context that values can be set in, but all the SimpleTask under an AndTask share a context. When a letter finally is chosen then the context from the chosen context is propagated up.

## Example of logic rules in letter matching ##

So the vectorizer will put a MultiLinePolygon object into the string
"polygon" in a JEXL context.


Then there are rules that go in and look at the object using reflection.
Here is an example of a simple NumericRule:
```
NumericRule("A", "pointCount", "polygon.getPoints().size()", 5.),
```

Here is an example of all the rules for a letter:
```
NumericRule[] numericRulesForA = {
    new NumericRule("A", POINT_COUNT, POINT_COUNT_EX, 5.),
    new NumericRule("A", LINE_COUNT, LINE_COUNT_EX, 5.),
    new NumericRule("A", HORIZONTAL_LINE_COUNT, HORIZONTAL_LINE_COUNT_EX, 1.),
    new NumericRule("A", VERTICAL_LINE_COUNT, VERTICAL_LINE_COUNT_EX, 0.),
    new NumericRule("A", END_POINT_COUNT, END_POINT_COUNT_EX, 2.),
```

where

```
POINT_COUNT = "pointCount";
POINT_COUNT_EX = "polygon.getPoints().size()"
```

All these rules are combined into and AndTask.
A whole letter match will then be an XOrTask with an AndTask for each letter.

## Boolean combinations of filters with parameters ##
If you need to do more complex boolean combinations of filter on polygons try the following:
```
"polygon.filter('PointRightOfFilter(0.3) && PointAboveFilter(0.3)')"
"polygon.filter('PointOfTypeFilter(PointType.T_JUNCTION) && PointLeftOfFilter(0.5)').size()"
```

These will filter:
  * Points that are in in the right 30% and top 30% of the bounding box for the polygon.
  * Points that are in in the left 50% of the bounding box for the polygon and having the annotation T Junction.

## Logical operators ##

Currently ShapeLogic is using the standard Java, C, C++ notation:
```
  And use &&
  Or use ||
  Not use !
```

# Influences #

## Prolog ##

### Prolog history ###
Prolog was all the rage in the early 1980, the Japanese 5 generation project was mainly based on Prolog. It turned out that it did not scale well to real world problems. Partly because it was only good at handling symbolic information and not doing computations.
So Prolog fell out of favor.

I still think programming in Prolog is almost as simple as programming in SQL.

So ShapeLogic is trying to preserve this simplicity of Prolog, while maintaining ability to do efficient numerical calculation.

ShapeLogic is not using backtracking or unification. 2 of the main components in Prolog.
But the idea of a program as:
  * Goals / tasks with sub goals
  * Horn caluse

## AI choice tree ##
Unlike Prolog ShapeLogic can have multiple values for a name at the same time, and exploring different choices at the same time.

## Oz ##
[Oz](http://www.mozart-oz.org/) is a experimental language boasting:
  * Declarative programming
  * Functional programming
  * Object-oriented programming
  * Constraint programming
  * Fast concurrent programming

The way ShapeLogic is merging contexts when faced with different options is similar to what Oz did. This is currently not fully implemented.