/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lct.data;

import lct.ConstantSettingException;
import lct.IntConstant;

/**
 *
 * @author lachlan
 */
public class ConstantField {

    public final Class<?> container;
    public final String field;
    public final String name;

    public ConstantField(
            Class<?> container, String field, String name) {
        this.container = container;
        this.field = field;
        this.name = name;
    }

    public void set(int value) {
        try {
            container.getField(field).setInt(null, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    public static ConstantField from(
            Class<?> aClass, String field, IntConstant annot) {
        return new ConstantField(aClass, field, annot.name());
    }
}
