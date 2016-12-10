package spinillos.dagger_espresso.presentation.menu.di;

import dagger.Subcomponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponent;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.menu.MenuActivity;

@Subcomponent(modules = ActivityModule.class)
public interface MenuComponent extends ActivityComponent {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MenuComponent> {
        Builder activityModule(ActivityModule activityModule);
    }

    void inject(MenuActivity menuActivity);
}
