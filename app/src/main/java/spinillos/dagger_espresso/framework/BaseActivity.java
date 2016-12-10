package spinillos.dagger_espresso.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import spinillos.dagger_espresso.EspressoApplication;
import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>>
        extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        setupViews();
        injectDependencies();
        initializePresenter();
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void injectDependencies();

    protected void setupViews() {

    }

    public abstract V getView();

    public abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }

    public ActivityComponentBuilder getComponentBuilder() {
        return ((EspressoApplication) getApplication())
                .getActivityComponent(this.getClass());
    }

    private void initializePresenter() {
        if (presenter == null) {
            presenter = createPresenter();
        }

        presenter.setView(getView());
        presenter.initialize();
    }
}
