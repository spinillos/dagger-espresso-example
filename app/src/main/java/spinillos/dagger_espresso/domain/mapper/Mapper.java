package spinillos.dagger_espresso.domain.mapper;

public interface Mapper<R, M> {

    M resourceToModel(R resource);

    R modelToResource(M model);
}
