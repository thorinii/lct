package me.lachlanap.lct.data;

import java.util.Properties;
import me.lachlanap.lct.ConstantSettingException;

/**
 * A ConstantField that references an int field.
 * @author Lachlan Phillips
 */
public class IntConstantField extends ConstantField {

    public final int min, max;
    public final int original;

    public IntConstantField(Class<?> container, String field, String name,
            String constraints) {
        super(container, field, name);

        int min, max;
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;

        if (!constraints.isEmpty()) {
            int comma = constraints.indexOf(',');
            if (comma == -1)
                min = Integer.parseInt(constraints);
            else if (comma == constraints.length() - 1)
                min = Integer.parseInt(constraints.substring(0, constraints.length() - 1));
            else if (comma == 0)
                max = Integer.parseInt(constraints.substring(1));
            else {
                String[] split = constraints.split(",");
                min = Integer.parseInt(split[0]);
                max = Integer.parseInt(split[1]);
            }
        }

        this.min = min;
        this.max = max;
        this.original = get();
    }

    public IntConstantField(
            Class<?> container, String field, String name, int min, int max) {
        super(container, field, name);
        this.min = min;
        this.max = max;
        this.original = get();
    }

    /**
     * Gets the value of the field.
     * @return the value
     * @throws ConstantSettingException if getting fails
     */
    public int get() {
        try {
            return container.getField(field).getInt(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    /**
     * Sets the value of the field.
     * @param value the value
     * @throws ConstantSettingException if setting fails
     */
    public void set(int value) {
        try {
            container.getField(field).setInt(null, value);
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

        try {
            int intValue = Integer.parseInt(strValue);
            set(intValue);
        } catch (NumberFormatException nfe) {
        }
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
