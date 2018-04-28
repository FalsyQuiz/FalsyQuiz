package hu.falsyquiz.falsyquiz.Components;

import android.drm.DrmStore;

import javax.inject.Singleton;

import dagger.Component;
import hu.falsyquiz.falsyquiz.Actions.Actions;
import hu.falsyquiz.falsyquiz.Activities.AbstractActivity;
import hu.falsyquiz.falsyquiz.Moduls.ActionsModule;
import hu.falsyquiz.falsyquiz.Moduls.ApplicationModule;
import hu.falsyquiz.falsyquiz.Moduls.StorageModule;
import hu.falsyquiz.falsyquiz.Moduls.VibratorEngineModule;

/**
 * Created by Peti on 2018. 03. 23..
 */

@Singleton
@Component(modules = {ApplicationModule.class, StorageModule.class, VibratorEngineModule.class,
        ActionsModule.class})
public interface MainComponent {

    void inject(AbstractActivity abstractActivity);
    void inject(Actions action);

}
