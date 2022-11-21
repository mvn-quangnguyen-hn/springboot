package com.bai4.user.service;

import com.bai4.user.model.User;
import com.bai4.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> ListAll() {
        return repo.findAll();
    }
    public User findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public List<User> ListAllByUsername(String username) {
        return repo.findByUsername(username);
    }

    public void save(User user) {
        repo.save(user);
    }

    public User getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}