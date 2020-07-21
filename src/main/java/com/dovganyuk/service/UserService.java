package com.dovganyuk.service;

import com.dovganyuk.model.Order;
import com.dovganyuk.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getAllUsers();

    User findById(Integer userId);

    List<Order> getOrdersByUserId(Integer userId);
}
