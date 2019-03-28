package library.base.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import library.base.R;


/**
 * 菊花Dialog
 * Created by zrmen on 2018/8/27
 */
public class MyProgressDialog extends Dialog {

    private TextView tvTxt;

    private int textStrId = -1;

    private String textStr;

    MyProgressDialog(Context context, @StringRes int textStrId) {
        this(context);
        this.textStrId = textStrId;
    }

    MyProgressDialog(Context context, String textStr) {
        this(context);
        this.textStr = textStr;
    }

    MyProgressDialog(Context context) {
        super(context, R.style.loading_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.loading_dialog);
        tvTxt = findViewById(R.id.tv_txt);
        setText();
    }

    private void setText() {
        if (tvTxt == null) return;
        if (TextUtils.isEmpty(textStr) && textStrId == -1) {
            return;
        }
        tvTxt.setVisibility(View.VISIBLE);
        if (textStrId != -1) {
            tvTxt.setText(textStrId);
        } else {
            tvTxt.setText(textStr);
        }
    }
}
