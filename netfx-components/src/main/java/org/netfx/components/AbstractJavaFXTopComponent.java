/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.components;

import java.awt.BorderLayout;
import java.awt.LayoutManager2;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
abstract public class AbstractJavaFXTopComponent extends TopComponent {

    private JFXPanel fxPanel = null;

    public AbstractJavaFXTopComponent(LayoutManager2 layout, String name, String tooltip) {
        setLayout(layout);
        setName(name);
        setToolTipText(tooltip);
        initAndShowGUI();

    }

    private void initAndShowGUI() {
        Platform.setImplicitExit(false);
        fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fxPanel.setScene(createJavaFXScene());

            }
        });
    }

    abstract protected Scene createJavaFXScene();
}
