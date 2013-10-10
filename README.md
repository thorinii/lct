lct - The Live Constant Tweaker
===============================

The Live Constant Tweaker is designed for use in game developement, to help get your numbers (eg physics constants, timers) _just right_. It is Java based.

This is still a work in progress - as such it shouldn't really be used for 'real' projects.

[![Build Status](https://travis-ci.org/ThorinII/lct.png?branch=master)](https://travis-ci.org/ThorinII/lct)

Using Live Constant Tweaker
===========================

Add this to your Maven pom:
```
<dependency>
    <groupId>me.lachlanap.lct</groupId>
    <artifactId>lct</artifactId>
    <version>1.1</version>
</dependency>
```

or translate it into the correct format for your build system.

Expected Use
============

Defining constants
------------------

Define your constants as ```public static``` fields (not ```final``` - that would defeat the purpose of tweaking), and annotate them with the Constant annotation:
```java
public static class PhysicsConstants {
  @Constant(name = "Gravity", constraints = "1,100")
  public static float GRAVITY = 9.81f;
  
  @Constant(name = "Substeps in Frame", constraints = "1,")
  public static int NUMBER_OF_SUBSTEPS = 3;
  
  @Constant(name = "Coefficient of Friction")
  public static double FRICTION_COEFFICIENT = 0.3;
}
```

Note there is just one Constant annotation. Currently, you specify constraints like a limited range using a String - the exact format depends on the type of the field, but for number constants use it like this: "min,max", where either min or max can be omitted. Note the constraints parameter is optional.

Using the system
----------------

Just create an instance of the me.lachlanap.lct.LCTManager class:
```java
LCTManager lct = new LCTManager();
```

Then register all your constant classes:
```java
lct.register(PhysicsConstants.class);
// more if needed...
```

Load any saved settings (the idea is that you tweak settings, then save them):
```java
String file = "game-constants.properties";
try {
  lct.loadSettings(file);
} catch (IOException ioe) {
  // handle file reading errors...
}
```

When you want to show the settings tweaker, either create an LCTEditor and install it in a Swing GUI, or just create an LCTFrame:
```java
// Using an LCTEditor
LCTEditor editor = new LCTEditor(lct);
// add it to a panel...

// Using an LCTFrame
LCTFrame frame = new LCTFrame(lct);
frame.setVisible(true);
```

When all the tweaking is done, save the settings:
```java
String file = "game-constants.properties";
try {
  lct.saveSettings(file);
} catch (IOException ioe) {
  // handle file writing errors...
}
```


