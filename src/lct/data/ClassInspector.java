/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lct.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lct.IntConstant;

/**
 *
 * @author lachlan
 */
public class ClassInspector {

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

    private ConstantField processField(Class<?> aClass, Field field) {
        IntConstant annot = field.getAnnotation(IntConstant.class);
        if (annot != null) {
            return ConstantField.from(aClass, field.getName(), annot);
        }

        return null;
    }
}
