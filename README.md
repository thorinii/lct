lct - The Live Constant Tweaker
===============================

The Live Constant Tweaker is designed for use in game developement, to help get your numbers (eg physics constants, timers) _just right_. It is Java based.

This is still a work in progress - as such you can't really use it yet.

[![Build Status](https://travis-ci.org/ThorinII/lct.png?branch=master)](https://travis-ci.org/ThorinII/lct)

Expected Use
============

Defining constants
------------------

I'm not sure whether there'll be just a *Constant* annotation or one for each data type.
Here's an example of with typed annotations.

```java
public static class PhysicsConstants {
  @FloatConstant(name = "Gravity", min = 0, max = 100)
  public static float GRAVITY = 9.81f;
  
  @IntConstant(name = "Substeps in Frame", min = 1)
  public static int NUMBER_OF_SUBSTEPS = 3;
  
  @DoubleConstant(name = "Coefficient of Friction")
  public static double FRICTION_COEFFICIENT = 0.3;
}
```

What I'd like to do is use just one *Constant* annotation, and determine the type from the field signature, but I'm not sure how to handle things like _min_ and _max_.

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


