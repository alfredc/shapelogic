# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) FAQ wiki page #

## Problem running ShapeLogic ##

### Why won't ShapeLogic run as a plugin for ImageJ? ###
The problem is that there are several jar files that are needed for it to run.
This is described on the [GettingStarted](GettingStarted.md) page

### What version of Java JDK does ShapeLogic work with ###
ShapeLogic is compiled with JDK version 1.6, but should work fine if compiled with JDK 1.5.

A version compiled with 1.5 might be released.

### How do I install a snapshot version of ShapeLogic ###
  * Download the jar file.
  * Go to ./ImageJ/plugins/shapelogic
  * Append the word old to the current version e.g.: shapelogic-0.7.jar.old
  * Copy the downloaded or compiled version to ./ImageJ/plugins/shapelogic

## Other questions ##

### Does ShapeLogic work with other plugins for ImageJ ###
  * ShapeLogic should work with other plugins.
  * ShapeLogic uses off the shelf open source libraries when possible.
  * ShapeLogic is a very lightweight framework.

### Does ShapeLogic work without ImageJ ###
Not now. But ShapeLogic is very loosely coupled to ImageJ, so abstracting the connection to ImageJ out should not be a big task.

## User questions ##

Add your questions here.
This page is intended for users to post question.
This might be replaced with a regular user list.