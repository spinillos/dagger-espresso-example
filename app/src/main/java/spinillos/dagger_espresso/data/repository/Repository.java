package spinillos.dagger_espresso.data.repository;

/**
 * Created by Selene on 11/11/16.
 */

public interface Repository<T> {

    T get();

    boolean put(T data);

    T update(T data);

    boolean delete();
}
