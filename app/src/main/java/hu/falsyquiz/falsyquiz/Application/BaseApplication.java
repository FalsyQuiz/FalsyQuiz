package hu.falsyquiz.falsyquiz.Application;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public BaseApplication() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeStetho();
    }

    public static BaseApplication getInstance() {return mInstance; }

    private void initializeStetho() {
        Stetho.initializeWithDefaults(this);
    }
}
