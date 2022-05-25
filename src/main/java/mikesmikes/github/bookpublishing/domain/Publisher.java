package mikesmikes.github.bookpublishing.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishers")
public class Publisher extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                '}';
    }
}
