package mikesmikes.github.bookpublishing.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mikesmikes.github.bookpublishing.converters.AuthorIdToAuthorConverter;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    private final String CREATEORUPDATEFORM = "publisher/createOrUpdatePublisherForm";
    private final String REDIRECT_FINDALL = "redirect:/publisher/findall";


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

    @Test
    void processCreatePublisherValidationPass() throws Exception {
        mockMvc.perform(post("/publisher/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "some name")
                        .param("address", "some name"))
                .andExpect(view().name(REDIRECT_FINDALL));

        verify(publisherService, times(1)).save(any());
    }

    @Test
    void updatePublisherGetForm() throws Exception {
        Publisher publisherInvalid = Publisher.builder().id(1L).name("asdasd").build();
        given(publisherService.findById(any())).willReturn(publisherInvalid);

        mockMvc.perform(get("/publisher/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name(CREATEORUPDATEFORM))
                .andExpect(model().attributeExists("publisher"));

        verify(publisherService, times(1)).findById(any());
    }

    @Test
    void processUpdatePublisherValidationFail() throws Exception {
        mockMvc.perform(post("/publisher/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("address", ""))
                .andExpect(view().name(CREATEORUPDATEFORM));

        verify(publisherService, times(0)).save(any());
    }

    @Test
    void processUpdatePublisherValidationPass() throws Exception {
        mockMvc.perform(post("/publisher/1/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "a")
                        .param("address", "b"))
                .andExpect(view().name(REDIRECT_FINDALL));

        verify(publisherService, times(1)).save(any());
    }

    @Test
    void processDeletePublisher() {
    }

    public static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}