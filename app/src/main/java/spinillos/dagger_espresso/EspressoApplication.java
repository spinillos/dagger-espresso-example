package spinillos.dagger_espresso;

import android.app.Activity;
import android.app.Application;

import java.util.Map;

import javax.inject.Inject;

import spinillos.dagger_espresso.presentation.di.component.DaggerApplicationComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ApplicationModule;

public class EspressoApplication extends Application {

    @Inject
    Map<Class<? extends Activity>, ComponentBuilder> componentBuilder;

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

    public ComponentBuilder getActivityComponent(Class<? extends Activity> activity) {
        return componentBuilder.get(activity);
    }
}
