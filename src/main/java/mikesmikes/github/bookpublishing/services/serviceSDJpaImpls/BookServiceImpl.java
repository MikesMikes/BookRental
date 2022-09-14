package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.controllers.exceptions.NotFoundExceptionHandler;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.repositories.PublisherRepository;
import mikesmikes.github.bookpublishing.services.BookService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Set<Book> findAll() {
        return new HashSet<>(bookRepository.findAll());
    }

    /**
     * Returns a Book object if found else throws a RunTimeException through NotFoundExceptionHandler.
     *
     * @param id of Book object
     * @return Book object
     */
    @Override
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new NotFoundExceptionHandler("Book ID not for ID: " + id);
        }

        return book.get();
    }

    @Override
    public Book save(Book object) {

        if (object.getId() == null) {
            log.info("null");
            object.getAuthors().forEach(i -> {
                i.getBooks().add(object);
            });
            return bookRepository.save(object);
        } else {
            Book book = bookRepository.findById(object.getId()).get();
            book.setName(object.getName());
            if (object.getPublisher() != null) {
                log.info("publisher: " + object.getPublisher());
                book.setPublisher(object.getPublisher());
            } else {
                book.setPublisher(null);
            }
            if (!object.getAuthors().isEmpty()) {
                if (object.getAuthors().contains(null)) {
                    log.info("contains null");
                    book.getAuthors().forEach(i -> {
                        i.getBooks().clear();
                    });
                } else {
                    object.getAuthors().forEach(i -> i.getBooks().add(book));
                }
            }

            return bookRepository.save(book);
        }
    }

    @Override
    public void delete(Book object) {
        bookRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(null);

        book.setAuthors(null);
        book.setPublisher(null);

        authorRepository.findAll().forEach(i -> {
            if (i.getBooks().contains(book)) {
                i.getBooks().remove(book);
            }
        });

        bookRepository.deleteById(book.getId());
    }

    @Override
    public void saveAll(List<Book> books) {
        bookRepository.saveAll(books);
    }
}
