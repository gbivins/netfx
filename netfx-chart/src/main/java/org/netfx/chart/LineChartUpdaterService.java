/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.chart;

import java.util.Random;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.netfx.data.JavaFXDataUpdaterService;

/**
 *
 * @author gbivins
 */
public class LineChartUpdaterService implements JavaFXDataUpdaterService<ObservableList<XYChart.Series<Double, Double> > > {

    private String seedForData = null;

    public LineChartUpdaterService(String seed) {
        seedForData = seed;
    }

    @Override
    public void updateData(int index, ObservableList<Series<Double, Double>> dataToModify) throws RuntimeException {
        final ObservableList<Data<Double, Double>> data = dataToModify.get(index).getData();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //create a some random data

                data.clear();
                //Random x = new Random(Long.valueOf(seedForData));
                Random y = new Random(seedForData.length());

                for (int i = 0; i < 100; i++) {
                    double radians = (Math.PI / seedForData.length()) * i;
                    data.add(new XYChart.Data<>(new Double(i), Math.sin(radians)));
                }
            }
        });


    }
}
