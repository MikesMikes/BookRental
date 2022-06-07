package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.services.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PublisherControllerTest {

    MockMvc mockMvc;

    @Mock
    PublisherService publisherService;

    @InjectMocks
    PublisherController publisherController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/publisher/findall"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("publishers"))
                .andExpect(view().name("publisher/findall"));
    }
}