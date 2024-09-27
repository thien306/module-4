package com.codegym.service;

import java.awt.print.Book;
import java.util.List;

public interface ILibraryService {

    List<Book> findAllBook();

    void borrowBook();

    void giveBack();
}
