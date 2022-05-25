package mikesmikes.github.bookpublishing.services;

import mikesmikes.github.bookpublishing.domain.Author;

import java.util.List;

public interface AuthorService extends CrudService<Author, Long> {

    public void saveAll(List<Author> authorList);

}
