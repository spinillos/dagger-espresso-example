package spinillos.dagger_espresso.domain.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import spinillos.dagger_espresso.data.entity.PictureResource;
import spinillos.dagger_espresso.data.repository.PictureRepository;
import spinillos.dagger_espresso.domain.exception.NoDataException;
import spinillos.dagger_espresso.domain.mapper.PictureMapper;
import spinillos.dagger_espresso.framework.executor.PostThreadExecutor;
import spinillos.dagger_espresso.framework.executor.ThreadExecutor;
import spinillos.dagger_espresso.presentation.main.model.Picture;

public class GetPicturesUseCase extends UseCase {

    private PictureRepository repository;

    private PictureMapper mapper;

    private Callback callback;

    @Inject
    public GetPicturesUseCase(
            ThreadExecutor threadExecutor,
            PostThreadExecutor postThreadExecutor,
            PictureRepository repository,
            PictureMapper mapper) {
        super(threadExecutor, postThreadExecutor);
        this.repository = repository;
        this.mapper = mapper;
    }

    public void fetchPictures(final Callback callback) {
        this.callback = callback;
        execute();
    }

    @Override
    public void run() {
        this.repository.getPictures(new PictureRepository.PictureCallback() {
            @Override
            public void onSuccess(List<PictureResource> resources) {
                handleOnSuccess(resources);
            }

            @Override
            public void onError(final Throwable throwable) {
                postExecute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(throwable);
                    }
                });
            }
        });

    }

    private void handleOnSuccess(List<PictureResource> resources) {
        if (resources.isEmpty()) {
            postExecute(new Runnable() {
                @Override
                public void run() {
                    callback.onError(new NoDataException());
                }
            });
        }

        final List<Picture> pictures = new ArrayList<>();
        for (PictureResource resource : resources) {
            pictures.add(mapper.resourceToModel(resource));
        }

        postExecute(new Runnable() {
            @Override
            public void run() {
                callback.onPicturesLoaded(pictures);
            }
        });
    }

    public interface Callback {

        void onPicturesLoaded(List<Picture> pictures);

        void onError(Throwable throwable);
    }
}
