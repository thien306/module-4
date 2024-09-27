package com.codegym.service;

import com.codegym.model.User;

import java.util.Optional;

public interface IUserService {

    Iterable<User> findAll();

    Iterable<User> findByNameContaining(String name);

    Optional<User> findById(Long id);

    User save(User user);

    void delete(Long id);


}
