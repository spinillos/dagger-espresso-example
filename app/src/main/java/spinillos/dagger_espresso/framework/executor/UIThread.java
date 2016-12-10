package spinillos.dagger_espresso.framework.executor;

import android.os.Handler;
import android.os.Looper;

public class UIThread implements PostThreadExecutor {

    private final Handler handler;

    public UIThread() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run(Runnable runnable) {
        handler.post(runnable);
    }
}
