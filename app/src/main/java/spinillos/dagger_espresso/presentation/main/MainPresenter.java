package spinillos.dagger_espresso.presentation.main;

import java.util.List;

import javax.inject.Inject;

import spinillos.dagger_espresso.domain.exception.NoDataException;
import spinillos.dagger_espresso.domain.interactor.GetPicturesUseCase;
import spinillos.dagger_espresso.framework.BasePresenter;
import spinillos.dagger_espresso.presentation.main.model.Picture;

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

    public void onPictureCaptured(String path) {
        Picture picture = new Picture();
        picture.setPath(path);
        getView().addNewPictureToList(picture);
    }

}
