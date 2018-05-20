package hu.falsyquiz.falsyquiz.Moduls;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.falsyquiz.falsyquiz.Actions.Actions;
import hu.falsyquiz.falsyquiz.Tools.VibratorEngine;

/**
 * Created by Peti on 2018. 04. 28..
 */

@Module(includes = VibratorEngineModule.class)
public class ActionsModule {

    @Provides
    @Singleton
    public Actions provideActions(VibratorEngine vibratorEngine) {
        return new Actions(vibratorEngine);
    }
}
