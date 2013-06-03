/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lct.data;

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

    public static ConstantField from(
            Class<?> aClass, String field, IntConstant annot) {
        return new ConstantField(aClass, field, annot.name());
    }
}
