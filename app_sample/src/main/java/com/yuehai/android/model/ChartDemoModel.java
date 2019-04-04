package com.yuehai.android.model;

import com.yuehai.android.contract.ChartDemoContract;
import com.yuehai.android.net.response.CharDataBean;
import com.yuehai.android.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by zhaoyuehai 2019/4/2
 */
public class ChartDemoModel implements ChartDemoContract.Model {
    /*
     * 假数据 月数据
     */
    private List<CharDataBean> mMonthList;
    /*
     * 假数据 年数据
     */
    private List<CharDataBean> mYearList;

    @NonNull
    @Override
    public List<String> getMonthXData() {
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= mMonthList.size(); i++) {//设置30天
            xList.add(String.valueOf(i));
        }
        return xList;
    }

    @NonNull
    @Override
    public List<Float> getMonthYData() {
        List<Float> y = new ArrayList<>();
        for (int i = 0; i < mMonthList.size(); i++) {//设置30天
            y.add(0f);
        }
        for (int i = 0; i < mMonthList.size(); i++) {//设置X值对应的Y值
            if (mMonthList.get(i).getValue() != null) {
                y.set(Integer.parseInt(mMonthList.get(i).getDate()) - 1, Float.valueOf(mMonthList.get(i).getValue()));
            }
        }
        return y;
    }

    @NonNull
    @Override
    public List<String> getYearXData() {
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= mYearList.size(); i++) {//设置12个月
            xList.add(String.valueOf(i));
        }
        return xList;
    }

    @NonNull
    @Override
    public List<Float> getYearYData() {
        List<Float> y = new ArrayList<>();
        for (int i = 0; i < mYearList.size(); i++) {//设置12个月
            y.add(0f);
        }
        for (int i = 0; i < mYearList.size(); i++) {//设置X值对应的Y值
            if (mYearList.get(i).getValue() != null) {
                y.set(Integer.parseInt(mYearList.get(i).getDate()) - 1, Float.valueOf(mYearList.get(i).getValue()));
            }
        }
        return y;
    }

    public ChartDemoModel() {
        String data_month = "[" +
                "    {" +
                "    \"date\":\"1\"," +
                "    \"value\":\"11.4762\"" +
                "    }," +
                "    {" +
                "    \"date\":\"2\"," +
                "    \"value\":\"9.6752\"" +
                "    }," +
                "    {" +
                "    \"date\":\"3\"," +
                "    \"value\":\"16.1332\"" +
                "    }," +
                "    {" +
                "    \"date\":\"4\"," +
                "    \"value\":\"22.2093\"" +
                "    }," +
                "    {" +
                "    \"date\":\"5\"," +
                "    \"value\":\"20.2761\"" +
                "    }," +
                "    {" +
                "    \"date\":\"6\"," +
                "    \"value\":\"21.4358\"" +
                "    }," +
                "    {" +
                "    \"date\":\"7\"," +
                "    \"value\":\"8.5263\"" +
                "    }," +
                "    {" +
                "    \"date\":\"8\"," +
                "    \"value\":\"19.9229\"" +
                "    }," +
                "    {" +
                "    \"date\":\"9\"," +
                "    \"value\":\"13.6082\"" +
                "    }," +
                "    {" +
                "    \"date\":\"10\"," +
                "    \"value\":\"10.4839\"" +
                "    }," +
                "    {" +
                "    \"date\":\"11\"," +
                "    \"value\":\"9.0943\"" +
                "    }," +
                "    {" +
                "    \"date\":\"12\"," +
                "    \"value\":\"11.7128\"" +
                "    }," +
                "    {" +
                "    \"date\":\"13\"," +
                "    \"value\":\"11.2465\"" +
                "    }," +
                "    {" +
                "    \"date\":\"14\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"15\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"16\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"17\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"18\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"19\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"20\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"21\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"22\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"23\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"24\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"25\"," +
                "    \"value\":\"23.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"26\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"27\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"28\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"29\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"30\"," +
                "    \"value\":\"0.0000\"" +
                "    }," +
                "    {" +
                "    \"date\":\"31\"," +
                "    \"value\":\"0.0000\"" +
                "    }" +
                "    ]";
        mMonthList = JsonUtil.stringToArray(data_month, CharDataBean[].class);
        String data_year = "[" +
                "    {" +
                "    \"date\":\"01\"," +
                "    \"value\":\"466.2648\"" +
                "    }," +
                "    {" +
                "    \"date\":\"02\"," +
                "    \"value\":\"437.4457\"" +
                "    }," +
                "    {" +
                "    \"date\":\"03\"," +
                "    \"value\":\"473.4397\"" +
                "    }," +
                "    {" +
                "    \"date\":\"04\"," +
                "    \"value\":\"457.2334\"" +
                "    }," +
                "    {" +
                "    \"date\":\"05\"," +
                "    \"value\":\"0\"" +
                "    }," +
                "    {" +
                "    \"date\":\"06\"," +
                "    \"value\":\"387.6668\"" +
                "    }," +
                "    {" +
                "    \"date\":\"07\"," +
                "    \"value\":\"391.8285\"" +
                "    }," +
                "    {" +
                "    \"date\":\"08\"," +
                "    \"value\":\"372.1738\"" +
                "    }," +
                "    {" +
                "    \"date\":\"09\"," +
                "    \"value\":\"374.3310\"" +
                "    }," +
                "    {" +
                "    \"date\":\"10\"," +
                "    \"value\":\"0\"" +
                "    }," +
                "    {" +
                "    \"date\":\"11\"," +
                "    \"value\":\"509.0135\"" +
                "    }," +
                "    {" +
                "    \"date\":\"12\"," +
                "    \"value\":\"485.8149\"" +
                "    }" +
                "    ]";
        mYearList = JsonUtil.stringToArray(data_year, CharDataBean[].class);
    }
}
