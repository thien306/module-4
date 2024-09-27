package com.codegym.service.impl;

import com.codegym.service.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.List;

public class LibraryService implements ILibraryService {

    @Autowired
    private ILibraryService libraryService;

    @Override
    public List<Book> findAllBook() {
        return null;
    }

    @Override
    public void borrowBook() {

    }

    @Override
    public void giveBack() {

    }
}
