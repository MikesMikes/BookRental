package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.services.AuthorService;
import mikesmikes.github.bookpublishing.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final String CREATEUPDATEFORM = "book/createOrUpdateBookForm";

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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

        return CREATEUPDATEFORM;
    }

    @PostMapping("book/new")
    public String processCreateBook(@ModelAttribute("book") Book book) {
        log.info("processCreateBook - ");
        log.info("" + book.getName() + ", " + book.getAuthors().toString());
        bookService.save(book);

        return "redirect:/book/findall";
    }

    @GetMapping("/book/{id}/update")
    public String bookUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());

        return CREATEUPDATEFORM;
    }

    @PostMapping("/book/{id}/update")
    public String processBookUpdate(@PathVariable("id") Long id, Book book){

        bookService.save(book);

        return "redirect:/";
    }
}
