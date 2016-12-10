package spinillos.dagger_espresso.domain.mapper;

import javax.inject.Inject;

import spinillos.dagger_espresso.data.entity.PictureResource;
import spinillos.dagger_espresso.presentation.main.model.Picture;

public class PictureMapper implements Mapper<PictureResource, Picture> {

    @Inject
    public PictureMapper() {

    }

    @Override
    public Picture resourceToModel(PictureResource resource) {
        Picture picture = new Picture();
        picture.setPath(resource.getPath());
        return picture;
    }

    @Override
    public PictureResource modelToResource(Picture model) {
        PictureResource resource = new PictureResource();
        resource.setPath(model.getPath());
        return resource;
    }
}
