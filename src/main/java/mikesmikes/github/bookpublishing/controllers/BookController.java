package mikesmikes.github.bookpublishing.controllers;

import lombok.extern.slf4j.Slf4j;
import mikesmikes.github.bookpublishing.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;

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
}
