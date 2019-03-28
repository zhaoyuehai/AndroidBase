package library.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.multidex.MultiDexApplication;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private static BaseApplication INSTANCE;

    public static BaseApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 所有activity管理
     */
    private final List<Activity> activityList = new LinkedList<>();
    /**
     * 正在运行的activity
     */
    protected final List<Activity> runningActivity = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        runningActivity.add(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        runningActivity.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
    }

    public int getActivityCount() {
        return activityList.size();
    }

    public int getRunningActivityCount() {
        return runningActivity.size();
    }

    public Activity getTopActivity() {
        if (runningActivity.size() > 0) {
            return runningActivity.get(runningActivity.size() - 1);
        }
        return null;
    }

    public Activity getActivity() {
        if (activityList.size() > 0) {
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    /**
     * 只回收activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 是否还有正在运行的activity
     */
    public boolean hasAliveActivity() {
        return activityList.size() != 0;
    }

    /**
     * 获取某个activity
     *
     * @param name activity的名称
     */
    public Activity getActivity(String name) {
        Activity act = null;
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    if (activity.getLocalClassName().contains(name)) {
                        act = activity;
                        break;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return act;
    }

    public void exitApp() {
        if (hasAliveActivity()) {
            synchronized (BaseApplication.this) {
                finishAllActivity();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
