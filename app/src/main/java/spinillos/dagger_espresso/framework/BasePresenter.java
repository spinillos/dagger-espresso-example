package spinillos.dagger_espresso.framework;

import java.lang.ref.WeakReference;

/**
 * Created by Selene on 06/11/16.
 */

public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> view;

    public void setView(V view) {
        this.view = new WeakReference<>(view);
    }

    public V getView() {
        return view.get();
    }

    public abstract void resume();
}
