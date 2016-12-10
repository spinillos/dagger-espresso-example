package spinillos.dagger_espresso.presentation.main.di;

import dagger.Subcomponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.di.scopes.ActivityScope;
import spinillos.dagger_espresso.presentation.main.MainActivity;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class, PictureModule.class})
public interface MainComponent extends ActivityComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<MainComponent> {

        Builder activityModule(ActivityModule activityModule);
    }
}
