package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.*;
import ru.spring.javaspring.repo.BirthRepository;
import ru.spring.javaspring.repo.PhoneRepository;
import ru.spring.javaspring.repo.TownRepository;
import ru.spring.javaspring.repo.UserRepository;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BirthRepository birthRepository;
    @Autowired
    TownRepository townRepository;
    @Autowired
    PhoneRepository phoneRepository;

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
        Iterable<Town> towns = townRepository.findAll();
        Iterable<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phone", phones);
        model.addAttribute("towns", towns);
        model.addAttribute("usersE", arrayList);
        model.addAttribute("user", new User());

        return "users/edit_user";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("users") @Valid User newuser,
                       BindingResult bindingResult,
                       @PathVariable("id") Long id,
                       @RequestParam(value = "town", required = false) String userTown,
                       @RequestParam(value = "phone", required = false) String phones,
                       @RequestParam(value = "birth", required = false) String birth,
                       Model model) {

        if (bindingResult.hasErrors()) {
            Optional<User> user = userRepository.findById(id);
            ArrayList<User> arrayList = new ArrayList<>();
            user.ifPresent(arrayList::add);
            model.addAttribute("usersE", arrayList);
            return "users/edit_user";
        }

        User user = userRepository.findById(id).orElseThrow();
        Birth date_of_birth = birthRepository.findByBirth(birth);
        Town town = townRepository.findByTown(userTown);
        Phone phone = phoneRepository.findByNumber(phones);

        user.setName(newuser.getName());
        user.setUsername(newuser.getUsername());
        user.setAge(newuser.getAge());
        user.setGender(newuser.getGender());
        user.setEmail(newuser.getEmail());
        user.setDate_of_birth(date_of_birth);
        user.setPhone(phone);
        town.getUsers().add(user);
        user.getTowns().add(town);

        userRepository.save(user);
        townRepository.save(town);
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
