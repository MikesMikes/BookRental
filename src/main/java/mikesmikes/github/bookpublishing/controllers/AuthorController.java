package mikesmikes.github.bookpublishing.controllers;

import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/author/index")
    public String getIndex(Model model) {

        model.addAttribute("authors", authorService.findAll());

        return "author/index";
    }

}
