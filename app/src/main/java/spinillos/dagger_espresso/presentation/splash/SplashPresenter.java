package spinillos.dagger_espresso.presentation.splash;

import javax.inject.Inject;

import spinillos.dagger_espresso.framework.BasePresenter;

/**
 * Created by Selene on 08/11/16.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    public SplashPresenter() {

    }

    @Override
    public void resume() {
        getView().showLoading();
        getView().checkPermissions();
    }

    public void onRequestPermissionClicked() {
        getView().showRequestPermissionDialog();
    }

    public void onRequestBlockedPermissionClicked() {
        getView().openApplicationSettings();
    }

    public void onPermissionsGranted() {
        getView().openNextActivity();
    }

    public void onPermissionsRejected() {
        getView().hideLoading();
        getView().showPermissionRejectedMessage();
    }

    public void onPermissionsBlocked() {
        getView().hideLoading();
        getView().showPermissionBlockedMessage();
    }
}
