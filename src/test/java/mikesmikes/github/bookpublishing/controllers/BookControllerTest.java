package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.converters.AuthorIdToAuthorConverter;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.RequestResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private final String REDIRECTBOOKFINDALL = "redirect:/book/findall";
    private final String CREATEUPDATEFORM = "book/createOrUpdateBookForm";
    private final Book bookValid = Book.builder().id(1L).name("Test").build();
    String bookName = "Some name";

    @MockBean
    BookService bookService;

    @MockBean
    AuthorService authorService;

    @MockBean
    PublisherService publisherService;

    @MockBean
    AuthorIdToAuthorConverter authorIdToAuthorConverter;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/book/findall"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/findall"))
                .andExpect(model().attributeExists("books"));
    }


    @Test
    void createBookForm() throws Exception {
        mockMvc.perform(get("/book/new"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("publishers"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(view().name(CREATEUPDATEFORM));
    }

    @Test
    void processCreateBook() throws Exception {

        mockMvc.perform(post("/book/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "name"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECTBOOKFINDALL));
    }



    @Test
    void bookUpdate() {
    }

    @Test
    void processBookUpdate() {
    }

    @Test
    void processBookDelete() {
    }
}