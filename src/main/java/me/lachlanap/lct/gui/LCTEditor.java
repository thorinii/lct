package me.lachlanap.lct.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import me.lachlanap.lct.LCTManager;
import me.lachlanap.lct.data.ConstantField;

/**
 *
 * @author lachlan
 */
public class LCTEditor extends JPanel {

    private final LCTManager manager;

    public LCTEditor(LCTManager manager) {
        this.manager = manager;

        setLayout(new BorderLayout());

        JPanel pane = buildPane();

        JScrollPane scrollPane = new JScrollPane(pane,
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(BorderLayout.CENTER, scrollPane);
    }

    public LCTManager getManager() {
        return manager;
    }

    private JPanel buildPane() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        Map<String, List<ConstantField>> mappedConstants = aggregateMappedConstants(manager.getFields());

        for (Map.Entry<String, List<ConstantField>> constantClass : mappedConstants.entrySet()) {
            JPanel group = new JPanel();
            group.setAlignmentX(Component.LEFT_ALIGNMENT);
            group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
            root.add(group);

            JLabel className = new JLabel(constantClass.getKey());
            className.setFont(getFont().deriveFont(Font.BOLD, 20));
            group.add(className);

            for (ConstantField constant : constantClass.getValue()) {
                ConstantEditor editor = new ConstantEditor(constant);
                group.add(editor);
            }
        }

        root.add(Box.createVerticalGlue());

        return root;
    }

    private Map<String, List<ConstantField>> aggregateMappedConstants(List<ConstantField> fields) {
        Map<String, List<ConstantField>> mappedConstants = new HashMap<String, List<ConstantField>>();

        for (ConstantField constant : fields) {
            if (mappedConstants.containsKey(constant.container.getSimpleName())) {
                List<ConstantField> list = mappedConstants.get(constant.container.getSimpleName());
                list.add(constant);
            } else {
                List<ConstantField> list = new ArrayList<ConstantField>();
                list.add(constant);
                mappedConstants.put(constant.container.getSimpleName(), list);
            }
        }

        return mappedConstants;
    }
}
