/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.chart;

import java.awt.LayoutManager2;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.netfx.components.AbstractJavaFXTopComponent;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author gbivins
 */
abstract public class AbstractJavaFXLineChartTopComponent extends AbstractJavaFXTopComponent implements LookupListener {

    private Lookup.Result<LineChartUpdaterService> categories = null;
    private InstanceContent context = null;
    private Lookup lookup = null;
    private LineChartController controller = null;

    public AbstractJavaFXLineChartTopComponent(LayoutManager2 layout, String name, String tooltip) {
        super(layout, name, tooltip);
        context = new InstanceContent();
        lookup =  new AbstractLookup(context);
    }

    @Override
    protected Scene createJavaFXScene() {
        try {
            ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
            URL resource = cl.getResource("/org/netfx/chart/fx-linechart.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            AnchorPane pane = (AnchorPane) fxmlLoader.load(resource.openStream());
            controller = fxmlLoader.getController();
            return new Scene(pane);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        return null;
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        categories = Utilities.actionsGlobalContext().lookupResult(LineChartUpdaterService.class);
        categories.addLookupListener(AbstractJavaFXLineChartTopComponent.this);
        resultChanged(new LookupEvent(categories));

    }

    @Override
    public void componentClosed() {
        categories.removeLookupListener(AbstractJavaFXLineChartTopComponent.this);
    }

    @Override
    public void resultChanged(LookupEvent le) {
        final Lookup.Result<LineChartUpdaterService> result = (Lookup.Result<LineChartUpdaterService>) le.getSource();
        if (result.allInstances().isEmpty()) {
        } else if(controller != null){
            //            final String newTitle = result.allInstances().iterator().next();
            //            setName(newTitle);
            LineChartUpdaterService next = result.allInstances().iterator().next();
            next.updateData(0, controller.getLineChartData());
            

        }

    }
}
