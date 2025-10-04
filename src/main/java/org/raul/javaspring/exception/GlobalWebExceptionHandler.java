package org.raul.javaspring.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalWebExceptionHandler {

    @ExceptionHandler
    public String handleGeneralException(Exception ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // nombre de la plantilla Thymeleaf
    }
}
