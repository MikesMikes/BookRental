package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Author;
import mikesmikes.github.bookpublishing.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Slf4j
@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final String INDEX = "/author/findall";
    private final String CREATEORUPDATEFORM = "author/createOrUpdateAuthorForm";

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/author/index")
    public String getIndex(Model model) {
        log.info("getIndex - ");
        log.info(authorService.findAll().toString());

        model.addAttribute("authors", authorService.findAll());

        log.info("getIndex - end");
        return INDEX;
    }

    @GetMapping("/author/new")
    public String createOrUpdateForm(Model model) {
        log.info("createOrUpdateForm - ");

        model.addAttribute("author", new Author());

        log.info("createOrUpdateForm - end");
        return CREATEORUPDATEFORM;
    }

    @PostMapping("/author/new")
    public String processCreateAuthor(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return CREATEORUPDATEFORM;
        }
        authorService.save(author);
        return "redirect:/author/index";
    }

    @GetMapping("/author/{id}/update")
    public String updateAuthor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("author", authorService.findById(id));

        return CREATEORUPDATEFORM;
    }

    @PostMapping("/author/{id}/update")
    public String processAuthorUpdate(@PathVariable("id") Long id,@Valid Author author, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.info("has errors :" + bindingResult.getAllErrors().toString());
            return CREATEORUPDATEFORM;
        } else {
            author.setId(id);
            authorService.save(author);
            return "redirect:" + INDEX;
        }
    }

    @RequestMapping("/author/{id}/delete")
    public String processAuthorDelete(@PathVariable("id") Long id){

        authorService.deleteById(id);

        return "redirect:/author/index";
    }
}
