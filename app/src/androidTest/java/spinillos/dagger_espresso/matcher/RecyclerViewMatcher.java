package spinillos.dagger_espresso.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Selene on 13/11/16.
 */

public class RecyclerViewMatcher {

    public static Matcher<View> withNumberOfItems(final int numberOfItems) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                int items = item.getAdapter().getItemCount();
                return items == numberOfItems;
            }
        };
    }
}
