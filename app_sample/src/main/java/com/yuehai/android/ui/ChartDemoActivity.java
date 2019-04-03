package com.yuehai.android.ui;

import android.graphics.Color;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
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

    @BindView(R.id.combined_chart)
    CombinedChart mCombinedChart;
    @BindView(R.id.demo_rg)
    RadioGroup radioGroup;

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_demo;
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
        radioGroup.setOnCheckedChangeListener(presenter);
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
    public void setChartData(List<String> xAxisData, List<Float> yData, ValueFormatter formatter) {
        mCombinedChart.clear();
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
        //用Y轴数据画柱状图
        mCombinedChart.setData(getCombinedBarData(yData));
        mCombinedChart.animateX(500); // 立即执行的动画,x轴
    }

    /**
     * 柱状图数据
     *
     * @param yData Y轴数据
     */
    private CombinedData getCombinedBarData(List<Float> yData) {
        BarData barData = new BarData();
        ArrayList<BarEntry> yValues = new ArrayList<>();
        for (int i = 0; i < yData.size(); i++) {
            yValues.add(new BarEntry(i, yData.get(i)));
        }
        int color = getResources().getColor(R.color.blue);
        BarDataSet barDataSet = new BarDataSet(yValues, "累计发电量万kwh");
        barDataSet.setColor(color);
        barDataSet.setValueTextSize(10f);
        barDataSet.setValueTextColor(color);
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setHighLightColor(getResources().getColor(R.color.blue_dark));
        barData.addDataSet(barDataSet);
        barData.setDrawValues(false);
        //格式化显示数据 barDataSet.setValueFormatter 等等
        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);
        return combinedData;
    }
}
