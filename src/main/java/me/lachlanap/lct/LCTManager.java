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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import me.lachlanap.lct.data.ClassInspector;
import me.lachlanap.lct.data.ConstantField;
import me.lachlanap.lct.data.ConstantFieldFactory;
import me.lachlanap.lct.data.IntConstantField;

/**
 * The core class for the Live Constant Tweaker. For most intents and purposes, this is what
 * you should be using.
 *
 * This class has no singleton data, so can have multiple instances if needed.
 * @author Lachlan Phillips
 */
public class LCTManager {

    private final Set<ConstantField> constantList;
    private final Map<String, ConstantField> constants;
    private final ClassInspector inspector;

    /**
     * Creates an LCTManager with a default ClassInspector.
     */
    public LCTManager() {
        this(new ClassInspector(new ConstantFieldFactory()));
    }

    /**
     * Creates an LCTManager with the specified ConstantFieldFactory being
     * fed to a default ClassInspector.
     * @param factory the factory for the ClassInspector
     */
    public LCTManager(ConstantFieldFactory factory) {
        this(new ClassInspector(factory));
    }

    /**
     * Creates an LCTManager with a custom ClassInspector.
     * @param inspector
     */
    public LCTManager(ClassInspector inspector) {
        constantList = new HashSet<>();
        constants = new HashMap<>();
        this.inspector = inspector;
    }

    /**
     * Manually add a field to the list of constants.
     * @param field the field
     */
    public void addConstant(ConstantField field) {
        constantList.add(field);
        constants.put(field.name, field);
    }

    /**
     * Get all the fields associated with this LCTManager.
     * @return the fields
     */
    public List<ConstantField> getFields() {
        return new ArrayList<>(constantList);
    }

    /**
     * Extracts the constant fields from the specified class using the ClassInspector.
     */
    public void register(Class<?> aClass) {
        List<ConstantField> tmp = inspector.getConstants(aClass);

        constantList.addAll(tmp);
        for (ConstantField constant : tmp)
            constants.put(constant.name, constant);
    }

    /**
     * Loads constant settings from a Properties.
     */
    public void loadSettings(Properties props) {
        for (ConstantField constant : constantList) {
            constant.loadFromProperties(props);
        }
    }

    /**
     * Loads constant settings from a file in Properties format.
     * @param file the path to the file to read from
     * @throws IOException when file reading fails
     */
    public void loadSettings(String file) throws IOException {
        Path path = Paths.get(file);

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Properties props = new Properties();
            props.load(reader);
            loadSettings(props);
        }
    }

    /**
     * Saves constant settings to a Properties.
     */
    public void saveSettings(Properties props) {
        for (ConstantField constant : constantList) {
            constant.saveToProperties(props);
        }
    }

    /**
     * Saves constant settings to a file in Properties format.
     * @param file the path to the file to write tp
     * @throws IOException if writing fails
     */
    public void saveSettings(String file) throws IOException {
        Path path = Paths.get(file);

        try (BufferedWriter reader = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Properties props = new Properties();
            saveSettings(props);
            props.store(reader, "Saved by LiveConstantTweaker");
        }
    }

    /**
     * Set an integer field with name given.
     * @deprecated design flaw - cannot support all types of fields like this. Get the field from the list of fields and set it from there.
     */
    @Deprecated
    public void set(String name, int i) {
        ConstantField constant = constants.get(name);
        ((IntConstantField) constant).set(i);
    }
}
