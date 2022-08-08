package mikesmikes.github.bookpublishing.services;

import mikesmikes.github.bookpublishing.domain.Book;

import java.util.List;

public interface BookService extends CrudService<Book, Long> {

    void saveAll(List<Book> books);
}
