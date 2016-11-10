package spinillos.dagger_espresso.presentation.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.framework.BaseActivity;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.main.di.MainComponent;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    private static final int REQUEST_IMAGE_CAPTURE = 123;

    @BindView(R.id.take_picture_button)
    Button pictureButton;

    @Inject
    MainPresenter presenter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void injectDependencies() {
        ((MainComponent.Builder) getComponentBuilder())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public MainView getView() {
        return this;
    }

    @Override
    public MainPresenter getPresenter() {
        return presenter;
    }

    @OnClick(R.id.take_picture_button)
    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
