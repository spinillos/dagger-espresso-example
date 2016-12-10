package spinillos.dagger_espresso.presentation.di.component.activity;

import android.app.Activity;

import dagger.MapKey;

@MapKey
public @interface ActivityKey {
    Class<? extends Activity> value();
}
