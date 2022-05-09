package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.SteppedLineData;

public interface SteppedLineDataProvider extends BarLineScatterCandleBubbleDataProvider {
    SteppedLineData getSteppedLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
