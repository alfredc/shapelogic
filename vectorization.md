# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) Vectorization algorithms #

Simple algorithm for transforming bitmap lines into vector lines.

# Different implementations #

There are currently 3 different vectorizer implementations.
They all assume that you are working on binary gray scale images images.
So before you can use a vectorizer you have to convert your bitmap to this format.

The vectorizers are part of a class hierarchy of different vectorization algorithms, all sub classed from BaseVectorizer.

## MaxDistanceVectorizer ##
The main idea is that this will read a whole multi line at a time.
Then later it will split it according to max distance of pixels to the line between start and end point of the multi line.

## LineVectorizer ##
LineVectorizer is a vectorizer reading sequences of short line of length 5.
If 2 consecutive lines are close in direction you can merge them.
But if they are far away in angle you create a new line.

This approach ran into problems and has been deprecated for now.

## DirectionBasedVectorizer ##
The main idea is that this will read a whole multi line at a time.
It is using a priority of different types of points around an intersection to read trough an intersection where you can take different ways.

This approach ran into problems and has been deprecated for now.

# Future implementations #
All the current implementations
  * Assumes that you have a gray scale image with only black and white in it
  * Are modifying the bitmap

It would probably be a good idea to loosen some of these restrictions.