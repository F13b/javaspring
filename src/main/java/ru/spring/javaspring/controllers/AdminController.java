package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.Role;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.repo.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyAuthority('ADMIN')") //Доступ для админов
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String all_users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/all_users";
    }
    @GetMapping("/edit/{id}")
    public String edit_user(@PathVariable("id") Long id, Model model) {
        Optional<User> user_raw = userRepository.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user", userArrayList);
        model.addAttribute("roles", Role.values());
        return "admin/edit_user";
    }

    @PostMapping
    public String edit_role(
            @RequestParam("userId") User user,
            @RequestParam("username") String username,
            @RequestParam(name = "roles[]", required = false)
            String[] roles
    )
    {
        user.setUsername(username);
        user.getRoles().clear();

        if (roles != null)
        {
            for (String role_name:
                    roles) {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        userRepository.save(user);
        return "redirect:/admin";
    }
}
