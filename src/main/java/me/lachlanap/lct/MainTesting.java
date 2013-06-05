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

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        LCTManager lct = new LCTManager();
        lct.register(ClassOfConstantTesting.class);

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
}
