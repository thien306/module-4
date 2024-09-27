package com.codegym.service;

import com.codegym.model.Blog;

import java.util.List;

public interface IBlogService {

    List<Blog> findAll();

    void save(Blog blog);

    void remove(Blog blog);

    Blog findById(int id);
}
