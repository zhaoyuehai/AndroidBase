package com.yuehai.android.presenter;

import android.widget.CompoundButton;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.yuehai.android.R;
import com.yuehai.android.contract.ChartDemoContract;
import com.yuehai.android.model.ChartDemoModel;

import java.util.List;

import library.base.BasePresenter;

/**
 * 图表的Demo P
 * Created by zhaoyuehai 2019/4/2
 */
public class ChartDemoPresenter extends BasePresenter<ChartDemoContract.View> implements ChartDemoContract.Presenter {

    private ChartDemoContract.Model model;
    private boolean isMonth = true;

    public ChartDemoPresenter(ChartDemoContract.View view) {
        super(view);
        model = new ChartDemoModel();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        loadData();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.chart_demo_rb1:
                //选中月数据
                if (isChecked) {
                    isMonth = true;
                    loadData();
                }
                break;
            case R.id.chart_demo_rb2:
                //选中年数据
                if (isChecked) {
                    isMonth = false;
                    loadData();
                }
                break;
            case R.id.chart_demo_cb1:
                if (isViewAttached()) getView().setBarDataEnable(isChecked);
                loadData();
                break;
            case R.id.chart_demo_cb2:
                if (isViewAttached()) getView().setLineDataEnable(isChecked);
                loadData();
                break;
        }
    }

    /**
     * 设置数据
     */
    private void loadData() {
        if (isViewAttached()) {
            List<String> xAxis = isMonth ? model.getMonthXData() : model.getYearXData();
            List<Float> yData = isMonth ? model.getMonthYData() : model.getYearYData();
            getView().setChartData(xAxis, new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (isMonth) {
                        if (Integer.valueOf(xAxis.get((int) value % xAxis.size())) % 5 != 0) {
                            //处理月份过多的情况--> %5
                            return "";
                        } else {
                            return xAxis.get((int) value % xAxis.size());
                        }
                    } else {
                        return xAxis.get((int) value % xAxis.size());
                    }
                }
            }, yData);
        }
    }
}
