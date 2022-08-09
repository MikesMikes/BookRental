package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.repositories.AuthorRepository;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;

    @MockBean
    AuthorRepository authorRepository;

    @Test
    void getIndex() throws Exception {
        mockMvc.perform(get("/author/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("/author/findall"))
                .andExpect(model().attributeExists("authors"));
    }

    @Test
    void testInitNewAuthor() throws Exception {
        mockMvc.perform(get("/author/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author/createOrUpdateAuthor"));
    }

    @Test
    void testInitUpdateAuthor() throws Exception {
        when(authorService.findById(anyLong())).thenReturn(Author.builder().id(1L).build());

        mockMvc.perform(get("/author/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/createOrUpdateAuthor"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    void processUpdateAuthor() throws Exception {
        Author author = Author.builder().id(1L).firstName("James").lastName("Dean").build();
        when(authorService.save(any())).thenReturn(author);

        mockMvc.perform(post("/author/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("firstName", "James")
                        .param("lastName", "Dean")
                ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/author/findall"));

        verify(authorService, times(1)).save(any());
    }

    @Test
    void processUpdateAuthorValidationFail() throws Exception {
        when(authorService.save(any())).thenReturn(Author.builder().id(1L).build());

        mockMvc.perform(post("/author/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "")
                        .param("lastName", ""))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("author/createOrUpdateAuthorForm"));

    }
}