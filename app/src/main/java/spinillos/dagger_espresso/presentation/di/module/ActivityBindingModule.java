package spinillos.dagger_espresso.presentation.di.module;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import spinillos.dagger_espresso.presentation.main.MainActivity;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityKey;
import spinillos.dagger_espresso.presentation.main.di.MainComponent;
import spinillos.dagger_espresso.presentation.splash.di.SplashComponent;
import spinillos.dagger_espresso.presentation.splash.SplashActivity;

/**
 * Created by Selene on 05/11/16.
 */

@Module(subcomponents = {MainComponent.class, SplashComponent.class})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract ActivityComponentBuilder mainActivity(MainComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    public abstract ActivityComponentBuilder splashActivity(SplashComponent.Builder builder);
}
