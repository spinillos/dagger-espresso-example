package spinillos.dagger_espresso;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by Selene on 07/11/16.
 */

public class EspressoRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, EspressoApplicationForTest.class.getName(), context);
    }
}
