package spinillos.dagger_espresso.presentation.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import spinillos.dagger_espresso.framework.executor.JobExecutor;
import spinillos.dagger_espresso.framework.executor.PostThreadExecutor;
import spinillos.dagger_espresso.framework.executor.ThreadExecutor;
import spinillos.dagger_espresso.framework.executor.UIThread;
import spinillos.dagger_espresso.presentation.Navigator;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context providesContext() {
        return application;
    }

    @Singleton
    @Provides
    Navigator providesNavigator() {
        return new Navigator();
    }

    @Singleton
    @Provides
    ThreadExecutor providesThreadExecutor() {
        return new JobExecutor();
    }

    @Singleton
    @Provides
    PostThreadExecutor providesPostThreadExecutor() {
        return new UIThread();
    }

}
