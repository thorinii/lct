package me.lachlanap.lct;

import me.lachlanap.lct.data.IntConstantField;

/**
 *
 * @author lachlan
 */
public class ConstantSettingException extends ConstantException {

    public ConstantSettingException(IntConstantField field) {
        super("Could not set field " + field.name + " of " + field.container.getCanonicalName());
    }

    public ConstantSettingException(IntConstantField field, Throwable cause) {
        super("Could not set field " + field.name + " of " + field.container.getCanonicalName(), cause);
    }
}
