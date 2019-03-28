package library.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Activity的基类
 * <p>
 * 完全自主布局时：
 * 使用getContentViewId()返回你的布局layout文件id。
 * <p>
 * <p>
 * 在BaseActivity支持统一的Toolbar+无数据页面显示时：
 * 请使用getInnerViewId()返回你的内容布局（不需要toolbar）layout文件id；
 * 请使用getToolbarTitle()设置你的标题内容。
 * <p>
 * 当你需要自己实现toolbar布局时：
 * 请使用getToolbarLayoutId()返回你的toolbar布局layout文件id；
 * 请使用getToolbarId()返回你的Toolbar的id；
 * 请使用getTitleId()返回你的TextView标题的id。
 */
public abstract class BaseActivity extends AppCompatActivity {


    private TextView mTitleTV;
    private ViewGroup mContentView;
    private Unbinder unbinder;

    /**
     * 自主实现布局 setContentView
     * 注意：getContentViewId()和getInnerViewId()方法需要二选一去重写！
     *
     * @return 布局layout文件id
     */
    protected int getContentViewId() {
        return -1;
    }

    /**
     * 使用支持toolbar和无数据页面展示
     * 注意：getContentViewId()和getInnerViewId()方法需要二选一去重写！
     *
     * @return 嵌套布局layout文件id
     */
    protected int getInnerViewId() {
        return -1;
    }

    /**
     * toolbar布局文件
     * 不用默认toolbar时，此方法返回自己的toolbar布局
     */
    protected @LayoutRes
    int getToolbarLayoutId() {
        return R.layout.toolbar_layout;
    }

    /**
     * Toolbar的id
     * 不用默认toolbar时，此方法返回自己的toolbar布局中的toolbarId
     */
    protected @IdRes
    int getToolbarId() {
        return R.id.toolbar;
    }

    /**
     * 标题TextView的id
     * 不用默认toolbar时，此方法返回自己的toolbar布局中的标题TextView的id
     */
    protected @IdRes
    int getTitleId() {
        return R.id.tv_title;
    }

    /**
     * toolbar标题
     *
     * @return 标题
     */
    protected int getToolbarTitle() {
        return -1;
    }

    /**
     * 根布局设置背景
     *
     * @return The identifier of the resource.
     */
    protected @DrawableRes
    int getBackgroundResource() {
        return R.color.bg_color;
    }

    protected void onToolbarBack() {
        finish();
    }

    protected boolean exitAnimalEnable() {
        return true;
    }

    protected @Nullable
    TextView getTitleTV() {
        return mTitleTV;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewId() != -1) {//自主实现布局
            setContentView(getContentViewId());
            unbinder = ButterKnife.bind(this);
        } else if (getInnerViewId() != -1) {//支持toolbar和无数据页面内嵌布局
            //根布局是个LinearLayout
            LinearLayout rootView = new LinearLayout(this);
            rootView.setBackgroundResource(getBackgroundResource());
            rootView.setOrientation(LinearLayout.VERTICAL);
            setContentView(rootView);//此方法默认是宽高MATCH_PARENT
            //先添加toolbar
            View.inflate(this, getToolbarLayoutId(), rootView);
            //再添加FrameLayout作为主内容View
            mContentView = new FrameLayout(this);
            View.inflate(this, getInnerViewId(), mContentView);
            //mContentFL需要宽高MATCH_PARENT，不设置默认是WRAP_CONTENT
            rootView.addView(mContentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            unbinder = ButterKnife.bind(this);
            //初始化toolbar并设置标题
            Toolbar toolbar = findViewById(getToolbarId());
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            mTitleTV = findViewById(getTitleId());
            if (mTitleTV != null) {
                if (getToolbarTitle() != -1) {
                    mTitleTV.setText(getToolbarTitle());
                } else {
                    mTitleTV.setText("");
                }
            }
        }
    }

    protected void setToolbarTitle(String title) {
        if (mTitleTV != null) {
            mTitleTV.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getInnerViewId() != -1) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onToolbarBack();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) unbinder.unbind();
    }

    @Override
    public void finish() {
        super.finish();
        if (exitAnimalEnable())
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

}