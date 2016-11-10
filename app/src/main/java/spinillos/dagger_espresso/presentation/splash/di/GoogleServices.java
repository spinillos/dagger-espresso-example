package spinillos.dagger_espresso.presentation.splash.di;

import com.google.android.gms.common.api.GoogleApiClient;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Selene on 09/11/16.
 */

@Module
public class GoogleServices {

    @Provides
    GoogleApiClient googleApiClient(Activity activity) {
        return new GoogleApiClient.Builder(activity)
                .build();
    }
}
