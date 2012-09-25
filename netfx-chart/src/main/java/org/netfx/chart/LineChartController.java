/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.chart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author gbivins
 * TODO: this is specific to the chart implementation
 */
public class LineChartController {

    @FXML
    private LineChart<Double, Double> lineChart;
    ObservableList<XYChart.Series<Double, Double>> lineChartData;

    @FXML
    protected void initialize() {
        lineChartData = FXCollections.observableArrayList();
        //something needs to define the series data...
        //When the series changes it's data, it needs a connection to
        //a series in the LineChart.Series
        LineChart.Series<Double, Double> series1 = new LineChart.Series<>();
        series1.setName("Series 1");

        lineChartData.add(series1);
        lineChart.setData(lineChartData);
        lineChart.createSymbolsProperty();
        lineChart.setTitle("Test Line Chart");
    }
    public ObservableList<XYChart.Series<Double, Double>> getLineChartData()
    {
        return lineChartData;
    }
}
