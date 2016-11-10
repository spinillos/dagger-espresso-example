package spinillos.dagger_espresso.presentation.di.component.activity;

import spinillos.dagger_espresso.presentation.di.module.ActivityModule;

/**
 * Created by Selene on 05/11/16.
 */

public interface ActivityComponentBuilder<M extends ActivityModule, C extends ActivityComponent> {

    ActivityComponentBuilder<M, C> activityModule(M module);

    C build();
}
