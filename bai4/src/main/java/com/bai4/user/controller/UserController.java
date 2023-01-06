package com.bai4.user.controller;

import com.bai4.user.model.User;
import com.bai4.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    //    List users
    @GetMapping(value = {"/index", "/", ""})
//    public String showUserList(Model model) {
//        model.addAttribute("users", service.ListAll());
//        return "index";
//    }
    public String showUserList(Model model,
                               @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                               @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        assert sortable != null;

        Page<User> userPage = service.findUsers(page, size, sortable);

        List<User> users = userPage.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("users", users);

        return "index";
    }

    //    Search username
    @RequestMapping("/search")
    public String searchUsername(Model model, @RequestParam(value = "search", required = false) String search) {
        if (search.equals("")) {
            model.addAttribute("users", service.ListAll());
            return "index";
        } else {
            model.addAttribute("users", service.ListAllByUsername(search));
            return "search";
        }
    }

    //    Add user
    @GetMapping("/add")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "/users/add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/users/add-user";
        }
        service.save(user);
        return "redirect:/index";
    }

    //    Edit user
    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", service.getById(id));
        return "/users/edit-user";
    }

    @PostMapping("/edituser")
    public String editUser(User user) {
        User updateUser = service.getById(user.getId());
        if (updateUser != null) {
            updateUser.setId(user.getId());
            updateUser.setUsername(user.getUsername());
            updateUser.setEmail(user.getEmail());
            updateUser.setBirthday(user.getBirthday());

            service.save(updateUser);

            return "redirect:/index";
        } else {
            service.save(user);
            return "redirect:/index";
        }
    }

    //    Delete user
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return "redirect:/index";
        } else return "redirect:/index";
    }
}
