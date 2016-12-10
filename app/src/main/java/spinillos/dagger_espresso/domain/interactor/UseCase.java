package spinillos.dagger_espresso.domain.interactor;

import spinillos.dagger_espresso.framework.executor.PostThreadExecutor;
import spinillos.dagger_espresso.framework.executor.ThreadExecutor;

public abstract class UseCase implements Runnable {

    private final ThreadExecutor threadExecutor;
    private final PostThreadExecutor postThreadExecutor;

    protected UseCase(ThreadExecutor threadExecutor, PostThreadExecutor postThreadExecutor) {
        this.threadExecutor = threadExecutor;
        this.postThreadExecutor = postThreadExecutor;
    }

    protected void execute() {
        threadExecutor.execute(this);
    }

    protected void postExecute(Runnable runnable) {
        postThreadExecutor.run(runnable);
    }
}
