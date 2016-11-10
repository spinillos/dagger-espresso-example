package spinillos.dagger_espresso.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;

import spinillos.dagger_espresso.presentation.main.MainActivity;

/**
 * Created by Selene on 09/11/16.
 */

public class Navigator {

    private static final String PACKAGE = "package:";

    public void goToMainActivity(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public void goToApplicationSettings(@NonNull Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse(PACKAGE + activity.getPackageName()));
        activity.startActivity(intent);
    }

}
