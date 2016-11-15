package spinillos.dagger_espresso.domain.interactor;

import android.graphics.Bitmap;

import javax.inject.Inject;

import spinillos.dagger_espresso.data.repository.PictureRepository;
import spinillos.dagger_espresso.framework.executor.PostThreadExecutor;
import spinillos.dagger_espresso.framework.executor.ThreadExecutor;

/**
 * Created by Selene on 13/11/16.
 */

public class SavePictureUseCase extends UseCase {

    private Bitmap bitmap;
    private PictureRepository repository;

    @Inject
    public SavePictureUseCase(
            ThreadExecutor threadExecutor,
            PostThreadExecutor postThreadExecutor,
            PictureRepository repository) {
        super(threadExecutor, postThreadExecutor);
        this.repository = repository;
    }

    public void savePicture(Bitmap bitmap) {
        this.bitmap = bitmap;
        execute();
    }

    @Override
    public void run() {

    }
}
