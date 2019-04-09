package com.yuehai.android.contract;

import android.widget.CompoundButton;

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
//        /**
//         * 显示第一个图表数据
//         */
//        void setCombinedChartData(int xSize, ValueFormatter formatter, List<Float> yData);

//        /**
//         * 第一个图表是否显示柱状图
//         */
//        void setBarDataEnable(boolean enable);
//
//        /**
//         * 第一个图表是否显示折线图
//         */
//        void setLineDataEnable(boolean enable);

        /**
         * 显示第二个图表数据
         */
        void setLineChartData(List<Float> yData);

        /**
         * 第二个图表类型
         *
         * @param type //0:折线图 1：曲线图 2：方线图
         */
        void setLineChartType(int type);

        /**
         * Y值为0是否继续绘制
         */
        void setLineChartContinuous(boolean continuous);
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
