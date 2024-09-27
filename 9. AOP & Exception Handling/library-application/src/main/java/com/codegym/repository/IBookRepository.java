package com.codegym.repository;

import com.codegym.dto.BookDto;

import com.codegym.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT id,count,name_book as bookName FROM book", nativeQuery = true)
    List<BookDto> findByCount();

    Book findById(Long id);
}
