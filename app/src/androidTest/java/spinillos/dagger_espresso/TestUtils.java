package spinillos.dagger_espresso;

import android.content.pm.PackageManager;
import android.os.Build;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by Selene on 11/11/16.
 */

public class TestUtils {

    public static void grantPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation()
                    .executeShellCommand(
                            "pm grant " + getTargetContext().getPackageName() + " " + permission);
        }
    }

    public static void revokePermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation()
                    .executeShellCommand(
                            "pm revoke " + getTargetContext().getPackageName() + " " + permission);
        }
    }

    public static boolean hasFeature(String feature) {
        PackageManager packageManager = getTargetContext().getPackageManager();
        return packageManager.hasSystemFeature(feature);
    }
}
