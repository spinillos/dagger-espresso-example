package spinillos.dagger_espresso.activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.presentation.main.MainActivity;
import spinillos.dagger_espresso.presentation.menu.MenuActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MenuActivityTest {

    @Rule
    public IntentsTestRule<MenuActivity> menuActivity = new IntentsTestRule<>(
            MenuActivity.class, true, false);

    @Test
    public void cameraMenuButtonOpensMainActivity() {

        menuActivity.launchActivity(null);

        onView(withId(R.id.button_camera_example)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

}