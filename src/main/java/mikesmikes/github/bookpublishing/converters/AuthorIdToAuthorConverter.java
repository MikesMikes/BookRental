package mikesmikes.github.bookpublishing.converters;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuthorIdToAuthorConverter implements Converter<String, Author> {

    private final AuthorRepository authorRepository;

    public AuthorIdToAuthorConverter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author convert(String id) {
        log.info("Author convert: "+id);
        if (Integer.valueOf(id) == -1){
            return null;
        }
        Optional<Author> authorOptional = authorRepository.findById(Long.valueOf(id));
        Author author = null;
        if (authorOptional.isPresent()){
            author = authorOptional.get();
        }
        System.out.println(author.toString());
        return author;
    }
}
