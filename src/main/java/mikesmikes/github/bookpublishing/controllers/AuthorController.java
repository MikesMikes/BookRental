package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/author/index")
    public String getIndex(Model model) {
        log.info("getIndex - ");

        model.addAttribute("authors", authorService.findAll());

        log.info("getIndex - end");
        return "author/index";
    }

}
