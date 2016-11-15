package spinillos.dagger_espresso.domain.mapper;

/**
 * Created by Selene on 13/11/16.
 */

public interface Mapper<R, M> {

    M resourceToModel(R resource);

    R modelToResource(M model);
}
