/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.view;

import java.awt.LayoutManager2;
import javafx.scene.Scene;
import javax.swing.ActionMap;
import javax.swing.text.DefaultEditorKit;
import org.netfx.components.AbstractJavaFXTopComponent;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author gbivins
 */
abstract public class JavaFXExplorer extends AbstractJavaFXTopComponent implements ExplorerManager.Provider, Lookup.Provider {

    private ExplorerManager manager = null;
    private JavaFXView view = null;
    public JavaFXExplorer(LayoutManager2 layout, String name, String tooltip) {
        super(layout, name, tooltip);
        
        this.manager = new ExplorerManager();
        
        ActionMap map = this.getActionMap();
        map.put(DefaultEditorKit.copyAction, ExplorerUtils.actionCopy(manager));
        map.put(DefaultEditorKit.cutAction, ExplorerUtils.actionCut(manager));
        map.put(DefaultEditorKit.pasteAction, ExplorerUtils.actionPaste(manager));
        map.put("delete", ExplorerUtils.actionDelete(manager, true)); // or false

        
        // following line tells the top component which lookup should be associated with it
        associateLookup(ExplorerUtils.createLookup(manager, map));
    }
   abstract protected Node getExploredContent();
    
    @Override
    protected Scene createJavaFXScene() {
        
        view = new JavaFXView(manager);
        return new Scene(view.getViewNode());
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }
    // It is good idea to switch all listeners on and off when the
    // component is shown or hidden. In the case of TopComponent use:

    @Override
    protected void componentActivated() {
        ExplorerUtils.activateActions(manager, true);
        
    }
    

    @Override
    protected void componentDeactivated() {
        ExplorerUtils.activateActions(manager, false);
    }
    @Override 
    protected void componentOpened()
    {
        manager.setRootContext(getExploredContent());
    }
    @Override
    protected void componentClosed()
    {
        manager.setRootContext(Node.EMPTY);
    }
}
