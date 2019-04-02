package com.yuehai.android.presenter;

import android.widget.RadioGroup;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.yuehai.android.model.DemoModel;
import com.yuehai.android.R;
import com.yuehai.android.contract.ChartDemoContract;

import java.util.List;

import library.base.BasePresenter;

/**
 * 图表的Demo P
 * Created by zhaoyuehai 2019/4/2
 */
public class ChartDemoPresenter extends BasePresenter<ChartDemoContract.View> implements ChartDemoContract.Presenter {

    private ChartDemoContract.Model model;

    public ChartDemoPresenter(ChartDemoContract.View view) {
        super(view);
        model = new DemoModel();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        loadData(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.demo_rb1:
                loadData(true);
                break;
            case R.id.demo_rb2:
                loadData(false);
                break;
        }
    }

    /**
     * 设置数据
     */
    private void loadData(boolean isMonth) {
        if (isViewAttached()) {
            List<String> xAxis = isMonth ? model.getMonthXData() : model.getYearXData();
            List<Float> yData = isMonth ? model.getMonthYData() : model.getYearYData();
            getView().setChartData(xAxis, yData, new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if (isMonth && Integer.valueOf(xAxis.get((int) value % xAxis.size())) % 5 != 0) {
                        //处理月份过多的情况--> %5
                        return "";
                    } else {
                        return xAxis.get((int) value % xAxis.size());
                    }
                }
            });
        }
    }
}
