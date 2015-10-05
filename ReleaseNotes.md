# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) Release notes for ShapeLogic #

ShapeLogic releases:

# 2007-12-07 ShapeLogic v 0.8 released AKA Letter matching is almost working (for the forth time) #
  * Create ShapeLogic documentation site, checked the documentation into SVN.
  * Add Javadoc to ShapeLogic documentation site.
  * Created a way simpler and more expressive syntax for matching rules at the lowest level.
  * More general logical expression instead of just equal and greater than test at the lowest level now you can use the following 3 predicates for matching: ==, >, <.
  * Clean up letter recognition example, take out all the size() that are part of expressions.
  * There is around 16000 lines of Java code
  * 190 unit test that all works on my local machine

# 2007-11-27 ShapeLogic release 0.7 AKA Alpha 1 #

Content of 0.7.0:
  * First alpha quality release
  * Letter recognition example more robust
  * Bug fix so logic language directly can use annotation
  * More general logical expression instead of just equal test at the lowest level
  * Includes a binary distribution that contains all the dependent jar files, to ease deployment
  * There is around 16000 lines of Java code
  * 182 unit test that all works on my local machine

# 2007-11-20 ShapeLogic release 0.3.0  AKA All capital letters release #

Content of 0.3.0:
  * There is around 15000 lines of Java code
  * 172 unit test that all works on my local machine
  * Fixed problems with logic language for filters
  * Fixed several bugs
  * Letter matching is now extended to work for all capital letters

There are still:
  * The logic language is having problems with parsing some annotations, so the letter matching is not very robust

# 2007-11-08 ShapeLogic release 0.2.0 AKA the Smilla release #
Named after the most fantastic prematurely born baby.
The code is released somewhat prematurely, and still needs more cleanup.
This is the state of code when it was first uploaded to Google Code Hosting.
Content of 0.2.0:
  * There is around 14000 lines of Java code
  * 159 unit test that all works on my local machine.
  * Color segmentation
  * 3 implementations of line vectorizers
  * Point and line annotation
  * Rudimentary declarative logic engine
  * Example of rule database for recognizing capital letters
  * Maybe half the code is well commented
  * There are some documentation on the Google Code Hosting page

# 2007-09-29 ShapeLogic was created on Google Code Hosting #
Development started August 2007.