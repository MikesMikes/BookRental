package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.domain.Book;
import mikesmikes.github.bookpublishing.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;
    private final String CREATEUPDATEFORM = "book/createOrUpdateBookForm";

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/findall")
    public String findAll(Model model) {
        log.info("findAll - ");

        model.addAttribute("books", bookService.findAll());

        log.info("findAll - end");
        return "book/findall";
    }

    @RequestMapping("/book/new")
    public String createBook(Model model){

        model.addAttribute("book", new Book());

        return CREATEUPDATEFORM;
    }

    @PostMapping("book/new")
    public String processCreateBook(Model model, Book book) {



        return "redirect:/book/findall";
    }
}
