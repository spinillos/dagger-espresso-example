package spinillos.dagger_espresso.data.datastore;

public interface DataSource<R> {

    void fetchData(ResourceCallback<R> callback);

}
