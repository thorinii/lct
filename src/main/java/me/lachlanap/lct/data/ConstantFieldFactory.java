package me.lachlanap.lct.data;

import java.util.ArrayList;
import java.util.List;
import me.lachlanap.lct.Constant;
import me.lachlanap.lct.ConstantException;
import me.lachlanap.lct.spi.ConstantFieldProvider;
import me.lachlanap.lct.spi.impl.PrimitivesProvider;

/**
 * A provider based system for creating {@link ConstantField}s from raw fields.
 *
 * This implementation is initialised with a {@link PrimitivesProvider}.
 * @author Lachlan Phillips
 */
public class ConstantFieldFactory {

    private final List<ConstantFieldProvider> providers;

    public ConstantFieldFactory() {
        providers = new ArrayList<>();
        providers.add(new PrimitivesProvider());
    }

    /**
     * Adds a provider to the end of the provider list.
     */
    public void addProvider(ConstantFieldProvider provider) {
        providers.add(provider);
    }

    /**
     * Tries to create a ConstantField from a raw field. Cycles through all the providers
     * until one is found that can satisfy.
     * <p/>
     * @param container the class enclosing the field
     * @param field the name of the field
     * @param annot the Constant annotation to extract constraints from
     * @return the constructed ConstantField
     * @throws ConstantException when the field cannot be found, or no provider is found
     */
    public ConstantField createConstantField(Class<?> container, String field, Constant annot)
            throws ConstantException {
        Class<?> type;

        try {
            type = container.getField(field).getType();
        } catch (NoSuchFieldException e) {
            throw new ConstantException("Cannot find constant " + annot.name()
                    + " (" + container.getSimpleName() + "." + field + ")", e);
        }

        for (ConstantFieldProvider provider : providers) {
            if (provider.canProvide(type))
                return provider.getField(type, container, field, annot);
        }

        throw new ConstantException("No provider found for " + annot.name()
                + " of type " + type.getSimpleName());
    }
}
