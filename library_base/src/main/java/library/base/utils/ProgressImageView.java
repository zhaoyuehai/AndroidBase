package library.base.utils;

import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 白色的菊花
 * Created by zhaoyuehai 2019/3/6
 */
public class ProgressImageView extends AppCompatImageView {
    private MyProgressDrawable mDrawable;

    public ProgressImageView(Context context) {
        super(context);
        init();
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDrawable = new MyProgressDrawable();
        setImageDrawable(mDrawable);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private void startAnimation() {
        mDrawable.start();
    }

    private void stopAnimation() {
        mDrawable.stop();
    }
}
