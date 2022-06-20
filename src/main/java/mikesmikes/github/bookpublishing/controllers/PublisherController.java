package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PublisherController {

    private final String CREATEORUPDATEFORM = "createOrUpdatePublisherForm";
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher/findall")
    public String findAll(Model model){
        log.info("findAll - ");

        model.addAttribute("publishers", publisherService.findAll());


        log.info("findAll - end");
        return "publisher/findall";
    }

    @GetMapping("/publisher/new")
    public String createAuthor(Model model){

        model.addAttribute("publisher", new Publisher());

        return CREATEORUPDATEFORM;
    }
}
