package com.github.mikephil.charting.charts.custom;

public class HorizontalBarChartData {
    final String valueRate;
    final  String valueTime;

    public HorizontalBarChartData(String valueRate, String valueTime) {
        this.valueRate = valueRate;
        this.valueTime = valueTime;
    }

    public String getValueRate() {
        return valueRate;
    }

    public String getValueTime() {
        return valueTime;
    }
}
