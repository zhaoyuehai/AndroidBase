package com.yuehai.android.contract;

import android.widget.CompoundButton;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

import androidx.annotation.NonNull;
import library.base.IBasePresenter;
import library.base.IBaseView;

/**
 * 图表的Demo
 * Created by zhaoyuehai 2019/4/2
 */
public interface ChartDemoContract {
    interface View extends IBaseView {
        /**
         * 显示图表数据
         */
        void setChartData(List<String> xAxisData, ValueFormatter formatter, List<Float> yData);

        /**
         * 是否显示柱状图
         */
        void setBarDataEnable(boolean enable);

        /**
         * 是否显示折线图
         */
        void setLineDataEnable(boolean enable);
    }

    interface Presenter extends IBasePresenter, CompoundButton.OnCheckedChangeListener {

    }

    interface Model {

        /**
         * 获取X周坐标集合（月数据）
         */
        @NonNull
        List<String> getMonthXData();

        /**
         * 获取对应的Y轴数据集合（月数据）
         */
        @NonNull
        List<Float> getMonthYData();

        /**
         * 获取X周坐标集合（年数据）
         */
        @NonNull
        List<String> getYearXData();

        /**
         * 获取对应的Y轴数据集合（年数据）
         */
        @NonNull
        List<Float> getYearYData();
    }
}
