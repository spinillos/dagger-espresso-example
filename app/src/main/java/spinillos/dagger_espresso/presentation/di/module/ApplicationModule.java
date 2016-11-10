package spinillos.dagger_espresso.presentation.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import spinillos.dagger_espresso.presentation.Navigator;

/**
 * Created by Selene on 05/11/16.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return application;
    }

    @Singleton
    @Provides
    Navigator providesNavigator() {
        return new Navigator();
    }


}
