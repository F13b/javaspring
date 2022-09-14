package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.spring.javaspring.model.Role;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.repo.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/all-users")
    public String all_users(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/all_users";
    }
    @GetMapping("/admin/edit/{id}")
    public String edit_user(@PathVariable("id") Long id, Model model) {
        Optional<User> user_raw = userRepository.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user", userArrayList);
        model.addAttribute("roles", Role.values());
        return "admin/edit_user";
    }
}
