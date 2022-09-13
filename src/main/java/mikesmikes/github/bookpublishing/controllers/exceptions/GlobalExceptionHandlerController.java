package mikesmikes.github.bookpublishing.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    private final String DEFAULT_ERROR_VIEW = "errors/error";


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView defaultExceptionHandler(Exception e, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("request", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);

        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ModelAndView notFoundExceptionHandler(Exception e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.addObject("request", request.getRequestURI());
        mav.setViewName("errors/404error");

        return mav;
    }

}
