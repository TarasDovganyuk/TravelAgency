package com.dovganyuk.dao;

import com.dovganyuk.model.Order;
import com.dovganyuk.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    List<User> getAllUsers();

    User findById(Integer userId);

    List<Order> getOrdersByUserId(Integer userId);
}
