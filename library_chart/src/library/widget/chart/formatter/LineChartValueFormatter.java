package library.widget.chart.formatter;


import library.widget.chart.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
