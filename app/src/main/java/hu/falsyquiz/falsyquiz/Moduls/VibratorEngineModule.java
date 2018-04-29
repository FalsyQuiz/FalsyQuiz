package hu.falsyquiz.falsyquiz.Moduls;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;


@Module(includes = ApplicationModule.class)
public class VibratorEngineModule {
    @Provides
    @Singleton
    public VibratorEngine provideVibratorEngine(Context context) {
        return new VibratorEngine(context);
    }
}
