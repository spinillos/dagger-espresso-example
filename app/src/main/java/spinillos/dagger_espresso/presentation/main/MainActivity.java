package spinillos.dagger_espresso.presentation.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.framework.BaseActivity;
import spinillos.dagger_espresso.presentation.Navigator;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.main.di.MainComponent;
import spinillos.dagger_espresso.presentation.main.model.Picture;
import spinillos.dagger_espresso.presentation.main.utils.PictureUtils;
import spinillos.dagger_espresso.presentation.main.view.PictureAdapter;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    public static final int REQUEST_IMAGE_CAPTURE = 123;
    public static final int REQUEST_CAPTURE_PERMISSION = 124;

    @BindView(R.id.button_camera)
    FloatingActionButton buttonCamera;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.loading)
    ProgressBar loadingView;

    @BindView(R.id.empty_view)
    TextView emptyListView;

    @Inject
    Navigator navigator;

    @Inject
    MainPresenter presenter;

    @Inject
    PictureUtils utils;

    private PictureAdapter adapter;

    private Uri uri;

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
    public MainPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        setupRecycler();
    }

    private void setupRecycler() {
        recyclerView.setHasFixedSize(true);
        adapter = new PictureAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView
                .addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @OnClick(R.id.button_camera)
    public void takePicture() {
        File file = null;
        try {
            file = utils.createTempFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        uri = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", file);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            getPresenter().onPictureCaptured(uri.getPath());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showPermissionRejectedMessage() {
        Snackbar.make(recyclerView, R.string.main_snack_camera_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.main_snack_camera_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().onRequestPermissionClicked();
                    }
                }).show();
    }

    @Override
    public void showPermissionBlockedMessage() {
        Snackbar.make(recyclerView, R.string.main_snack_camera_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.main_snack_camera_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().onRequestBlockedPermissionClicked();
                    }
                }).show();
    }

    @Override
    public void openApplicationSettings() {
        navigator.goToApplicationSettings(this);
    }

    @Override
    public void enableCamera() {
        buttonCamera.setEnabled(true);
    }

    @Override
    public void showPictures(List<Picture> pictures) {
        adapter.setPictures(pictures);
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(recyclerView, R.string.main_loading_pictures_error,
                Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    @Override
    public void showEmptyResultsView() {
        recyclerView.setVisibility(View.GONE);
        emptyListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void addNewPictureToList(Picture picture) {
        adapter.addPicture(picture);
    }

    @Override
    public void showRequestPermissionDialog() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAPTURE_PERMISSION);
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
}
