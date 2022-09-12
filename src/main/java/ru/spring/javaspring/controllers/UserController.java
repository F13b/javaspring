package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.Role;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.repo.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    Get mappings
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "All users");
        return "users/all_users";
    }

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("users", arrayList);
        model.addAttribute("pageTitle", "23");
        return "users/separate_user";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
         userRepository.deleteById(id);
        return "redirect:/users/all";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/all/";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("usersE", arrayList);
        model.addAttribute("user", new User());

        return "users/edit_user";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("users") @Valid User newuser,
                       BindingResult bindingResult,
                       @PathVariable("id") Long id,
                       Model model) {

        if (bindingResult.hasErrors()) {
            Optional<User> user = userRepository.findById(id);
            ArrayList<User> arrayList = new ArrayList<>();
            user.ifPresent(arrayList::add);
            model.addAttribute("usersE", arrayList);
            return "users/edit_user";
        }

        User user = userRepository.findById(id).orElseThrow();

        user.setName(newuser.getName());
        user.setUsername(newuser.getUsername());
        user.setAge(newuser.getAge());
        user.setGender(newuser.getGender());
        user.setEmail(newuser.getEmail());

        userRepository.save(newuser);
        return "redirect:/users/all";
    }

//    Search post mappings
    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<User> usersList = userRepository.findByUsernameContains(name);
        model.addAttribute("users", usersList);
        return "users/all_users";
    }
}
