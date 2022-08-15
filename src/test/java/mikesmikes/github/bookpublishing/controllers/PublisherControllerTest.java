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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    private final String CREATEORUPDATEFORM = "publisher/createOrUpdatePublisherForm";
    private final String REDIRECT_FINDALL = "redirect:/publisher/findAll";
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
    void processCreatePublisherFail() throws Exception {
        mockMvc.perform(post("/publisher/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("address", ""))
                .andExpect(view().name(CREATEORUPDATEFORM));

        verify(publisherService, times(0)).save(any());
    }

//    @Test
//    void processCreatePublisherValidationPass() throws Exception {
//        mockMvc.perform(post("/publisher/new")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("name", "")
//                        .param("address", ""))
//                .andExpect(view().name(CREATEORUPDATEFORM));
//    }

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