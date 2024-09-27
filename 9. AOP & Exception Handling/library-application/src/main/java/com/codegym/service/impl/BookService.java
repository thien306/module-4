package com.codegym.service.impl;

import com.codegym.dto.BookDto;
import com.codegym.model.Book;
import com.codegym.repository.IBookRepository;
import com.codegym.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDto> findAllDto() {
        return bookRepository.findByCount();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void addNew(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book booK) {
        bookRepository.save(booK);
    }


}
