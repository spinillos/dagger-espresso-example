package spinillos.dagger_espresso.presentation.main.di;

import dagger.Subcomponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.di.scopes.ActivityScope;
import spinillos.dagger_espresso.presentation.main.MainActivity;

/**
 * Created by Selene on 05/11/16.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, PictureModule.class})
public interface MainComponent extends ActivityComponent {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainComponent> {

        Builder activityModule(ActivityModule activityModule);
    }

    void inject(MainActivity activity);
}
