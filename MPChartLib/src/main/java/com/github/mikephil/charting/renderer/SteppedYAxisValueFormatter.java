package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class SteppedYAxisValueFormatter  implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if((int)value == 0) return "Good 1";
        if((int) value == 1) return "Good 2";
        if((int) value == 2) return "Good 3";
        if((int) value == 3) return "Good 4";
        return "";
    }
}
