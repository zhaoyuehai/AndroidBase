package com.yuehai.android;

import com.yuehai.android.contract.ChartDemoContract;
import com.yuehai.android.net.response.DemoData;
import com.yuehai.android.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by zhaoyuehai 2019/4/2
 */
public class DemoModel implements ChartDemoContract.Model {
    /*
     * 假数据 月数据
     */
    private List<DemoData> mMonthList;
    /*
     * 假数据 年数据
     */
    private List<DemoData> mYearList;

    @NonNull
    @Override
    public List<String> getMonthXData() {
        List<String> xList = new ArrayList<>();
        for (int i = 0; i < mMonthList.size(); i++) {//设置30天
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
            if (mMonthList.get(i).getEqpGen() != null) {
                y.set(Integer.parseInt(mMonthList.get(i).getDate()) - 1, Float.valueOf(mMonthList.get(i).getEqpGen()));
            }
        }
        return y;
    }

    @NonNull
    @Override
    public List<String> getYearXData() {
        List<String> xList = new ArrayList<>();
        for (int i = 0; i < mYearList.size(); i++) {//设置12个月
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
            if (mYearList.get(i).getEqpGen() != null) {
                y.set(Integer.parseInt(mYearList.get(i).getDate()) - 1, Float.valueOf(mYearList.get(i).getEqpGen()));
            }
        }
        return y;
    }

    public DemoModel() {
        String data_month = "[" + "{" + "\"date\":\"1\"," + "\"eqpGen\":\"11.4762\"," + "\"resource\":\"0.03\"" + "}," + "{" + "\"date\":\"2\"," +
                "    \"eqpGen\":\"9.6752\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"3\"," +
                "    \"eqpGen\":\"16.1332\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"4\"," +
                "    \"eqpGen\":\"22.2093\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"5\"," +
                "    \"eqpGen\":\"20.2761\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"6\"," +
                "    \"eqpGen\":\"21.4358\"," +
                "    \"resource\":\"0.02\"" +
                "    }," +
                "    {" +
                "    \"date\":\"7\"," +
                "    \"eqpGen\":\"8.5263\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"8\"," +
                "    \"eqpGen\":\"19.9229\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"9\"," +
                "    \"eqpGen\":\"13.6082\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"10\"," +
                "    \"eqpGen\":\"10.4839\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"11\"," +
                "    \"eqpGen\":\"9.0943\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"12\"," +
                "    \"eqpGen\":\"11.7128\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"13\"," +
                "    \"eqpGen\":\"11.2465\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"14\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"15\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"16\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"17\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"18\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"19\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"20\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"21\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"22\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.02\"" +
                "    }," +
                "    {" +
                "    \"date\":\"23\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.02\"" +
                "    }," +
                "    {" +
                "    \"date\":\"24\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"25\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.02\"" +
                "    }," +
                "    {" +
                "    \"date\":\"26\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"16.93\"" +
                "    }," +
                "    {" +
                "    \"date\":\"27\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.02\"" +
                "    }," +
                "    {" +
                "    \"date\":\"28\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"29\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.03\"" +
                "    }," +
                "    {" +
                "    \"date\":\"30\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"23.58\"" +
                "    }," +
                "    {" +
                "    \"date\":\"31\"," +
                "    \"eqpGen\":\"0.0000\"," +
                "    \"resource\":\"0.02\"" +
                "    }" +
                "    ]";
        mMonthList = JsonUtil.stringToArray(data_month, DemoData[].class);
        String data_year = "[" +
                "    {" +
                "    \"date\":\"01\"," +
                "    \"eqpGen\":\"466.2648\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"02\"," +
                "    \"eqpGen\":\"437.4457\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"03\"," +
                "    \"eqpGen\":\"473.4397\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"04\"," +
                "    \"eqpGen\":\"457.2334\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"05\"," +
                "    \"eqpGen\":\"420.3451\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"06\"," +
                "    \"eqpGen\":\"387.6668\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"07\"," +
                "    \"eqpGen\":\"391.8285\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"08\"," +
                "    \"eqpGen\":\"372.1738\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"09\"," +
                "    \"eqpGen\":\"374.3310\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"10\"," +
                "    \"eqpGen\":\"440.9918\"," +
                "    \"resource\":\"0.00\"" +
                "    }," +
                "    {" +
                "    \"date\":\"11\"," +
                "    \"eqpGen\":\"509.0135\"," +
                "    \"resource\":\"323.07\"" +
                "    }," +
                "    {" +
                "    \"date\":\"12\"," +
                "    \"eqpGen\":\"485.8149\"," +
                "    \"resource\":\"5981.83\"" +
                "    }" +
                "    ]";
        mYearList = JsonUtil.stringToArray(data_year, DemoData[].class);
    }
}
