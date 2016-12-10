package spinillos.dagger_espresso.presentation.di.module;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import spinillos.dagger_espresso.presentation.di.component.activity.ComponentBuilder;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityKey;
import spinillos.dagger_espresso.presentation.main.MainActivity;
import spinillos.dagger_espresso.presentation.main.di.MainComponent;
import spinillos.dagger_espresso.presentation.menu.MenuActivity;
import spinillos.dagger_espresso.presentation.menu.di.MenuComponent;

@Module(subcomponents = {MainComponent.class, MenuComponent.class})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract ComponentBuilder mainActivity(MainComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MenuActivity.class)
    public abstract ComponentBuilder menuActivity(MenuComponent.Builder builder);
}
