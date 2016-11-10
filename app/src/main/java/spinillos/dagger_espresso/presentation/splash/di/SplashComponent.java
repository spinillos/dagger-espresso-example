package spinillos.dagger_espresso.presentation.splash.di;

import dagger.Subcomponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.splash.SplashActivity;

/**
 * Created by Selene on 08/11/16.
 */

@Subcomponent(modules = ActivityModule.class)
public interface SplashComponent extends ActivityComponent {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<ActivityModule, SplashComponent> {}

    void inject(SplashActivity activity);
}
