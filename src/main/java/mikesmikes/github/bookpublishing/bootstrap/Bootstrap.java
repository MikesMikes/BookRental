package mikesmikes.github.bookpublishing.bootstrap;

import mikesmikes.github.bookpublishing.BookDatabaseApp;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BookDatabaseApp.class);
    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;


    public Bootstrap(AuthorService authorService, BookService bookService, PublisherService publisherService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    public void run() throws Exception {
        run();
    }

    @Override
    public void run(String... args) throws Exception {

        Set<Author> authorSet = authorService.findAll();
        Set<Publisher> publisherSet = publisherService.findAll();
        Set<Book> bookSet = bookService.findAll();

        Publisher publisher1 = new Publisher("London Book Publishing");
        publisher1.setAddress("123 Queensland Ave, London");


        Author author1 = new Author("Mike", "Kelly");
        Author author2 = new Author("Terry", "Jones");
        Author author3 = new Author("James", "Carling");
        List<Author> authors = Arrays.asList(author1, author2, author3);


        Book book1 = new Book("Don Doxuito");
        Book book2 = new Book("Arlington Major");
        List<Book> books = Arrays.asList(book1, book2);

        if (!authorSet.containsAll(authors)) {
            publisherService.save(publisher1);
            authorService.saveAll(authors);
            bookService.saveAll(books);

            author1.getBooks().add(book1);
            author2.getBooks().add(book1);
            book1.setPublisher(publisher1);

            bookService.save(book2);
            authorService.saveAll(authors);
        }


    }
}
