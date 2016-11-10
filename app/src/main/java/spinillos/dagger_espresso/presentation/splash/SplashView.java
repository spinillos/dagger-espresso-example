package spinillos.dagger_espresso.presentation.splash;

import spinillos.dagger_espresso.framework.BaseView;

/**
 * Created by Selene on 08/11/16.
 */

public interface SplashView extends BaseView {

    void checkPermissions();

    void showLoading();

    void hideLoading();

    void showPermissionRejectedMessage();

    void showPermissionBlockedMessage();

    void showRequestPermissionDialog();

    void openNextActivity();

    void openApplicationSettings();
}
