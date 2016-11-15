package spinillos.dagger_espresso.data.datastore;

/**
 * Created by Selene on 10/11/16.
 */

public interface ResourceCallback<R> {

    void onResourcesLoaded(R resources);

    void onError(Throwable throwable);
}
