package spinillos.dagger_espresso.presentation.main;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import spinillos.dagger_espresso.domain.exception.NoDataException;
import spinillos.dagger_espresso.domain.interactor.GetPicturesUseCase;
import spinillos.dagger_espresso.framework.BasePresenter;
import spinillos.dagger_espresso.presentation.main.model.Picture;
import spinillos.dagger_espresso.presentation.main.utils.PictureUtils;

/**
 * Created by Selene on 06/11/16.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private final GetPicturesUseCase getPicturesUseCase;

    @Inject
    public MainPresenter(GetPicturesUseCase getPicturesUseCase) {
        this.getPicturesUseCase = getPicturesUseCase;
    }

    @Override
    public void initialize() {
        getView().checkPermissions();
        getView().showLoading();
        fetchPictures();
    }

    public void onRequestPermissionClicked() {
        getView().showRequestPermissionDialog();
    }

    public void onRequestBlockedPermissionClicked() {
        getView().openApplicationSettings();
    }

    public void onPermissionsRejected() {
        getView().showPermissionRejectedMessage();
    }

    public void onPermissionsBlocked() {
        getView().showPermissionBlockedMessage();
    }

    public void onPermissionsGranted() {
        getView().enableCamera();
    }

    private void fetchPictures() {
        getPicturesUseCase.fetchPictures(new GetPicturesUseCase.Callback() {
            @Override
            public void onPicturesLoaded(List<Picture> pictures) {
                Collections.reverse(pictures);
                getView().showPictures(pictures);
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable throwable) {
                handleErrors(throwable);
                getView().hideLoading();
            }
        });
    }

    private void handleErrors(Throwable throwable) {
        if (throwable instanceof NoDataException) {
            getView().showEmptyResultsView();
        } else {
            getView().showLoadingError();
        }
    }

    public void onPictureCaptured(Bitmap bitmap) {
        Uri tempUri = getImageUri(bitmap);
        Picture picture = new Picture();
        picture.setPath(tempUri.getPath());
        getView().onNewPictureAdded(picture);
    }

    private Uri getImageUri(Bitmap inImage) {

        File file = null;

        try {
            file = PictureUtils.createTempFile();
            FileOutputStream out = new FileOutputStream(file);
            inImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.parse(file.getAbsolutePath());
    }

}
