package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Set<Author> findAll() {
        Set<Author> authorSet = new HashSet<>();
        authorRepository.findAll().forEach(authorSet::add);
        return authorSet;
    }

    @Override
    public void saveAll(List<Author> authorList) {
        authorRepository.saveAll(authorList);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author save(Author object) {
        log.info("" + object.getId());
        if (authorRepository.existsById(object.getId())) {
            Author author = authorRepository.findById(object.getId()).get();
            author.setFirstName(object.getFirstName());
            author.setLastName(object.getLastName());
            authorRepository.save(author);
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
