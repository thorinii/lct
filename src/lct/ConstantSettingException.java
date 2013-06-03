/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lct;

import lct.data.ConstantField;

/**
 *
 * @author lachlan
 */
public class ConstantSettingException extends RuntimeException {

    private final ConstantField field;

    public ConstantSettingException(ConstantField field) {
        super("Could not set field " + field.name + " of " + field.container.getCanonicalName());
        this.field = field;
    }

    public ConstantSettingException(ConstantField field, Throwable cause) {
        super("Could not set field " + field.name + " of " + field.container.getCanonicalName(),
              cause);
        this.field = field;
    }

    public ConstantField getField() {
        return field;
    }
}
