/**
 * The most useful Live Constant Tweaker classes.
 *
 * This package contains what most people will need to use the Live Constant Tweaker.
 *
 * To define constants for tweaking, use it like this:
 * <pre><blockquote>
 * import me.lachlanap.lct.Constant;
 * ...
 * public class AClassContainingConstants {
 *   &#064;Constant(name = "Gravity", constraints = "1,100")
 *   public static float GRAVITY = 9.81f;
 *
 *   &#064;Constant(name = "Substeps in Frame", constraints = "1,")
 *   public static int NUMBER_OF_SUBSTEPS = 3;
 *
 *   &#064;Constant(name = "Coefficient of Friction")
 *   public static double FRICTION_COEFFICIENT = 0.3;
 * }
 * </blockquote></pre>
 *
 * Then to do tweaking, at your application's startup:
 * <pre><blockquote>
 * import me.lachlanap.lct.LCTManager;
 *
 * LCTManager lct = new LCTManager();
 * lct.register(AClassContainingConstants.class);
 * </blockquote></pre>
 *
 * For visual tweaking (kinda the aim):
 * <pre><blockquote>
 * import me.lachlanap.lct.gui.LCTEditor;
 * import me.lachlanap.lct.gui.LCTFrame;
 *
 * // Using an LCTEditor for putting in a Swing GUI:
 * LCTEditor editor = new LCTEditor(lct);
 * // add it to a panel...
 *
 * // Using an LCTFrame for a standalone GUI:
 * LCTFrame frame = new LCTFrame(lct);
 * // set visible when needed:
 * frame.setVisible(true);
 * </blockquote></pre>
 *
 * You can save and load settings:
 *
 * <pre><blockquote>
 * String file = "game-constants.properties";
 * try {
 *   lct.loadSettings(file);
 * } catch (IOException ioe) {
 *   // handle file reading errors...
 * }
 *
 * try {
 *   lct.saveSettings(file);
 * } catch (IOException ioe) {
 *   // handle file writing errors...
 * }
 * </blockquote></pre>
 */
package me.lachlanap.lct;
