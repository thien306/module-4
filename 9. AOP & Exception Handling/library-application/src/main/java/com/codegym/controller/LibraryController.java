package com.codegym.controller;

import com.codegym.service.IBookService;
import com.codegym.service.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name = "library",produces = "text/html; charset=utf-8")
public class LibraryController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public String showAll(Model model){
//        model.addAttribute("library",)
        return "/library/home";
    }
}