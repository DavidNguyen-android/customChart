package com.github.mikephil.charting.charts.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.SteppedLineData;
import com.github.mikephil.charting.interfaces.dataprovider.SteppedLineDataProvider;
import com.github.mikephil.charting.renderer.custom.SteppedLineChartRenderer;

/**
 * Chart that draws lines, surfaces, circles, ...
 *
 * @author Philipp Jahoda
 */
public class SteppedLineChart extends SteppedBarLineChartBase<SteppedLineData> implements SteppedLineDataProvider {

    public SteppedLineChart(Context context) {
        super(context);
    }

    public SteppedLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SteppedLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mRenderer = new SteppedLineChartRenderer(this, mAnimator, mViewPortHandler);
    }

    @Override
    public SteppedLineData getSteppedLineData() {
        return mData;
    }

    @Override
    protected void onDetachedFromWindow() {
        // releases the bitmap in the renderer to avoid oom error
        if (mRenderer != null && mRenderer instanceof SteppedLineChartRenderer) {
            ((SteppedLineChartRenderer) mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

}
