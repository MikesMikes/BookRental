package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PublisherController {

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
}
