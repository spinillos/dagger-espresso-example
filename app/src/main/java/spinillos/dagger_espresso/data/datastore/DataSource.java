package spinillos.dagger_espresso.data.datastore;

/**
 * Created by Selene on 13/11/16.
 */

public interface DataSource<R> {

    void fetchData(ResourceCallback<R> callback);

}
