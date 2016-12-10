package spinillos.dagger_espresso.presentation.main.di;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import spinillos.dagger_espresso.data.datastore.DataSource;
import spinillos.dagger_espresso.data.datastore.PictureDataSource;
import spinillos.dagger_espresso.data.entity.PictureResource;

/**
 * Created by Selene on 13/11/16.
 */

@Module
public class PictureModule {

    @Provides
    DataSource<List<PictureResource>> providesPictureDataStore() {
        return new PictureDataSource();
    }
}
