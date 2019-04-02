package com.yuehai.android.widget;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.yuehai.android.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyMarkerView extends MarkerView {
    Unbinder unbinder;
    @BindView(R.id.x_data)
    TextView x_data;
    @BindView(R.id.y1_data)
    TextView y1_data;
    @BindView(R.id.y2_data)
    TextView y2_data;
    private List<Float> barChartY;

    public MyMarkerView(Context context) {
        super(context, R.layout.chat_marker_layout);
        unbinder = ButterKnife.bind(this);
    }

    public void setData(List<Float> barChartY) {
        this.barChartY = barChartY;
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() >> 1), -getHeight() >> 1);
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            x_data.setText(Utils.formatNumber(ce.getHigh(), 0, true));
        } else {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String dd = fnum.format(e.getY());
            if (barChartY != null) {
                x_data.setText(String.valueOf((int) e.getX() + 1));
                for (int j = 0; j < barChartY.size(); j++) {
                    if (e.getX() == j) {
                        y1_data.setText(String.format("%s%s", barChartY.get(j), "(万kWh)"));
                    }
                }
//                for (int i = 0; i < lineChartY.size(); i++) {
//                    if (e.getX() == i) {
//                        if (isGuangfu) {
//                            y2_data.setText(String.format("%s(MJ/m2)", lineChartY.get(i)));
//                            y2_data.setTextColor(context.getResources().getColor(R.color.color_rbfz));
//                        } else {
//                            y2_data.setText(String.format("%s(m/s)", lineChartY.get(i)));
//                            y2_data.setTextColor(context.getResources().getColor(R.color.station_blue));
//                        }
//
//                    }
//                }
            } else {
                x_data.setText(dd);
            }

        }
        super.refreshContent(e, highlight);
    }

}
