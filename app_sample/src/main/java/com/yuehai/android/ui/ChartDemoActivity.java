package com.yuehai.android.ui;

import android.graphics.Color;
import android.widget.RadioButton;
import android.widget.Switch;

import com.yuehai.android.R;
import com.yuehai.android.contract.ChartDemoContract;
import com.yuehai.android.presenter.ChartDemoPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import library.base.BaseMvpActivity;
import library.widget.chart.model.Axis;
import library.widget.chart.model.Line;
import library.widget.chart.model.LineChartData;
import library.widget.chart.model.PointValue;
import library.widget.chart.model.ValueShape;
import library.widget.chart.model.Viewport;
import library.widget.chart.view.LineChartView;

/**
 * 图表的Demo V
 * Created by zhaoyuehai 2019/3/22
 * <p>
 * 已经注释掉了MPAndroidChart实现方式
 * 如果需要，打开注释掉的代码，并在gradle中添加依赖 implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
 * <p>
 */
public class ChartDemoActivity extends BaseMvpActivity<ChartDemoContract.Presenter> implements ChartDemoContract.View {

    //    @BindView(R.id.chart_demo_combined)
//    CombinedChart mCombinedChart;
    @BindView(R.id.chart_demo_line_chart)
    LineChartView mLineChart;
    @BindView(R.id.chart_demo_rb1)
    RadioButton radioButton1;
    @BindView(R.id.chart_demo_rb2)
    RadioButton radioButton2;
    @BindView(R.id.chart_demo_rb3)
    RadioButton radioButton3;
    @BindView(R.id.chart_demo_rb4)
    RadioButton radioButton4;
    @BindView(R.id.chart_demo_rb5)
    RadioButton radioButton5;
    //    @BindView(R.id.chart_demo_cb1)
//    CheckBox checkBox1;
//    @BindView(R.id.chart_demo_cb2)
//    CheckBox checkBox2;
    @BindView(R.id.chart_demo_sw)
    Switch mSwitch;
    //    private boolean mBarDataEnable = true;
//    private boolean mLineDataEnable = false;
    private int mLineChartTpye = 0;//0:折线图 1：曲线图 2：方线图
    private boolean mLineChartContinuous = true;//Y值为0是否继续绘制

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
//        initCombinedChart();
        initLineChart();
        radioButton1.setOnCheckedChangeListener(presenter);
        radioButton2.setOnCheckedChangeListener(presenter);
        radioButton3.setOnCheckedChangeListener(presenter);
        radioButton4.setOnCheckedChangeListener(presenter);
        radioButton5.setOnCheckedChangeListener(presenter);
//        checkBox1.setOnCheckedChangeListener(presenter);
//        checkBox2.setOnCheckedChangeListener(presenter);
        mSwitch.setOnCheckedChangeListener(presenter);
    }

    /**
     * 初始化LineChartView控件
     */
    private void initLineChart() {
        //设置行为属性，缩放、滑动、平移
        mLineChart.setInteractive(false);//设置图表是可以交互的（拖拽，缩放等效果的前提）
//        mLineChart.setZoomType(ZoomType.HORIZONTAL);//设置缩放方向
//        mLineChart.setMaxZoom((float) 3);//最大放大比例
    }

    @Override
    public void setLineChartType(int type) {
        mLineChartTpye = type;
    }

    @Override
    public void setLineChartContinuous(boolean continuous) {
        mLineChartContinuous = continuous;
    }

    @Override
    public void setLineChartData(List<Float> yData) {
        //转换为点
        List<PointValue> pointValues = new ArrayList<>();
        for (int x = 0; x < yData.size(); x++) {
            pointValues.add(new PointValue(x + 1, yData.get(x)));
        }

        LineChartData data = new LineChartData();

        Line line = new Line(pointValues).setColor(Color.parseColor("#FFCD41"));
        line.setShape(ValueShape.CIRCLE);    //折线图上每个数据点的形状，这里是圆形（有三种 ：ValueShape.SQUARE：方形  ValueShape.CIRCLE：圆形  ValueShape.DIAMOND：菱形）
        line.setPointRadius(4);
        line.setStrokeWidth(2);
        line.setContinuous(mLineChartContinuous);//当Y为0时跳过不画
        switch (mLineChartTpye) {
            case 0: //0:折线图 1：曲线图 2：方线图
                line.setCubic(false);//折线,如果setSquare会失效默认false
                break;
            case 1:
                line.setCubic(true);//平滑曲线,如果setSquare会失效默认false
                break;
            case 2:
                line.setSquare(true);//画方线条(就是那种拐来拐去直来直去的) ,setCubic会失效默认true
                break;
        }
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        data.setLines(lines);

        //坐标文字颜色
        int color = getResources().getColor(R.color.text_black);
        //坐标轴 -x轴
        Axis axisX = new Axis();//
        //axisX.setName("表格XXX");//表格名称
        axisX.setHasTiltedLabels(false);//X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(color);//设置字体颜色
        axisX.setTextSize(8);//设置字体大小
//        data.setAxisXTop(axisX);//x 轴在顶部
        data.setAxisXBottom(axisX);//x 轴在底部

        //坐标轴 -y轴
        Axis axisY = new Axis();//Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        axisY.setName("发电量（万kWh）");//y轴标注
        axisY.setTextColor(color);//设置字体颜色
        axisY.setTextSize(8);
        data.setAxisYLeft(axisY);//Y轴设置在左边
        data.setAxisYRight(axisY);//y轴设置在右边

        mLineChart.setLineChartData(data);
        //viewport必须设置在setLineChartData后面，设置一个当前viewport，再设置一个maxviewport，
        //就可以实现滚动，高度要设置数据的上下限
        //设置是否允许在动画进行中或设置完表格数据后，自动计算viewport的大小。如果禁止，则需要可以手动设置。
        //lineChart.setViewportCalculationEnabled(true);
        final Viewport v = new Viewport(mLineChart.getMaximumViewport());
        v.bottom = 0;
        //Y轴最大值为 加上20、防止显示不全
        float maxPoint = Collections.max(yData);
        v.top = maxPoint + (pointValues.size() > 12 ? 5 : 50);
        mLineChart.setMaximumViewport(v);
        //这2个属性的设置一定要在mLineChart.setMaximumViewport(v)方法之后,不然显示的坐标数据是不能左右滑动查看更多数据的
        v.left = 1;
        v.right = pointValues.size();//X轴显示最大值
        mLineChart.setCurrentViewport(v);
    }

