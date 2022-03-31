package mikesmikes.github.bookpublishing.repositories;

import mikesmikes.github.bookpublishing.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
