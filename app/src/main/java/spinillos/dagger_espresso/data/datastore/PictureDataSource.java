package spinillos.dagger_espresso.data.datastore;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import spinillos.dagger_espresso.data.entity.PictureResource;
import spinillos.dagger_espresso.data.exception.ResourceException;
import spinillos.dagger_espresso.framework.Constants;

/**
 * Created by Selene on 13/11/16.
 */

public class PictureDataSource implements DataSource<List<PictureResource>> {

    @Override
    public void fetchData(ResourceCallback<List<PictureResource>> callback) {

        List<PictureResource> resources = new ArrayList<>();

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath();

        File root = new File(path);
        if (root.isDirectory()) {
            for (File file : root.listFiles(filter())) {
                PictureResource resource = new PictureResource();
                resource.setPath(file.getAbsolutePath());
                resources.add(resource);
            }

            callback.onResourcesLoaded(resources);
        } else {
            callback.onError(new ResourceException());
        }

    }

    private FilenameFilter filter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.startsWith(Constants.PICTURE_PREFIX) && name
                        .endsWith(Constants.PICTURE_EXTENSION);
            }
        };
    }
}
