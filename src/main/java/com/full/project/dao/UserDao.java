package com.full.project.dao;

import com.full.project.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void deleteUser(Long id);

    void register(User user);

    User getUserByCredentials(User user);
}
