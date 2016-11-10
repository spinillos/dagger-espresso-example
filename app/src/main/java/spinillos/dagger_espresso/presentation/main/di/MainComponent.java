package spinillos.dagger_espresso.presentation.main.di;

import dagger.Subcomponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.main.MainActivity;

/**
 * Created by Selene on 05/11/16.
 */

@Subcomponent(modules = ActivityModule.class)
public interface MainComponent extends ActivityComponent {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<ActivityModule, MainComponent> {}

    void inject(MainActivity activity);
}
