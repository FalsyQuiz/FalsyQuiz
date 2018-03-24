package hu.falsyquiz.falsyquiz.Components;

import javax.inject.Singleton;

import dagger.Component;
import hu.falsyquiz.falsyquiz.Activities.AbstractActivity;
import hu.falsyquiz.falsyquiz.Moduls.ApplicationModule;
import hu.falsyquiz.falsyquiz.Moduls.StorageModule;

/**
 * Created by Peti on 2018. 03. 23..
 */

@Singleton
@Component(modules = {ApplicationModule.class, StorageModule.class})
public interface MainComponent {

    void inject(AbstractActivity abstractActivity);
}
