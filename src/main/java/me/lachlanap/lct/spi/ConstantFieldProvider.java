package me.lachlanap.lct.spi;

import me.lachlanap.lct.Constant;
import me.lachlanap.lct.ConstantException;
import me.lachlanap.lct.data.ConstantField;

/**
 *
 * @author Lachlan Phillips
 */
public interface ConstantFieldProvider {

    /**
     * Checks if this provider can create a ConstantField for this type of field.
     * @param type the type of the field
     * @return if it can provide
     */
    public boolean canProvide(Class<?> type);

    /**
     * Tries to get a ConstantField for the specified data.
     * @param type the type of field
     * @param container the containing class
     * @param field the field name
     * @param annot the Constant annotation to extract constraints
     * @return the ConstantField
     * @throws ConstantException if it cannot provide, or an error happens
     */
    public ConstantField getField(Class<?> type, Class<?> container, String field, Constant annot);
}
