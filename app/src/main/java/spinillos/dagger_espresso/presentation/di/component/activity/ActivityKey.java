package spinillos.dagger_espresso.presentation.di.component.activity;

import android.app.Activity;

import dagger.MapKey;

/**
 * Created by Selene on 05/11/16.
 */

@MapKey
public @interface ActivityKey {
    Class<? extends Activity> value();
}
