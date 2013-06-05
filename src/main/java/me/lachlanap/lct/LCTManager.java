package me.lachlanap.lct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.lachlanap.lct.data.ClassInspector;
import me.lachlanap.lct.data.ConstantField;

/**
 *
 * @author lachlan
 */
public class LCTManager {

    private final List<ConstantField> fieldList;
    private final Map<String, ConstantField> fields;

    public LCTManager() {
        fieldList = new ArrayList<ConstantField>();
        fields = new HashMap<String, ConstantField>();
    }

    public void addField(ConstantField field) {
        fieldList.add(field);
        fields.put(field.name, field);
    }

    public List<ConstantField> getFields() {
        return fieldList;
    }

    public void register(Class<?> aClass) {
        ClassInspector inspector = new ClassInspector();
        List<ConstantField> constants = inspector.getConstants(aClass);

        fieldList.addAll(constants);
        for (ConstantField constant : constants)
            fields.put(constant.name, constant);
    }

    public void loadSettings() {
    }

    public void set(String name, int i) {
        ConstantField constant = fields.get(name);
        constant.set(i);
    }
}
