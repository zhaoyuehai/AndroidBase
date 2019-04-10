package com.yuehai.android.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yuehai.android.BuildConfig;
import com.yuehai.android.Contacts;
import com.yuehai.android.MyApplication;
import com.yuehai.android.R;
import com.yuehai.android.net.ResultObserver;
import com.yuehai.android.util.MyDisposableContainer;
import com.yuehai.android.util.NetUtil;
import com.yuehai.android.util.SPUtil;
import com.yuehai.android.widget.InputDialogFragment;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.schedulers.Schedulers;
import library.base.BaseActivity;
import library.base.utils.ProgressDialogUtil;
import library.base.utils.ToastUtil;

/**
 * 主页
 * Created by zhaoyuehai 2019/3/22
 */
public class MainActivity extends BaseActivity implements InputDialogFragment.OnClickListener {
    @BindView(R.id.test_btn3)
    Button test3;
    @BindView(R.id.test_btn4)
    Button test4;

    @Override
    protected int getInnerViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.main;
    }

    @Override
    protected boolean exitAnimalEnable() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test3.setText(R.string.chart_demo);
        test4.setText(R.string.theme_setting);
        showInputDialog();
    }

    @OnClick({R.id.test_btn0, R.id.test_btn1, R.id.test_btn2, R.id.test_btn3, R.id.test_btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn0:
                showInputDialog();
                break;
            case R.id.test_btn1:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.test_btn2:
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
                break;
            case R.id.test_btn3:
                startActivity(new Intent(MainActivity.this, ChartDemoActivity.class));
                break;
            case R.id.test_btn4:
                startActivity(new Intent(MainActivity.this, ThemeSettingActivity.class));
                break;
        }
    }

    private InputDialogFragment inputDialogFragment;

    /**
     * 输入服务器地址弹框
     */
    private void showInputDialog() {
        String FGTag = "InputDialogFragment";
        if (inputDialogFragment == null) {
            inputDialogFragment = (InputDialogFragment) getSupportFragmentManager().findFragmentByTag(FGTag);
            if (inputDialogFragment == null) {
                String ip = SPUtil.getInstance(Contacts.SP_NAME).getString(Contacts.SP_IP_ADDRESS, "");
                inputDialogFragment = InputDialogFragment.newInstance("请输入服务器地址", ip);
                inputDialogFragment.setCancelable(false);
            }
            inputDialogFragment.setOnConfirmListener(this);
        }
        if (!inputDialogFragment.isAdded())
            inputDialogFragment.show(getSupportFragmentManager(), FGTag);
    }

    /**
     * 去检查服务器地址是否连通
     *
     * @param content 服务器地址
     */
    @Override
    public void onConfirm(String content) {
        Observable
                .create((ObservableOnSubscribe<Boolean>) emitter -> {
                    if (NetUtil.isConnServer(content)) {
                        SPUtil.getInstance(Contacts.SP_NAME).put(Contacts.SP_IP_ADDRESS, content);
                        MyApplication.BASE_URL = content;
                        emitter.onNext(true);
                    } else {
                        emitter.onNext(false);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver<Boolean>(getDisposableContainer()) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        showLoading();
                    }

                    @Override
                    public void onNext(Boolean o) {
                        dismissLoading();
                        if (o) {
                            ToastUtil.showToast("连接成功");
                        } else {
                            showInputDialog();
                            ToastUtil.showToast("连接失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        dismissLoading();
                        showInputDialog();
                        String tips = "连接失败";
                        if (e instanceof SocketTimeoutException) {
                            tips = "响应超时，地址错误或者服务器未启动";
                        } else if (e instanceof ConnectException) {
                            if (NetUtil.isNetworkConnected()) {
                                tips = "连接失败！地址有问题";
                            } else {
                                tips = "连接失败！请检查网络连接";
                            }
                        } else if (e instanceof UnknownHostException || e instanceof MalformedURLException) {
                            tips = "连接失败！地址有问题";
                        }
                        ToastUtil.showToast(tips);
                    }
                });
    }

    @Override
    public void onCancel() {
        if (MyApplication.BASE_URL == null)
            MyApplication.BASE_URL = BuildConfig.BASE_URL;
    }

    protected Dialog loadingDialog;

    /**
     * 弹出加载中dialog
     * <p>
     * 如果需要带文字的dialog，重写此方法即可--参考-->LoginActivity
     */
    private void showLoading() {
        if (null == loadingDialog) {
            loadingDialog = ProgressDialogUtil.getProgressDialog(this);
        }
        loadingDialog.show();
    }

    private void dismissLoading() {
        ProgressDialogUtil.dismissDialog(loadingDialog);
    }

    @Override
    protected void onDestroy() {
        if (myDisposableContainer != null) myDisposableContainer.clear();
        super.onDestroy();
    }

    private MyDisposableContainer myDisposableContainer;

    protected DisposableContainer getDisposableContainer() {
        if (myDisposableContainer == null)
            myDisposableContainer = new MyDisposableContainer();
        return myDisposableContainer;
    }
}
