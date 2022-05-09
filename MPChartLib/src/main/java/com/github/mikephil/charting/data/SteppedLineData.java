
package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.List;

/**
 * Data object that encapsulates all data associated with a LineChart.
 * 
 * @author Philipp Jahoda
 */
public class SteppedLineData extends BarLineScatterCandleBubbleData<ILineDataSet> {

    public SteppedLineData() {
        super();
    }

    public SteppedLineData(ILineDataSet... dataSets) {
        super(dataSets);
    }

    public SteppedLineData(List<ILineDataSet> dataSets) {
        super(dataSets);
    }
}
