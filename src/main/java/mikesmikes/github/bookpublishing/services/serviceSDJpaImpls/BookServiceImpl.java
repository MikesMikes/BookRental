package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.services.BookService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Book> findAll() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
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
        bookRepository.deleteById(id);
    }
}
//[Book{authors=[Author{firstName='Terry', lastName='Jones'}, Author{firstName='Mike', lastName='Kelly'}], publisher=null, name='Don Doxuitosss'}];
//[Book{authors=[Author{firstName='Terry', lastName='Jones'}, Author{firstName='Mike', lastName='Kelly'}], publisher=Publisher{name='London Book Publishing'}, name='Don Doxuitosss'}