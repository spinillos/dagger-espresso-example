package spinillos.dagger_espresso.data.datastore;

public interface ResourceCallback<R> {

    void onResourcesLoaded(R resources);

    void onError(Throwable throwable);
}
