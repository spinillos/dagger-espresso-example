package spinillos.dagger_espresso.presentation.main.di;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import spinillos.dagger_espresso.data.datastore.DataSource;
import spinillos.dagger_espresso.data.datastore.PictureDataSource;
import spinillos.dagger_espresso.data.entity.PictureResource;

@Module
public class PictureModule {

    @Provides
    DataSource<List<PictureResource>> providesPictureDataStore() {
        return new PictureDataSource();
    }
}
