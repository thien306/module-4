package com.codegym.service;

import com.codegym.dto.BookDto;
import com.codegym.model.Book;


import java.util.List;

public interface IBookService {

    List<Book> findAll();

    List<BookDto> findAllDto();

    Book findById(Long id);

    void addNew(Book book);

    void update(Book booK);

}
