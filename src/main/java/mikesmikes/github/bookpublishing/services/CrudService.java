package mikesmikes.github.bookpublishing.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById();

    T save();

    void delete();

    void deleteById();
}
