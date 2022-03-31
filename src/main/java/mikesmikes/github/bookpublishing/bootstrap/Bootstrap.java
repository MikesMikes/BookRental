package mikesmikes.github.bookpublishing.bootstrap;

import mikesmikes.github.bookpublishing.SpringTutorialsApplication;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringTutorialsApplication.class);
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    public void run() throws Exception {
        run();
    }

    @Override
    public void run(String... args) throws Exception {



        Publisher publisher1 = new Publisher();
        publisher1.setName("London Book Publisher");
        publisherRepository.save(publisher1);

        Author author1 = new Author("Jemi", "Jlshong");
        Book book1 = new Book();
        book1.setName("First book");
        book1.setPublisher(publisher1);
        author1.getBooks().add(book1);
        publisher1.getBooks().add(book1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        bookRepository.findAll().forEach(i -> System.out.println(i.toString() + " " + i.getPublisher().getName()));
    }
}
