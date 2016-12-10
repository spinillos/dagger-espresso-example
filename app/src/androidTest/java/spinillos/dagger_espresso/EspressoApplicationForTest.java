package spinillos.dagger_espresso;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

import spinillos.dagger_espresso.presentation.di.component.activity.ActivityComponentBuilder;

public class EspressoApplicationForTest extends EspressoApplication {

    public void putComponent(Class<? extends Activity> activity, ActivityComponentBuilder builder) {
        Map<Class<? extends Activity>, ActivityComponentBuilder> map = new HashMap<>(componentBuilder);
        map.put(activity, builder);
        componentBuilder = map;
    }
}
