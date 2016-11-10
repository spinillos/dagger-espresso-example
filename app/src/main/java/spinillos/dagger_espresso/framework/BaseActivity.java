package spinillos.dagger_espresso.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import spinillos.dagger_espresso.EspressoApplication;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;

/**
 * Created by Selene on 05/11/16.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>>
        extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        injectDependencies();
        presenter = getPresenter();
        presenter.setView(getView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void injectDependencies();

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public abstract V getView();

    public abstract P getPresenter();

    public ActivityComponentBuilder getComponentBuilder() {
        return ((EspressoApplication) getApplication())
                .getActivityComponent(this.getClass());
    }
}
