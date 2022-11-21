package com.bai3.todo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @RestController dùng cho API, còn return View HTML thì dùng @Controller
@RequestMapping("/todos") // Endpoint gốc là /
public class TodoController {
    // Inject Service vào để gọi được
    @Autowired // Dùng cách này cho ngắn, chứ thường là inject qua constructor
    private TodoService service;

    // RESTful API methods for Retrieval operations
    @GetMapping
    // Trả về Model là một List<Todo>
    public List<Todo> listAll(@RequestParam(defaultValue = "") String title) {
        if (title.equals("")) {
            return service.ListAll();
        } else {
            return service.ListAllByTitle(title);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Integer id) {
        if (service.getById(id) != null) {
            return new ResponseEntity<Todo>(service.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found!" ,HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Create operation
    @PostMapping
    public ResponseEntity<?> Add(@RequestBody Todo todo) {
        service.save(todo);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    // RESTful API method for Update operation
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Todo todo, @PathVariable Integer id) {
        if (service.getById(id) != null) {
            service.save(todo);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found!" ,HttpStatus.NOT_FOUND);
        }
    }

    // RESTful API method for Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (service.getById(id) != null) {
            service.delete(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found!" ,HttpStatus.NOT_FOUND);
        }
    }
}
