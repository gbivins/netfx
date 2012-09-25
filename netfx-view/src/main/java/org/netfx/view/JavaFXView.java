/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javax.swing.SwingUtilities;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.WeakListeners;

/**
 *
 * @author gbivins
 */
public class JavaFXView<T extends Node> implements VetoableChangeListener, PropertyChangeListener {

    private ObservableList<T> dataItems = null;
    private ListView<T> listView = null;
    private ExplorerManager manager = null;

    public JavaFXView(ExplorerManager em) {
        manager = em;

        dataItems = FXCollections.observableArrayList();
        listView = new ListView<>(dataItems);
        listView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> list) {
                return new NodeCell();
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> node, T oldValue, T newValue) {

                if (newValue == null) {
                    //SwingUtilities.invokeLater(new Runnable() {
                    //  @Override
                    //public void run() {
                    try {
                        selectionChanged(new Node[]{}, manager);
                    } catch (PropertyVetoException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    // }
                    // });


                } else {

                    final T newNode = newValue;
                    //SwingUtilities.invokeLater(new Runnable() {
                    //@Override
                    //public void run() {
                    try {
                        selectionChanged(new Node[]{newNode}, manager);
                    } catch (PropertyVetoException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    //  }
                    //});

                }
            }
        });
        if (manager != null) {
            manager.addVetoableChangeListener(WeakListeners.vetoableChange(JavaFXView.this, manager));
            manager.addPropertyChangeListener(WeakListeners.propertyChange(JavaFXView.this, manager));
        }


    }

    public javafx.scene.Parent getViewNode() {
        return listView;
    }

    protected void initializeListViewData() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataItems.clear();
                List<T> asList = (List<T>) java.util.Arrays.asList(manager.getRootContext().getChildren().getNodes());
                dataItems.addAll(asList);
                updateListViewSelection();
            }
        });
    }

    private void updateListViewSelection() {
        final Node[] sel = manager.getSelectedNodes();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listView.getSelectionModel().clearSelection();

                for (int i = 0; i < sel.length; i++) {
                    listView.getSelectionModel().select(i);
                }
            }
        });
    }

    class NodeCell extends ListCell<T> {

        @Override
        protected void updateItem(T node, boolean isCellEmpty) {
            super.updateItem(node, isCellEmpty);
            if (isCellEmpty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(node == null ? "null" : node.getDisplayName());
                //TODO: how to convert Node icon to image view????????
                setGraphic(null);

            }

        }
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
            Node[] newNodes = (Node[]) evt.getNewValue();

            if (!selectionAccept(newNodes)) {
                throw new PropertyVetoException("", evt); // NOI18N
            }
        }

    }

    protected void selectionChanged(Node[] nodes, ExplorerManager em)
            throws PropertyVetoException {
        em.setSelectedNodes(nodes);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
            //updateListViewSelection();

            return;
        }

        if (ExplorerManager.PROP_ROOT_CONTEXT.equals(evt.getPropertyName())) {
            initializeListViewData();

            //System.out.println("Children: " + java.util.Arrays.asList (list.getValues ())); // NOI18N
        }
    }

    protected boolean selectionAccept(Node[] nodes) {
        // if the selection is just the root context, confirm the selection
        if ((nodes.length == 1) && manager.getExploredContext().equals(nodes[0])) {
            // XXX shouldn't this be exploredContext?
            return true;
        }


        // we do not allow selection in other than the exploredContext
        for (int i = 0; i < nodes.length; i++) {


            if (!dataItems.contains(nodes[i])) {
                return false;
            }
        }

        return true;
    }
}
