package mikesmikes.github.bookpublishing.controllers.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerController {

    private final String DEFAULT_400_ERROR_VIEW = "errors/400error";
    private final String DEFAULT_404_ERROR_VIEW = "errors/404error";



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView defaultExceptionHandler(Exception e, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("request", request.getRequestURI());
        mav.setViewName(DEFAULT_400_ERROR_VIEW);

        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ModelAndView notFoundExceptionHandler(Exception e, HttpServletRequest request) {

        log.info("Not Found Exception obtained");

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.addObject("request", request.getRequestURI());
        mav.setViewName(DEFAULT_404_ERROR_VIEW);

        return mav;
    }

}
