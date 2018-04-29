package hu.falsyquiz.falsyquiz.Moduls;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

/**
 * Created by Peti on 2018. 03. 23..
 * This module class provides the Application.
 */

@AllArgsConstructor
@Module
public class ApplicationModule {

    private final Application application;

    @Provides
    @Singleton
    public Context provideContext() { return application; }
}
