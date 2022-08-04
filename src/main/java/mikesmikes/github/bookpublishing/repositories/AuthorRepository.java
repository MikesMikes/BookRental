package mikesmikes.github.bookpublishing.repositories;

import mikesmikes.github.bookpublishing.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByLastName(String lastName);

}
