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
        publisherService.save(publisher1);

        Author author = new Author("Mike", "Kelly");
//        authorService.save(author);

        Book book = new Book();
        book.setName("Don Doquito");
        book.setPublisher(publisher1);

        author.addBook(book);

        authorService.save(author);

        authorService.findAll().forEach(i -> System.out.println(i));
        bookService.findAll().forEach(i -> System.out.println(i));
        publisherService.findAll().forEach(publisher -> System.out.println(publisher));

    }
}
