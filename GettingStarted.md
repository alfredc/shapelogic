# ![http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png](http://shapelogic.googlecode.com/svn/wiki/shapelogicsmalltransparent.png) Getting started #

For a more up to date version of this page go to [Getting Started](http://www.shapelogic.org/getting-started.html) on the project site.

You can use ShapeLogic in different ways:
  * User mode
  * Development mode

# User mode #

## Steps to get ShapeLogic working from within ImageJ from binary distribution ##
  * Download shapelogicplugin0.7.zip
  * Unzip it.
  * This will create a directory named shapelogicplugin
  * Take everything from this directory and copy it into ./ImageJ/plugins/

## Testing ShapeLogic letter matching from within ImageJ ##
  * Open ImageJ
  * Create a new image say 100 time 100
  * Draw a letter in black on white background
  * Go to the shapelogic menu and select item CapitalLetterMatch. Which does the following
    1. Convert the image to a binary image
    1. Call ImageJ's build in Skeletonize
    1. Vectorize into bitmap into polygon
    1. Clean up the vectorized polygon
    1. Then annotate the polygon
    1. Then match the polygon against all the rules for all the capital letters
    1. With a little luck you will see a dialog box saying what letter was matched
    1. If more than one letter match the match will fail
    1. If the match fails then all the properties of the polygon will be written to the console for debugging

## Steps to get ShapeLogic working from within ImageJ manually ##
If you need a different version of shapelogic_-0.7.jar, say one compiled for JDK 1.5.
  * Create a ShapeLogic dir under ImageJ: ./ImageJ/plugins/shapelogic
  * Take the jar file for ShapeLogic: shapelogic_-0.7.jar, and move it to that directory
  * Add the jar files that ShapeLogic is dependent on to the ImageJ/plugin dir:
    1. antlr-runtime-3.0.jar
    1. commons-collections-3.2.jar
    1. commons-jexl-1.1.jar
    1. commons-logging-1.0.4.jar
    1. commons-math-1.1.jar
    1. guice-1.0.jar

These jar file can be found on any Marven 2 repository:

E.g. commons-math-1.1.jar can be found here:
http://repo1.maven.org/maven2/commons-math/commons-math/1.1/

Or they can be taken out of the shapelogicplugin0.7.zip

ShapeLogic 0.7 should come with a version that contains all these dependent jars.

When you open ImageJ there will be a ShapeLogic menu with commands in.

You can get the binary distribution from the download page or compile it yourself.

## Separating Java and logic rules ##
If you need to add you own rules to ShapeLogic you need to add you changes and compile ShapeLogic.

The goal is to separate the databases with the logical rules from the executable jar file, so you do not have to recompile.

# Development mode #

ShapeLogic is build using [Maven 2](http://maven.apache.org/).

This makes several build tasks very easy, so this is the recommended way doing builds, but the code should run fine without Maven.

Currently the project is set up to work directly with Eclipse 3.3, Java 1.6, but you can use: NetBeans, IntelliJ, JBuilder, emacs or vi.

## Steps to compile and run unit tests ##

Check ShapeLogic out into a local directory.

Do a cd into that directory.

Run:
  * **mvn test** runs the unit tests locally
  * **mvn package** runs the unit tests locally, and if they passes it will create the jar file

## Changing JDK version when building with Maven ##

You will have to change the JDK target in pom.xml:
```
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
```