package mikesmikes.github.bookpublishing.services.serviceSDJpaImpls;

import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.repositories.BookRepository;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

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
