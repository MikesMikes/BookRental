package mikesmikes.github.bookpublishing.repositories;

import mikesmikes.github.bookpublishing.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
