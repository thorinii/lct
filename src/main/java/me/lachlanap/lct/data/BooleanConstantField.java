package me.lachlanap.lct.data;

import java.util.Properties;
import me.lachlanap.lct.ConstantSettingException;

/**
 * A ConstantField that references an boolean field.
 * @author Lachlan Phillips
 */
public class BooleanConstantField extends ConstantField {

    public final boolean original;

    public BooleanConstantField(Class<?> container, String field, String name) {
        super(container, field, name);
        original = get();
    }

    /**
     * Gets the value of the field.
     * @return the value
     * @throws ConstantSettingException if getting fails
     */
    public boolean get() {
        try {
            return container.getField(field).getBoolean(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    /**
     * Sets the value of the field.
     * @param value the value
     * @throws ConstantSettingException if setting fails
     */
    public void set(boolean value) {
        try {
            container.getField(field).setBoolean(null, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    /**
     * Loads this constant's settings from the Properties. If they don't exist this will do nothing.
     */
    @Override
    public void loadFromProperties(Properties props) {
        String strValue = props.getProperty(container.getSimpleName() + "." + name);
        if (strValue == null)
            return;

        boolean intValue = Boolean.parseBoolean(strValue);
        set(intValue);
    }

    /**
     * Loads this constant's settings from the Properties. If they don't exist this will do nothing.
     */
    @Override
    public void saveToProperties(Properties props) {
        String strValue = String.valueOf(get());
        props.setProperty(container.getSimpleName() + "." + name, strValue);
    }

    @Override
    public void reset() {
        set(original);
    }
}
