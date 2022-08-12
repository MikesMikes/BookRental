package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.converters.AuthorIdToAuthorConverter;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    private final String CREATEORUPDATEFORM = "publisher/createOrUpdatePublisherForm";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PublisherService publisherService;

    @MockBean
    AuthorIdToAuthorConverter authorIdToAuthorConverter;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/publisher/findall"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("publishers"))
                .andExpect(view().name("publisher/findall"));
    }


    @Test
    void creatPublisher() throws Exception {
        mockMvc.perform(get("/publisher/new"))
                .andExpect(model().attributeExists("publisher"))
                .andExpect(view().name(CREATEORUPDATEFORM));
    }

    @Test
    void processCreatePublisher() {
    }

    @Test
    void updatePublisher() {
    }

    @Test
    void processUpdatePublisher() {
    }

    @Test
    void processDeletePublisher() {
    }
}