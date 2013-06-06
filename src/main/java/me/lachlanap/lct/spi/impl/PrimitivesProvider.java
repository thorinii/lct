package me.lachlanap.lct.spi.impl;

import me.lachlanap.lct.Constant;
import me.lachlanap.lct.data.ConstantField;
import me.lachlanap.lct.data.IntConstantField;
import me.lachlanap.lct.spi.ConstantFieldProvider;

/**
 *
 * @author lachlan
 */
public class PrimitivesProvider implements ConstantFieldProvider {

    @Override
    public boolean canProvide(Class<?> type) {
        if (type == int.class)
            return true;
        if (type == long.class)
            return true;
        if (type == double.class)
            return true;
        if (type == float.class)
            return true;
        return false;
    }

    @Override
    public ConstantField getField(
            Class<?> type,
            Class<?> container, String field, Constant annot) {

        if (type == int.class)
            return createInt(container, field, annot);
        throw new UnsupportedOperationException("Cannot create constant of type " + type.getSimpleName());
    }

    private ConstantField createInt(Class<?> container, String field, Constant annot) {
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;

        String con = annot.constraints();

        if (!con.isEmpty()) {
            int comma = con.indexOf(',');
            if (comma == -1)
                min = Integer.parseInt(con);
            else if (comma == con.length() - 1)
                min = Integer.parseInt(con.substring(0, con.length() - 1));
            else if (comma == 0)
                max = Integer.parseInt(con.substring(1));
            else {
                String[] constraints = annot.constraints().split(",");
                min = Integer.parseInt(constraints[0]);
                max = Integer.parseInt(constraints[1]);
            }
        }

        return new IntConstantField(container, field, annot.name(), min, max);
    }
}
