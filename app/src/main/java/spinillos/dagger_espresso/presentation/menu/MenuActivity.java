package spinillos.dagger_espresso.presentation.menu;

import javax.inject.Inject;

import butterknife.OnClick;
import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.framework.BaseActivity;
import spinillos.dagger_espresso.presentation.Navigator;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.menu.di.MenuComponent;

public class MenuActivity extends BaseActivity<MenuView, MenuPresenter> implements MenuView {

    @Inject
    Navigator navigator;

    @Inject
    MenuPresenter presenter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_menu;
    }

    @Override
    public void injectDependencies() {
        ((MenuComponent.Builder) getComponentBuilder())
                .activityModule(new ActivityModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    public MenuView getView() {
        return this;
    }

    @Override
    public MenuPresenter createPresenter() {
        return presenter;
    }

    @OnClick(R.id.button_camera_example)
    public void onCameraClicked() {
        navigator.goToMainActivity(this);
    }
}
