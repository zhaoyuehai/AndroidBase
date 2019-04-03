package com.yuehai.android.ui;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.yuehai.android.R;
import com.yuehai.android.contract.ChartDemoContract;
import com.yuehai.android.presenter.ChartDemoPresenter;
import com.yuehai.android.widget.MyMarkerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import library.base.BaseMvpActivity;

/**
 * 图表的Demo V
 * Created by zhaoyuehai 2019/3/22
 */
public class ChartDemoActivity extends BaseMvpActivity<ChartDemoContract.Presenter> implements ChartDemoContract.View {

    @BindView(R.id.chart_demo_combined)
    CombinedChart mCombinedChart;
    @BindView(R.id.chart_demo_rb1)
    RadioButton radioButton1;
    @BindView(R.id.chart_demo_rb2)
    RadioButton radioButton2;
    @BindView(R.id.chart_demo_cb1)
    CheckBox checkBox1;
    @BindView(R.id.chart_demo_cb2)
    CheckBox checkBox2;
    private boolean mBarDataEnable = true;
    private boolean mLineDataEnable = false;

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_chart_demo;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.chart_demo;
    }

    @Override
    protected ChartDemoContract.Presenter createPresenter() {
        return new ChartDemoPresenter(this);
    }

    @Override
    protected void initView() {
        initChart();
        radioButton1.setOnCheckedChangeListener(presenter);
        radioButton2.setOnCheckedChangeListener(presenter);
        checkBox1.setOnCheckedChangeListener(presenter);
        checkBox2.setOnCheckedChangeListener(presenter);
    }

    /**
     * 初始化图标控件
     * 属性介绍：https://blog.csdn.net/baidu_31956557/article/details/80975541
     */
    private void initChart() {
        mCombinedChart.setScaleEnabled(false); // 两个轴上的缩放,X,Y分别默认为true
        mCombinedChart.setDrawBorders(false); // 显示边界（黑方框）
        mCombinedChart.getDescription().setEnabled(false);//不显示描述内容
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE
        });
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);
        //图例说明
        Legend legend = mCombinedChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(false);
        //Y轴设置
        YAxis rightAxis = mCombinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        YAxis leftAxis = mCombinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        //设置X轴坐标值
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴在底部
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
    }

    @Override
    public void setBarDataEnable(boolean enable) {
        mBarDataEnable = enable;
    }

    @Override
    public void setLineDataEnable(boolean enable) {
        mLineDataEnable = enable;
    }

    @Override
    public void setChartData(List<String> xAxisData, ValueFormatter formatter, List<Float> yData) {
        mCombinedChart.clear();//先清空
        mCombinedChart.notifyDataSetChanged();
        //设置X轴
        mCombinedChart.getXAxis().setLabelCount(xAxisData.size() - 1, false);
        mCombinedChart.getXAxis().setValueFormatter(formatter);
        //以下是为了解决 柱状图 左右两边只显示了一半的问题
        mCombinedChart.getXAxis().setAxisMinimum(-0.5f);
        mCombinedChart.getXAxis().setAxisMaximum((float) (xAxisData.size() - 0.5));
        //设置MarkerView
        MyMarkerView mv = new MyMarkerView(this);
        mv.setData(yData);
        mv.setChartView(mCombinedChart);
        mCombinedChart.setMarker(mv);
        CombinedData combinedData = new CombinedData();
        if (mBarDataEnable) {
            //用Y轴数据画柱状图
            combinedData.setData(getCombinedBarData(yData));
        } else {
            combinedData.setData(new BarData());//因为重复设值，需要设个空值，防止null
        }
        if (mLineDataEnable) {
            //用Y轴数据画曲线
            combinedData.setData(getCombinedLineData(yData));
        } else {
            combinedData.setData(new LineData());//因为重复设值，需要设个空值，防止null
        }
        mCombinedChart.setData(combinedData);
        mCombinedChart.animateX(500); // 立即执行的动画,x轴
    }

    /**
     * 曲线图数据
     */
    private LineData getCombinedLineData(List<Float> yData) {
        LineData lineData = new LineData();
        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < yData.size(); i++) {
            yValue.add(new Entry(i, yData.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(yValue, "日曝幅值（MJ/m2）");
        int color = getResources().getColor(R.color.red);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setValueTextColor(color);
        //显示值
        dataSet.setDrawValues(false);
        dataSet.setValueTextSize(10f);
        dataSet.setCircleRadius(1f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(dataSet);
        return lineData;
    }

    /**
     * 柱状图数据
     */
    private BarData getCombinedBarData(List<Float> yData) {
        BarData barData = new BarData();
        ArrayList<BarEntry> yValues = new ArrayList<>();
        for (int i = 0; i < yData.size(); i++) {
            yValues.add(new BarEntry(i, yData.get(i)));
        }
        int color = getResources().getColor(R.color.blue);
        BarDataSet barDataSet = new BarDataSet(yValues, "累计发电量（万kwh）");
        barDataSet.setColor(color);
        barDataSet.setValueTextSize(10f);
        barDataSet.setValueTextColor(color);
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setHighLightColor(getResources().getColor(R.color.blue_dark));
        barData.addDataSet(barDataSet);
        barData.setDrawValues(false);
        return barData;
    }
}
