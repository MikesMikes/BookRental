package mikesmikes.github.bookpublishing.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.ALL})
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    private Publisher publisher;
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    //getters setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "name='" + name + '\'' +
//                ", publisher=" + publisher +
//                '}';
//    }


    @Override
    public String toString() {
        return "Book{" +
                "authors=" + authors +
                ", publisher=" + publisher +
                ", name='" + name + '\'' +
                '}';
    }
}
