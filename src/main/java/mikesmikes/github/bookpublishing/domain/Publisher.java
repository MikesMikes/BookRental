package mikesmikes.github.bookpublishing.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Table(name = "publishers")
@Entity
public class Publisher extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Cannot not be left Blank or Empty")
    private String name;
    @NotBlank(message = "Cannot not be left Blank or Empty")
    private String address;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
