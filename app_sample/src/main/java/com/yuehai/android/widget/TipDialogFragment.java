package com.yuehai.android.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yuehai.android.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提示信息DialogFragment
 * Created by zhaoyuehai 2019/3/29
 */
public class TipDialogFragment extends DialogFragment {

    public interface OnClickListener {
        /**
         * 之后会dismiss
         */
        void onConfirm();

        /**
         * 之后会dismiss
         */
        void onCancel();
    }

    public void setOnConfirmListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    private Unbinder unbinder;
    @BindView(R.id.tip_tv)
    AppCompatTextView tipTV;
    private OnClickListener mOnClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tip_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String msg = getArguments().getString("msg");
            if (msg != null)
                tipTV.setText(msg);
        }
    }

    private String msg;

    public void setMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (msg != null) tipTV.setText(msg);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window win = getDialog().getWindow();
            // 一定要设置Background，如果不设置，window属性设置无效
            win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            DisplayMetrics dm = new DisplayMetrics();
            if (getActivity() != null && getActivity().getWindowManager() != null)
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams params = win.getAttributes();
            params.gravity = Gravity.CENTER;
            // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            win.setAttributes(params);
        }
    }

    @OnClick({R.id.confirm_btn, R.id.cancel_btn})
    void OnClick(View view) {
        if (mOnClickListener == null) return;
        switch (view.getId()) {
            case R.id.confirm_btn:
                mOnClickListener.onConfirm();
                dismiss();
                break;
            case R.id.cancel_btn:
                mOnClickListener.onCancel();
                dismiss();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY) unbinder.unbind();
    }
}
