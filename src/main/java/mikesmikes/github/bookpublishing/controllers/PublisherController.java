package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.domain.Publisher;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class PublisherController {

    private final String INDEX = "publisher/findall";
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
    public String processCreatePublisher(@Valid Publisher publisher, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return CREATEORUPDATEFORM;
        }
        publisherService.save(publisher);

        return "redirect:/publisher/findAll";
    }


    @GetMapping("/publisher/{id}/update")
    public String updatePublisher(@PathVariable("id") Long id, Model model) {

        model.addAttribute("publisher", publisherService.findById(id));

        return CREATEORUPDATEFORM;
    }

    @PostMapping("/publisher/{id}/update")
    public String processUpdatePublisher(@PathVariable("id") Long id, @Valid Publisher publisher, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return CREATEORUPDATEFORM;
        }

        publisher.setId(id);
        System.out.println(publisher.getId());
        publisherService.save(publisher);

        return "redirect:/publisher/findall";
    }
}
