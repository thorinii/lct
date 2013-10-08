package me.lachlanap.lct.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import me.lachlanap.lct.Constant;

/**
 * Field extractor.
 * @author Lachlan Phillips
 */
public class ClassInspector {

    /**
     * The ConstantFieldFactory from construction.
     */
    protected final ConstantFieldFactory factory;

    /**
     * Creates a ClassInspector with specified ConstantFieldFactory.
     * @param factory
     */
    public ClassInspector(ConstantFieldFactory factory) {
        this.factory = factory;
    }

    /**
     * Gets all fields that are potential 'constants'.
     * @param aClass the class to work from
     * @return all constants that could be found
     */
    public List<ConstantField> getConstants(Class<?> aClass) {
        List<ConstantField> constants = new ArrayList<>();
        List<Field> fields = Arrays.asList(aClass.getFields());

        for (Field field : fields) {
            constants.add(processField(aClass, field));
        }

        Iterator<ConstantField> itr = constants.iterator();
        while (itr.hasNext()) {
            if (itr.next() == null)
                itr.remove();
        }

        return constants;
    }

    /**
     * Converts a field/potential-constant into a ConstantField.
     * Returns null if the field cannot be constant.
     * Override this to change how fields are processed.
     * @param aClass the class the field came from
     * @param field the field
     * @return a ConstantField or null
     */
    protected ConstantField processField(Class<?> aClass, Field field) {
        Constant annot = field.getAnnotation(Constant.class);
        if (annot != null) {
            return factory.createConstantField(aClass, field.getName(), annot);
        }

        return null;
    }
}
