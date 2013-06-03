/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.lachlanap.lct.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import me.lachlanap.lct.LCTManager;

/**
 *
 * @author lachlan
 */
public class LCTEditor extends JPanel {

    private final LCTManager manager;
    private final JPanel pane;

    public LCTEditor(LCTManager manager) {
        this.manager = manager;

        pane = new JPanel();
        pane.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pane,
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }

    public LCTManager getManager() {
        return manager;
    }

    public void build() {
        pane.removeAll();
        
        // TODO: write this
    }
}
