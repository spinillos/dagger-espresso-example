package spinillos.dagger_espresso.presentation.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import spinillos.dagger_espresso.EspressoApplication;
import spinillos.dagger_espresso.presentation.di.module.ActivityBindingModule;
import spinillos.dagger_espresso.presentation.di.module.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class, ActivityBindingModule.class})
public interface ApplicationComponent {

    Context context();

    void inject(EspressoApplication espressoApplication);
}
