package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.repo.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("users", new User());
        return "users/Registration";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "All");
        return "users/AllUsers";
    }

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("users", arrayList);
        return "users/UserInfo";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
         userRepository.deleteById(id);
        return "redirect:/user/all";
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
        model.addAttribute("users", new User());

        return "users/EditUser";
    }

//    Post mappings
//    @PostMapping("/add")
//    public String add(@RequestParam("name") String name,
//                      @RequestParam("nickname") String nickname,
//                      @RequestParam("email") String email,
//                      @RequestParam("gender") String gender,
//                      @RequestParam("age") Integer age,
//                      Model model) {
//
//        User newUser = new User(name, nickname, email, gender, age);
//        userRepository.save(newUser);
//        return "redirect:/user/all";
//    }

    @PostMapping("/add")
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/Registration";

        userRepository.save(user);
        return "redirect:/user/all";
    }

//    @PostMapping("/edit/{id}")
//    public String edit (@PathVariable("id") Long id,
//                        @RequestParam("name") String name,
//                        @RequestParam("nickname") String nickname,
//                        @RequestParam("email") String email,
//                        @RequestParam("gender") String gender,
//                        @RequestParam("age") Integer age,
//                        Model model) {
//
//        User user = userRepository.findById(id).orElseThrow();
//
//        user.setName(name);
//        user.setNickname(nickname);
//        user.setAge(age);
//        user.setGender(gender);
//        user.setEmail(email);
//
//        userRepository.save(user);
//        return "redirect:/user/all";
//    }

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
            return "users/EditUser";
        }

        User user = userRepository.findById(id).orElseThrow();

        user.setName(newuser.getName());
        user.setNickname(newuser.getNickname());
        user.setAge(newuser.getAge());
        user.setGender(newuser.getGender());
        user.setEmail(newuser.getEmail());

        userRepository.save(newuser);
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
