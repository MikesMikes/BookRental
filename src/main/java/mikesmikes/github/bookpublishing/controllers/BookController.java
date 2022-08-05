package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import mikesmikes.github.bookpublishing.services.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final String CREATEUPDATEFORM = "book/createOrUpdateBookForm";

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping("/book/findall")
    public String findAll(Model model) {
        log.info("findAll - ");

        model.addAttribute("books", bookService.findAll());
        bookService.findAll().forEach(i -> {
            System.out.println(i.getAuthors().toString());
        });
        log.info("findAll - end");
        return "book/findall";
    }

    @RequestMapping("/book/new")
    public String createBook(Model model){

        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());

        return CREATEUPDATEFORM;
    }

    @PostMapping("book/new")
    public String processCreateBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        log.info("processCreateBook - ");
        if (bindingResult.hasErrors()){
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return CREATEUPDATEFORM;
        }
        bookService.save(book);

        return "redirect:/book/findall";
    }

    @GetMapping("/book/{id}/update")
    public String bookUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());

        return CREATEUPDATEFORM;
    }

    @PostMapping("/book/{id}/update")
    public String processBookUpdate(@PathVariable("id") Long id, @Valid Book book, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            log.info("Has errors: " + bindingResult.getAllErrors().toString());
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return CREATEUPDATEFORM;
        }
        log.info("book: " + book.toString());
        log.info("publisher: "+ book.getPublisher());
        bookService.save(book);

        return "redirect:/book/findall";
    }

    @RequestMapping("/book/{id}/delete")
    public String processBookDelete(@PathVariable("id") Long id){

        bookService.deleteById(id);

        return "redirect:/book/findall";
    }


}
