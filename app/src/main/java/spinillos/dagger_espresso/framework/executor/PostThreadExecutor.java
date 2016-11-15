package spinillos.dagger_espresso.framework.executor;

/**
 * Created by Selene on 10/11/16.
 */

public interface PostThreadExecutor {

    void run(Runnable runnable);
}
