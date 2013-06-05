package me.lachlanap.lct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import me.lachlanap.lct.data.ClassInspector;
import me.lachlanap.lct.data.ConstantField;

/**
 *
 * @author lachlan
 */
public class LCTManager {

    private final List<ConstantField> constantList;
    private final Map<String, ConstantField> constants;

    public LCTManager() {
        constantList = new ArrayList<>();
        constants = new HashMap<>();
    }

    public void addConstant(ConstantField field) {
        constantList.add(field);
        constants.put(field.name, field);
    }

    public List<ConstantField> getFields() {
        return constantList;
    }

    public void register(Class<?> aClass) {
        ClassInspector inspector = new ClassInspector();
        List<ConstantField> tmp = inspector.getConstants(aClass);

        constantList.addAll(tmp);
        for (ConstantField constant : tmp)
            constants.put(constant.name, constant);
    }

    public void loadSettings(Properties props) {
        for (ConstantField constant : constantList) {
            constant.loadFromProperties(props);
        }
    }

    public void loadSettings(String file) throws IOException {
        Path path = Paths.get(file);

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Properties props = new Properties();
            props.load(reader);
            loadSettings(props);
        }
    }

    public void saveSettings(Properties props) {
        for (ConstantField constant : constantList) {
            constant.saveToProperties(props);
        }
    }

    public void saveSettings(String file) throws IOException {
        Path path = Paths.get(file);

        try (BufferedWriter reader = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Properties props = new Properties();
            saveSettings(props);
            props.store(reader, "Saved by LiveConstantTweaker");
        }
    }

    public void set(String name, int i) {
        ConstantField constant = constants.get(name);
        constant.set(i);
    }
}
