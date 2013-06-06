package me.lachlanap.lct;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.lachlanap.lct.gui.LCTFrame;

/**
 *
 * @author lachlan
 */
public class MainTesting {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                IllegalAccessException |
                InstantiationException |
                UnsupportedLookAndFeelException e) {
            // We don't particularly care if the LaF cannot be set
            e.printStackTrace();
        }

        LCTManager lct = new LCTManager();
        lct.register(ClassOfConstantTesting.class);
        lct.register(SecondSetOfConstants.class);

        LCTFrame frame = new LCTFrame(lct);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static class ClassOfConstantTesting {

        @IntConstant(name = "Number", min = 1, max = 13)
        public static int NUMBER = 10;
        @IntConstant(name = "Integer", min = 6, max = 8)
        public static int INTEGER = 6;
    }

    public static class SecondSetOfConstants {

        @IntConstant(name = "Gravity", min = 0, max = 100)
        public static int GRAVITY = 7;
        @IntConstant(name = "Drag", min = 0, max = 10)
        public static int DRAG = 2;
    }
}
