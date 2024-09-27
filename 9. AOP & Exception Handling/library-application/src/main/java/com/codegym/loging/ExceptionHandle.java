package com.codegym.loging;

import com.codegym.exception.EmptyBooksException;
import com.codegym.exception.OutOfBoundsLimitException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(EmptyBooksException.class)
    public String showErrorEmpty(Model model){
        model.addAttribute("message","Số lượng sách trong thư viện đã hết, vui lòng chọn sách khác !");
        return "errorPage";
    }
    @ExceptionHandler(OutOfBoundsLimitException.class)
    public String showErrorFull(Model model){
        model.addAttribute("message","Số lượng sách của chúng tôi đã đủ, bạn có nhơ mình mượn ở đâu không ?");
        return "errorPage";
    }
}
