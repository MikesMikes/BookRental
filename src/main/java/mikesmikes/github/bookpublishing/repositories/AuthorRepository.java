package mikesmikes.github.bookpublishing.repositories;

import mikesmikes.github.bookpublishing.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByLastName(String lastName);

}
