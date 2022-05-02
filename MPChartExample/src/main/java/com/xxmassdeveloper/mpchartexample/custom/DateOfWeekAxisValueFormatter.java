package com.xxmassdeveloper.mpchartexample.custom;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by philipp on 02/06/16.
 */
public class DateOfWeekAxisValueFormatter implements IAxisValueFormatter {

    private final BarLineChartBase<?> chart;

    public DateOfWeekAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int days = (int) value;
        if (days == 1) return "Thứ 2";
        if (days == 2) return "Thứ 3";
        if (days == 3) return "Thứ 4";
        if (days == 4) return "Thứ 5";
        if (days == 5) return "Thứ 6";
        if (days == 6) return "Thứ 7";
        if (days == 7) return "CN";
        return String.valueOf(days);
    }
}
