package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.controllers.exceptions.NotFoundExceptionHandler;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookRepository bookRepository1) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository1;
    }

    @Override
    public Set<Author> findAll() {
        Set<Author> authorSet = new HashSet<>(authorRepository.findAll());
        return authorSet;
    }

    @Override
    public void saveAll(List<Author> authorList) {
        authorRepository.saveAll(authorList);
    }


    /**
     * Returns an Author object if found else throws a RunTimeException through NotFoundExceptionHandler.
     *
     * @param id
     * @return Author object
     */
    @Override
    public Author findById(Long id) {

        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            throw new NotFoundExceptionHandler("Author not found for ID: " + id);
        }

        return author.get();
    }

    @Override
    public Author save(Author object) {

        if (object.getId() == null) {
            return authorRepository.save(object);
        }

        if (authorRepository.existsById(object.getId())) {
            Author author = authorRepository.findById(object.getId()).get();
            author.setFirstName(object.getFirstName());
            author.setLastName(object.getLastName());

            return authorRepository.save(author);
        }
        return authorRepository.save(object);
    }

    @Override
    public void delete(Author object) {

        authorRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
