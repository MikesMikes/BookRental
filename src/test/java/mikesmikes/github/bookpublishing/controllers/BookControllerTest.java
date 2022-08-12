package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.converters.AuthorIdToAuthorConverter;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.util.*;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private final String REDIRECTBOOKFINDALL = "redirect:/book/findall";
    private final String CREATEUPDATEFORM = "book/createOrUpdateBookForm";
    private final Book BOOK_VALID = Book.builder().id(1L).name("Test").build();
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
    void processCreateBookFail() throws Exception {

        mockMvc.perform(post("/book/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", ""))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name(CREATEUPDATEFORM));
    }

    @Test
    void bookUpdateFormGet() throws Exception {
        when(bookService.findById(anyLong())).thenReturn(BOOK_VALID);

        mockMvc.perform(get("/book/1/update"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("publishers"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(view().name(CREATEUPDATEFORM));

        verify(bookService, times(1)).findById(anyLong());
    }

    @Test
    void processBookUpdateFormFail() throws Exception {
        mockMvc.perform(post("/book/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", ""))
                .andExpect(view().name(CREATEUPDATEFORM))
                .andExpect(status().is2xxSuccessful());

        verify(bookService, times(0)).save(any());
    }

    @Test
    void processBookUpdateValid() throws Exception {
        mockMvc.perform(post("/book/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "somename"))
                .andExpect(view().name(REDIRECTBOOKFINDALL))
                .andExpect(status().is3xxRedirection());

        verify(bookService, times(1)).save(any());
    }

    @Test
    void processBookDelete() throws Exception {


        mockMvc.perform(get("/book/1/delete"))
                .andExpect(view().name(REDIRECTBOOKFINDALL));

        verify(bookService, times(1)).deleteById(any());
    }
}