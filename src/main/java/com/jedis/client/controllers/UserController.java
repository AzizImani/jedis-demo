package com.jedis.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jedis.client.dao.user.UserDao;
import com.jedis.client.models.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> getUser(){
        return userDao.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userDao.getById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userDao.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userDao.deleteById(id);
    }
}
