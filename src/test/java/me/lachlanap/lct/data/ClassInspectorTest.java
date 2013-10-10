package me.lachlanap.lct.data;

import java.util.List;
import me.lachlanap.lct.Constant;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Lachlan Phillips
 */
public class ClassInspectorTest {

    @Test
    public void testBlankClass() {
        ClassInspector inspector = new ClassInspector(new ConstantFieldFactory());
        List<ConstantField> constants = inspector.getConstants(BlankClass.class);

        assertThat(constants.size(), is(0));
    }

    public static class BlankClass {
    }

    @Test
    public void testNoConstantsClass() {
        ClassInspector inspector = new ClassInspector(new ConstantFieldFactory());
        List<ConstantField> constants = inspector.getConstants(NoConstantsClass.class);

        assertThat(constants.size(), is(0));
    }

    public static class NoConstantsClass {

        public static int VARIABLE;
    }

    @Test
    public void testOneConstantClass() {
        ClassInspector inspector = new ClassInspector(new ConstantFieldFactory());
        List<ConstantField> constants = inspector.getConstants(OneConstantClass.class);

        assertThat(constants.size(), is(1));

        IntConstantField field = (IntConstantField) constants.get(0);
        assertEquals(OneConstantClass.class, field.container);
        assertThat(field.field, is("CONSTANT"));
        assertThat(field.name, is("Constant"));
        assertThat(field.min, is(1));
        assertThat(field.max, is(6));
    }

    public static class OneConstantClass {

        @Constant(name = "Constant", constraints = "1,6")
        public static int CONSTANT;
    }
}
