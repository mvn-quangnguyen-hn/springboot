package com.bai4.user.controller;

import com.bai4.user.model.User;
import com.bai4.user.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    //    List users
    @GetMapping(value = {"/index", "/", ""})
    public String showUserList(Model model) {
        model.addAttribute("users", service.ListAll());
        return "index";
    }

    //    Search username
    @RequestMapping("/search")
    public String searchUsername(@RequestParam(value = "search", required = false) String search, Model model) {
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