//    /**
//     * 初始化CombinedChart控件
//     * 属性介绍：https://blog.csdn.net/baidu_31956557/article/details/80975541
//     */
//    private void initCombinedChart() {
//        mCombinedChart.setScaleEnabled(false); // 两个轴上的缩放,X,Y分别默认为true
//        mCombinedChart.setDrawBorders(false); // 显示边界（黑方框）
//        mCombinedChart.getDescription().setEnabled(false);//不显示描述内容
//        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
//                CombinedChart.DrawOrder.BAR,
//                CombinedChart.DrawOrder.LINE
//        });
//        mCombinedChart.setBackgroundColor(Color.WHITE);
//        mCombinedChart.setDrawGridBackground(false);
//        mCombinedChart.setDrawBarShadow(false);
//        mCombinedChart.setHighlightFullBarEnabled(false);
//        //图例说明
//        Legend legend = mCombinedChart.getLegend();
//        legend.setWordWrapEnabled(true);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        legend.setDrawInside(false);
//        legend.setEnabled(false);
//        //Y轴设置
//        YAxis rightAxis = mCombinedChart.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f);
//        YAxis leftAxis = mCombinedChart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f);
//        //设置X轴坐标值
//        XAxis xAxis = mCombinedChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴在底部
//        xAxis.setGranularity(1f);
//        xAxis.setDrawLabels(true);
//        xAxis.setDrawGridLines(false);
//    }
//
//    @Override
//    public void setBarDataEnable(boolean enable) {
//        mBarDataEnable = enable;
//    }
//
//    @Override
//    public void setLineDataEnable(boolean enable) {
//        mLineDataEnable = enable;
//    }
//
//    @Override
//    public void setCombinedChartData(int xSize, ValueFormatter formatter, List<Float> yData) {
//        mCombinedChart.clear();//先清空
//        mCombinedChart.notifyDataSetChanged();
//        //设置X轴
//        mCombinedChart.getXAxis().setLabelCount(xSize - 1, false);
//        mCombinedChart.getXAxis().setValueFormatter(formatter);
//        //以下是为了解决 柱状图 左右两边只显示了一半的问题
//        mCombinedChart.getXAxis().setAxisMinimum(-0.5f);
//        mCombinedChart.getXAxis().setAxisMaximum((float) (xSize - 0.5));
//        //设置MarkerView
//        MyMarkerView mv = new MyMarkerView(this);
//        mv.setData(yData);
//        mv.setChartView(mCombinedChart);
//        mCombinedChart.setMarker(mv);
//        CombinedData combinedData = new CombinedData();
//        if (mBarDataEnable) {
//            //用Y轴数据画柱状图
//            combinedData.setData(getCombinedBarData(yData));
//        } else {
//            combinedData.setData(new BarData());//因为重复设值，需要设个空值，防止null
//        }
//        if (mLineDataEnable) {
//            //用Y轴数据画曲线
//            combinedData.setData(getCombinedLineData(yData));
//        } else {
//            combinedData.setData(new LineData());//因为重复设值，需要设个空值，防止null
//        }
//        mCombinedChart.setData(combinedData);
//        mCombinedChart.animateX(500); // 立即执行的动画,x轴
//    }
//
//    /**
//     * 曲线图数据
//     */
//    private LineData getCombinedLineData(List<Float> yData) {
//        LineData lineData = new LineData();
//        ArrayList<Entry> yValue = new ArrayList<>();
//        for (int i = 0; i < yData.size(); i++) {
//            yValue.add(new Entry(i, yData.get(i)));
//        }
//        LineDataSet dataSet = new LineDataSet(yValue, "日曝幅值（MJ/m2）");
//        int color = getResources().getColor(R.color.red);
//        dataSet.setColor(color);
//        dataSet.setCircleColor(color);
//        dataSet.setValueTextColor(color);
//        //显示值
//        dataSet.setDrawValues(false);
//        dataSet.setValueTextSize(10f);
//        dataSet.setCircleRadius(1f);
//        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//        lineData.addDataSet(dataSet);
//        return lineData;
//    }
//
//    /**
//     * 柱状图数据
//     */
//    private BarData getCombinedBarData(List<Float> yData) {
//        BarData barData = new BarData();
//        ArrayList<BarEntry> yValues = new ArrayList<>();
//        for (int i = 0; i < yData.size(); i++) {
//            yValues.add(new BarEntry(i, yData.get(i)));
//        }
//        int color = getResources().getColor(R.color.blue);
//        BarDataSet barDataSet = new BarDataSet(yValues, "累计发电量（万kwh）");
//        barDataSet.setColor(color);
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);
//        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
//        barDataSet.setHighLightColor(getResources().getColor(R.color.blue_dark));
//        barData.addDataSet(barDataSet);
//        barData.setDrawValues(false);
//        return barData;
//    }
}
