//package com.yuehai.android.widget;
//
//import android.content.Context;
//import android.widget.TextView;
//
//import com.github.mikephil.charting.components.MarkerView;
//import com.github.mikephil.charting.data.CandleEntry;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.highlight.Highlight;
//import com.github.mikephil.charting.utils.MPPointF;
//import com.github.mikephil.charting.utils.Utils;
//import com.yuehai.android.R;
//
//import java.text.DecimalFormat;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
//public class MyMarkerView extends MarkerView {
//    Unbinder unbinder;
//    @BindView(R.id.x_data)
//    TextView x_data;
//    @BindView(R.id.y1_data)
//    TextView y1_data;
//    private List<Float> yData;
//
//    public MyMarkerView(Context context) {
//        super(context, R.layout.chat_marker_layout);
//        unbinder = ButterKnife.bind(this);
//    }
//
//    public void setData(List<Float> yData) {
//        this.yData = yData;
//    }
//
//    @Override
//    public MPPointF getOffset() {
//        return new MPPointF(-(getWidth() >> 1), -getHeight() >> 1);
//    }
//
//
//    @Override
//    public void refreshContent(Entry e, Highlight highlight) {
//        if (e instanceof CandleEntry) {
//            CandleEntry ce = (CandleEntry) e;
//            x_data.setText(Utils.formatNumber(ce.getHigh(), 0, true));
//        } else {
//            String dd = new DecimalFormat("##0.00").format(e.getY());
//            x_data.setText(String.valueOf((int) e.getX() + 1));
//            if (yData != null) {
//                for (int j = 0; j < yData.size(); j++) {
//                    if (e.getX() == j) {
//                        y1_data.setText(String.format("%s%s", yData.get(j), "万kWh"));
//                    }
//                }
//            } else {
//                y1_data.setText(dd);
//            }
//        }
//        super.refreshContent(e, highlight);
//    }
//
//}
