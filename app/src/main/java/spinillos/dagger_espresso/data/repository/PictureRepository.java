package spinillos.dagger_espresso.data.repository;

import java.util.List;

import javax.inject.Inject;

import spinillos.dagger_espresso.data.datastore.DataSource;
import spinillos.dagger_espresso.data.datastore.ResourceCallback;
import spinillos.dagger_espresso.data.entity.PictureResource;

public class PictureRepository {

    private DataSource<List<PictureResource>> dataSource;

    @Inject
    public PictureRepository(DataSource<List<PictureResource>> dataSource) {
        this.dataSource = dataSource;
    }

    public void getPictures(final PictureCallback callback) {
        dataSource.fetchData(new ResourceCallback<List<PictureResource>>() {
            @Override
            public void onResourcesLoaded(List<PictureResource> resources) {
                callback.onSuccess(resources);
            }

            @Override
            public void onError(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    public interface PictureCallback {

        void onSuccess(List<PictureResource> pictures);

        void onError(Throwable throwable);
    }
}
