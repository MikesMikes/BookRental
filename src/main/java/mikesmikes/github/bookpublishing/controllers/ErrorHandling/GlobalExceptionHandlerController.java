package mikesmikes.github.bookpublishing.controllers.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlerController{

    private final String DEFAULT_ERROR_VIEW = "errors/error";


    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView defaultExceptionHandler(Exception e, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("log", request.getRequestURI());
        mav.setViewName(DEFAULT_ERROR_VIEW);

        return mav;
    }

}
