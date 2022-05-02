package com.xxmassdeveloper.mpchartexample.custom;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by philipp on 02/06/16.
 */
public class DateOfMonthAxisValueFormatter implements IAxisValueFormatter {

    private final BarLineChartBase<?> chart;

    public DateOfMonthAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int days = (int) value;
        return String.valueOf(days);
    }
}
