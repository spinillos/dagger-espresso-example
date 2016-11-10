package spinillos.dagger_espresso.presentation.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import spinillos.dagger_espresso.presentation.di.scopes.ActivityScope;

/**
 * Created by Selene on 05/11/16.
 */

@Module
public class ActivityModule {

    protected final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    Activity providesActivity() {
        return activity;
    }
}
