package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class PublisherController {

    private final String CREATEORUPDATEFORM = "publisher/createOrUpdatePublisherForm";
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
    public String creatPublisher(Model model){

        model.addAttribute("publisher", new Publisher());

        return CREATEORUPDATEFORM;
    }

    @PostMapping("/publisher/new")
    public String processCreatePublisher(Publisher publisher){

        publisherService.save(publisher);

        return "redirect:/publisher/findAll";
    }


    @GetMapping("/publisher/{id}/update")
    public String updatePublisher(@PathVariable("id") Long id, Model model) {

        model.addAttribute("publisher", publisherService.findById(id));

        return CREATEORUPDATEFORM;
    }

    @PostMapping("/publisher/{id}/update")
    public String processUpdatePublisher(@PathVariable("id") Long id, Publisher publisher){

        publisher.setId(id);
        publisherService.save(publisher);

        return "redirect:/publisher/findAll";
    }
}
