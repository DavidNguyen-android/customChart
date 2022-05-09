package com.xxmassdeveloper.mpchartexample;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.SteppedLineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.SteppedLineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.custom.SteppedYAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;
import java.util.Random;

public class CombinedChartActivity extends DemoBase {

    private final int count = 12;
    private CombinedChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_combined);

        setTitle("CombinedChartActivity");

        chart = findViewById(R.id.chart1);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.TRANSPARENT);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        // draw bars behind lines
        chart.setDrawOrder(new DrawOrder[]{
                DrawOrder.LINE_STEPPED,
                DrawOrder.LINE,
        });

//        Legend l = chart.getLegend();
//        l.setWordWrapEnabled(true);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        // disable grid lines
        rightAxis.setDrawGridLines(false);
        rightAxis.setValueFormatter(new SteppedYAxisValueFormatter());
        rightAxis.setLabelCount(4);
        // axis range
        rightAxis.setAxisMaximum(3.2f);
        rightAxis.setAxisMinimum(-0.2f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value % months.length];
            }
        });

        CombinedData data = new CombinedData();

        data.setData(generateLineData());
        data.setData(generateSteppedLineData());
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());
        data.setValueTypeface(tfLight);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        chart.setData(data);
        chart.invalidate();
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 0; index < count; index++)
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));

        int[] colors = new int[entries.size()];
        Integer maxValueIndex = null, minValueIndex = null;
        Float maxValue = null, minValue = null;
        for (int index = 0; index < entries.size(); index++) {
            colors[index] = Color.TRANSPARENT;
            Entry e = entries.get(index);
            if (maxValue == null || maxValue < e.getY()) {
                maxValue = e.getY();
                maxValueIndex = index;
            }
            if (minValue == null || minValue > e.getY()) {
                minValue = e.getY();
                minValueIndex = index;
            }
        }
        if (maxValueIndex != null) {
            colors[maxValueIndex] = Color.RED;
        }
        if (minValueIndex != null) {
            colors[minValueIndex] = Color.YELLOW;
        }

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setDrawCircleHole(false);
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColors(colors);
        set.setCircleRadius(5f);
        set.setDrawCircles(true);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private SteppedLineData generateSteppedLineData() {

        SteppedLineData d = new SteppedLineData();
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) (new Random().nextInt(4));
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set = new LineDataSet(values, "DataSet 1");
        set.setDrawIcons(false);

        // draw dashed line
        set.enableDashedLine(10f, 5f, 0f);

        // black lines and points
        set.setDrawValues(false);
        set.setDrawCircles(false);
//        int[] colors = {
//                ContextCompat.getColor(getApplicationContext(), R.color.stepped_chart_color_1),
//                ContextCompat.getColor(getApplicationContext(), R.color.stepped_chart_color_2),
//                ContextCompat.getColor(getApplicationContext(), R.color.stepped_chart_color_3),
//                ContextCompat.getColor(getApplicationContext(), R.color.stepped_chart_color_4),
//        };
//        final LinearGradient gradient = new LinearGradient(
//                0f, chart.getHeight(), 0f, 0f,
//                colors, null,
//                Shader.TileMode.REPEAT);
//        Paint paint = chart.getRenderer().getPaintRender();
//        paint.setShader(gradient);
        set.setEntries(values);

        set.setColor(Color.GRAY);
        set.setCircleColor(Color.GRAY);

        // line thickness and point size
        set.setLineWidth(1f);
        set.setCircleRadius(4f);

        // draw points as solid circles
        set.setDrawCircleHole(false);

        // customize legend entry
        set.setFormLineWidth(1f);
        set.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set.setFormSize(15.f);

        // text size of values
        set.setValueTextSize(9f);

        // draw selection line as dashed
        set.enableDashedHighlightLine(10f, 5f, 0f);

        // set the filled area
        set.setDrawFilled(true);
        set.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }

            @Override
            public float getFillLinePosition(ILineDataSet dataSet, SteppedLineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });

        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            set.setFillDrawable(drawable);
        } else {
            set.setFillColor(Color.BLACK);
        }
        set.setMode(LineDataSet.Mode.STEPPED);
        set.setDrawFilled(false);
        set.enableDashedLine(1f, 0f, 5f);
        // create a data object with the data sets
        // set data
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        d.addDataSet(set);
        return d;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.combined, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CombinedChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionToggleLineValues: {
                for (IDataSet set : chart.getData().getDataSets()) {
                    if (set instanceof LineDataSet)
                        set.setDrawValues(!set.isDrawValuesEnabled());
                }

                chart.invalidate();
                break;
            }
            case R.id.actionToggleBarValues: {
                for (IDataSet set : chart.getData().getDataSets()) {
                    if (set instanceof BarDataSet)
                        set.setDrawValues(!set.isDrawValuesEnabled());
                }

                chart.invalidate();
                break;
            }
            case R.id.actionRemoveDataSet: {
                int rnd = (int) getRandom(chart.getData().getDataSetCount(), 0);
                chart.getData().removeDataSet(chart.getData().getDataSetByIndex(rnd));
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();
                chart.invalidate();
                break;
            }
        }
        return true;
    }

    @Override
    public void saveToGallery() { /* Intentionally left empty */ }
}
