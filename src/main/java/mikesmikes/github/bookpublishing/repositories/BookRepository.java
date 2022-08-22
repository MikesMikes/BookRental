package mikesmikes.github.bookpublishing.repositories;

import mikesmikes.github.bookpublishing.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
