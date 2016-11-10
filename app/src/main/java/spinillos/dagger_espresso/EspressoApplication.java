package spinillos.dagger_espresso;

import android.app.Activity;
import android.app.Application;

import java.util.Map;

import javax.inject.Inject;

import spinillos.dagger_espresso.presentation.di.component.DaggerApplicationComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ApplicationModule;

/**
 * Created by Selene on 05/11/16.
 */

public class EspressoApplication extends Application {

    @Inject
    Map<Class<? extends Activity>, ActivityComponentBuilder> componentBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build()
                .inject(this);
    }

    public ActivityComponentBuilder getActivityComponent(Class<? extends Activity> activity) {
        return componentBuilder.get(activity);
    }
}
