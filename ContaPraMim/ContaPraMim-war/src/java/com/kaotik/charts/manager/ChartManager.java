/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.charts.manager;

import java.io.Serializable;
import org.primefaces.model.chart.ChartModel;

/**
 *
 * @author resilva
 */
public interface ChartManager extends Serializable {    
    ChartModel getModel();
}
