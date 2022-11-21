package com.bai3.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Đánh dấu class này là Service
public class TodoService {
    // Inject Repository vào
    @Autowired // DÙng cách này cho ngắn, chứ thường là inject qua constructor
    private TodoRepository repo;

    public List<Todo> ListAll() {
        // Lấy ra từ Repository dạng List<Todo> là Entity
        return repo.findAll();
    }

    public List<Todo> ListAllByTitle(String title) {
        return repo.findByTitle(title);
    }

    public void save(Todo todo) {
        repo.save(todo);
    }

    public Todo getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
