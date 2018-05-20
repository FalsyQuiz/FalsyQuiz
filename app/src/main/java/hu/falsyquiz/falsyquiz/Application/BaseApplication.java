package hu.falsyquiz.falsyquiz.Application;

import android.app.Application;
import com.facebook.stetho.Stetho;
import hu.falsyquiz.falsyquiz.Components.DaggerMainComponent;
import hu.falsyquiz.falsyquiz.Components.MainComponent;
import hu.falsyquiz.falsyquiz.Moduls.ActionsModule;
import hu.falsyquiz.falsyquiz.Moduls.ApplicationModule;
import hu.falsyquiz.falsyquiz.Moduls.StorageModule;
import hu.falsyquiz.falsyquiz.Moduls.VibratorEngineModule;
import lombok.Getter;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public BaseApplication() {
        mInstance = this;
    }

    @Getter
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeStetho();

        mainComponent = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .storageModule(new StorageModule())
                .vibratorEngineModule(new VibratorEngineModule())
                .actionsModule(new ActionsModule())
                .build();
    }

    public static BaseApplication getInstance() {return mInstance; }

    private void initializeStetho() {
        Stetho.initializeWithDefaults(this);
    }

}
