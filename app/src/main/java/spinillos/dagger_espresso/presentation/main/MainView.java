package spinillos.dagger_espresso.presentation.main;

import java.util.List;

import spinillos.dagger_espresso.framework.BaseView;
import spinillos.dagger_espresso.presentation.main.model.Picture;

public interface MainView extends BaseView {

    void checkPermissions();

    void showPermissionRejectedMessage();

    void showPermissionBlockedMessage();

    void showRequestPermissionDialog();

    void openApplicationSettings();

    void enableCamera();

    void showPictures(List<Picture> pictures);

    void showLoadingError();

    void showEmptyResultsView();

    void showLoading();

    void hideLoading();

    void onNewPictureAdded(Picture picture);
}
