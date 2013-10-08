package me.lachlanap.lct.data;

import me.lachlanap.lct.Constant;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Lachlan Phillips
 */
public class ConstantFieldFactoryTest {

    @Test
    public void testIntConstant() throws NoSuchFieldException {
        ConstantFieldFactory factory = new ConstantFieldFactory();
        String name = "INTEGER";

        ConstantField field = factory.createConstantField(Constants.class,
                                                          name,
                                                          Constants.class.getField(name).getAnnotation(Constant.class));

        assertThat(field, is(IntConstantField.class));

        IntConstantField intField = (IntConstantField) field;
        assertThat(intField.min, is(0));
        assertThat(intField.max, is(1));
    }

    @Test
    public void testDoubleConstant() throws NoSuchFieldException {
        ConstantFieldFactory factory = new ConstantFieldFactory();
        String name = "DOUBLE";

        ConstantField field = factory.createConstantField(Constants.class,
                                                          name,
                                                          Constants.class.getField(name).getAnnotation(Constant.class));

        assertThat(field, is(DoubleConstantField.class));

        DoubleConstantField doubleField = (DoubleConstantField) field;
        assertThat(doubleField.min, is(Double.MIN_VALUE));
        assertThat(doubleField.max, is(500.3));
    }

    public static class Constants {

        @Constant(name = "Integer", constraints = "0,1")
        public static int INTEGER;
        @Constant(name = "Double", constraints = ",500.3")
        public static double DOUBLE;
    }
}