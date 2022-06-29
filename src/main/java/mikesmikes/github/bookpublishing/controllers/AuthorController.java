package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final String CREATEORUPDATEFORM = "author/createOrUpdateAuthor";

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

    @GetMapping("/author/new")
    public String createOrUpdateForm(Model model) {
        log.info("createOrUpdateForm - ");

        model.addAttribute("author", new Author());

        log.info("createOrUpdateForm - end");
        return CREATEORUPDATEFORM;
    }

    @PostMapping("/author/new")
    public String processCreateAuthor(Author author) {
        authorService.save(author);
        return "redirect:/author/index";
    }

    @GetMapping("/author/{id}/update")
    public String updateAuthor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("author", authorService.findById(id));

        return CREATEORUPDATEFORM;
    }

    @PostMapping("/author/{id}/update")
    public String processAuthorUpdate(@PathVariable("id") Long id, Author author){
        author.setId(id);
        authorService.save(author);
        return "redirect:/";
    }
}
