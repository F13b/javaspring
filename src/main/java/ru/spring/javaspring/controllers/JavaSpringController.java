package ru.spring.javaspring.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.JavaspringApplication;

@Controller
public class JavaSpringController {
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Home page");
        return "Home";
    }
}
