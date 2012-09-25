/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.chart;

import org.netfx.data.InjectableJavaFXUpdaterSerivce;
import org.netfx.data.annotations.InjectableJavaFXService;

/**
 *
 * @author gbivins
 */
@InjectableJavaFXService(injectInto = String.class)
public class InjectableLineChartUpdater implements InjectableJavaFXUpdaterSerivce<String, LineChartUpdaterService> {

    @Override
    public LineChartUpdaterService convert(String t) {
        //TODO: this is where the well would be passed in to
        //initialize the code to get the specific series data from the well
        //for now just create a new LineChartUpdaterService
        return new LineChartUpdaterService(t);
    }

    @Override
    public Class<? extends LineChartUpdaterService> type(String t) {
        return LineChartUpdaterService.class;
    }

    @Override
    public String id(String t) {
        return getClass().getName() + t;
    }

    @Override
    public String displayName(String t) {
        return t;
    }
    
}
