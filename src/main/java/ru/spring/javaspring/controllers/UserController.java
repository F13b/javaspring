package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.javaspring.model.News;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.repo.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    Get mappings
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/user/registration";
    }

    @GetMapping("/registration")
    public String reg(Model model) {
        model.addAttribute("pageTitle", "Registration");
        return "users/Registration";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "All");
        return "users/AllUsers";
    }

//    Post mappings
    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("nickname") String nickname,
                      @RequestParam("email") String email,
                      @RequestParam("gender") String gender,
                      @RequestParam("age") Integer age,
                      Model model) {

        User newUser = new User(name, nickname, email, gender, age);
        userRepository.save(newUser);
        return "redirect:/user/all";
    }

//    Search post mappings
    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<User> usersList = userRepository.findByNicknameContains(name);
        model.addAttribute("users", usersList);
        return "users/AllUsers";
    }
}
