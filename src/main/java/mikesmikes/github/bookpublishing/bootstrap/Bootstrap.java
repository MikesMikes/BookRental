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

        Publisher publisher1 = new Publisher("London Book Publishing");
        publisher1.setAddress("123 Queensland Ave, London");
        publisherService.save(publisher1);

        Author author1 = new Author("Mike", "Kelly");
        Author author2 = new Author("Terry", "Jones");
        Author author3 = new Author("James", "Carling");
        List<Author> authors = Arrays.asList(author1, author2, author3);

        Book book1 = new Book("Don Doxuito");
        Book book2 = new Book("Arlington Major");

        author1.getBooks().add(book1);
        author2.getBooks().add(book1);
        book1.setPublisher(publisher1);

        bookService.save(book2);
        authorService.saveAll(authors);

//        authorService.findAll().forEach(i -> System.out.println(i));
//        bookService.findAll().forEach(i -> System.out.println(i));
//        publisherService.findAll().forEach(publisher -> System.out.println(publisher));

    }
}
