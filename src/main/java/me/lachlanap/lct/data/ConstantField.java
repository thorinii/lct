package me.lachlanap.lct.data;

import java.util.Properties;
import me.lachlanap.lct.ConstantSettingException;
import me.lachlanap.lct.IntConstant;

/**
 *
 * @author lachlan
 */
public class ConstantField {

    public final Class<?> container;
    public final String field;
    public final String name;
    public final int min, max;

    public ConstantField(
            Class<?> container, String field, String name, int min, int max) {
        this.container = container;
        this.field = field;
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public int get() {
        try {
            return container.getField(field).getInt(null);
        } catch (IllegalAccessException e) {
            throw new ConstantSettingException(this, e);
        } catch (NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    public void set(int value) {
        try {
            container.getField(field).setInt(null, value);
        } catch (IllegalAccessException e) {
            throw new ConstantSettingException(this, e);
        } catch (NoSuchFieldException e) {
            throw new ConstantSettingException(this, e);
        }
    }

    /**
     * Loads this constant's settings from the Properties. If they don't exist this will do nothing.
     */
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

    public void saveToProperties(Properties props) {
        String strValue = String.valueOf(get());
        props.setProperty(container.getSimpleName() + "." + name, strValue);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.container != null ? this.container.hashCode() : 0);
        hash = 73 * hash + (this.field != null ? this.field.hashCode() : 0);
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        ConstantField other = (ConstantField) obj;
        if (this.container != other.container && (this.container == null || !this.container.equals(other.container)))
            return false;
        return this.field.equals(other.field) && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "[" + name + " (" + container.getSimpleName() + "." + field + "]";
    }

    public static ConstantField from(
            Class<?> aClass, String field, IntConstant annot) {
        return new ConstantField(aClass, field,
                                 annot.name(), annot.min(), annot.max());
    }
}
