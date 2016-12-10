package spinillos.dagger_espresso.presentation.main;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.ArrayList;
import java.util.List;

import spinillos.dagger_espresso.EspressoApplicationForTest;
import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.TestUtils;
import spinillos.dagger_espresso.domain.exception.NoDataException;
import spinillos.dagger_espresso.domain.interactor.GetPicturesUseCase;
import spinillos.dagger_espresso.presentation.di.module.ActivityModule;
import spinillos.dagger_espresso.presentation.main.di.MainComponent;
import spinillos.dagger_espresso.presentation.main.model.Picture;
import spinillos.dagger_espresso.presentation.main.utils.PictureUtils;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static spinillos.dagger_espresso.matcher.RecyclerViewMatcher.withNumberOfItems;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String PICTURE_PATH
            = "/storage/emulated/0/Pictures/espresso_20161117_231613_321043582.jpg";

    @Mock
    MainComponent.Builder builder;

    @Mock
    GetPicturesUseCase picturesUseCase;

    @Mock
    PictureUtils pictureUtils;

    private MainComponent component = new MainComponent() {
        @Override
        public void injectMembers(MainActivity activity) {
            activity.presenter = new MainPresenter(picturesUseCase, pictureUtils);
        }
    };

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public IntentsTestRule<MainActivity> mainActivity = new IntentsTestRule<>(
            MainActivity.class, true, false);

    @Before
    public void setUp() {

        TestUtils.grantPermission(Manifest.permission.CAMERA);
        TestUtils.grantPermission(Manifest.permission.SET_ANIMATION_SCALE);

        when(builder.activityModule(any(ActivityModule.class))).thenReturn(builder);
        when(builder.build()).thenReturn(component);

        ((EspressoApplicationForTest) getTargetContext()
                .getApplicationContext())
                .putComponent(MainActivity.class, builder);
    }

    @Test
    public void showEmptyViewWhenNoPictures() {

        respondWithNoData();

        mainActivity.launchActivity(null);

        onView(withId(R.id.empty_view)).check(matches(isDisplayed()));

    }

    @Test
    public void showFilledList() {

        int items = 2;

        respondWithAListOfItems(items);

        mainActivity.launchActivity(null);

        onView(withId(R.id.empty_view)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recyclerView)).check(matches(withNumberOfItems(items)));
    }

    @Test
    public void testRealCamera() {
        Assume.assumeTrue(TestUtils.hasFeature(PackageManager.FEATURE_CAMERA));

        respondWithNoData();

        when(pictureUtils.saveBitmapToFile(any(Bitmap.class))).thenReturn(
                Uri.parse(PICTURE_PATH));

        mainActivity.launchActivity(null);

        onView(withId(R.id.button_camera)).perform(click());

        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));

        onView(withId(R.id.recyclerView)).check(matches(withNumberOfItems(1)));
    }

    @Test
    public void testCamera() {

        respondWithNoData();

        when(pictureUtils.saveBitmapToFile(any(Bitmap.class))).thenReturn(
                Uri.parse(PICTURE_PATH));

        mainActivity.launchActivity(null);

        Bitmap icon = BitmapFactory.decodeResource(
                getTargetContext().getResources(),
                R.mipmap.ic_launcher);

        Intent data = new Intent();
        data.putExtra("data", icon);

        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(
                Activity.RESULT_OK, data);

        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);

        onView(withId(R.id.button_camera)).perform(click());

        onView(withId(R.id.recyclerView)).check(matches(withNumberOfItems(1)));
    }

    private void respondWithNoData() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetPicturesUseCase.Callback) invocation.getArguments()[0])
                        .onError(new NoDataException());
                return null;
            }
        }).when(picturesUseCase).fetchPictures(any(GetPicturesUseCase.Callback.class));
    }

    private void respondWithAListOfItems(final int numberOfItems) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetPicturesUseCase.Callback) invocation.getArguments()[0])
                        .onPicturesLoaded(createAListWithNumberOfPictures(numberOfItems));
                return null;
            }
        }).when(picturesUseCase).fetchPictures(any(GetPicturesUseCase.Callback.class));
    }

    private List<Picture> createAListWithNumberOfPictures(int num) {
        List<Picture> list = new ArrayList<>();

        String path
                = "/storage/emulated/0/Pictures/espresso_20161117_231613_321043582.jpg";

        for (int i = 0; i < num; i++) {
            Picture picture = new Picture();
            picture.setPath(path);
            list.add(picture);
        }

        return list;
    }
}