package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthorControllerTest {


    MockMvc mockMvc;

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void getIndex() throws Exception {
        mockMvc.perform(get("/author/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/index"))
                .andExpect(model().attributeExists("authors"));

    }
}