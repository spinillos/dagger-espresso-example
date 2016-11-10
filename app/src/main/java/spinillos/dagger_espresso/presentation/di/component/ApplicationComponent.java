package spinillos.dagger_espresso.presentation.di.component;

import javax.inject.Singleton;

import dagger.Component;
import spinillos.dagger_espresso.EspressoApplication;
import spinillos.dagger_espresso.presentation.di.module.ActivityBindingModule;
import spinillos.dagger_espresso.presentation.di.module.ApplicationModule;

/**
 * Created by Selene on 05/11/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ActivityBindingModule.class})
public interface ApplicationComponent {

    void inject(EspressoApplication espressoApplication);
}
