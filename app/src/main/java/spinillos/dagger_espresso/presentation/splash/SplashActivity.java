package spinillos.dagger_espresso.presentation.splash;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.framework.BaseActivity;
import spinillos.dagger_espresso.presentation.Navigator;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.splash.di.SplashComponent;

/**
 * Created by Selene on 08/11/16.
 */

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter>
        implements SplashView {

    private static final int REQUEST_CAPTURE_PERMISSION = 124;

    @BindView(R.id.loading)
    ProgressBar loading;

    @Inject
    Navigator navigator;

    @Inject
    SplashPresenter presenter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void injectDependencies() {
        ((SplashComponent.Builder) getComponentBuilder())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public SplashView getView() {
        return this;
    }

    @Override
    public SplashPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                getPresenter().onPermissionsRejected();
            } else {
                getPresenter().onPermissionsBlocked();
            }

        } else {
            getPresenter().onPermissionsGranted();
        }
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAPTURE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPresenter().onPermissionsGranted();
            } else {
                getPresenter().onPermissionsRejected();
            }
        }
    }

    @Override
    public void showPermissionRejectedMessage() {
        Snackbar.make(loading, R.string.main_snack_camera_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.main_snack_camera_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().onRequestPermissionClicked();
                    }
                }).show();
    }

    @Override
    public void showPermissionBlockedMessage() {
        Snackbar.make(loading, R.string.main_snack_camera_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.main_snack_camera_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().onRequestBlockedPermissionClicked();
                    }
                }).show();
    }

    @Override
    public void openNextActivity() {
        navigator.goToMainActivity(this);
    }

    @Override
    public void openApplicationSettings() {
        navigator.goToApplicationSettings(this);
    }

    @Override
    public void showRequestPermissionDialog() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAPTURE_PERMISSION);
    }
}
