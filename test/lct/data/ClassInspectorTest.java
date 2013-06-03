/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lct.data;

import java.util.List;
import lct.IntConstant;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author lachlan
 */
public class ClassInspectorTest {

    @Test
    public void testBlankClass() {
        ClassInspector inspector = new ClassInspector();
        List<ConstantField> constants = inspector.getConstants(BlankClass.class);

        assertThat(constants.size(), is(0));
    }

    public static class BlankClass {
    }

    @Test
    public void testNoConstantsClass() {
        ClassInspector inspector = new ClassInspector();
        List<ConstantField> constants = inspector.getConstants(NoConstantsClass.class);

        assertThat(constants.size(), is(0));
    }

    public static class NoConstantsClass {

        public static int VARIABLE;
    }

    @Test
    public void testOneConstantClass() {
        ClassInspector inspector = new ClassInspector();
        List<ConstantField> constants = inspector.getConstants(OneConstantClass.class);

        assertThat(constants.size(), is(1));
        assertEquals(OneConstantClass.class, constants.get(0).container);
        assertThat(constants.get(0).field, is("CONSTANT"));
        assertThat(constants.get(0).name, is("Constant"));
    }

    public static class OneConstantClass {

        @IntConstant(name = "Constant")
        public static int CONSTANT;
    }
}