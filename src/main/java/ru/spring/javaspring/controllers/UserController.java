package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.Member;
import ru.spring.javaspring.repo.MemberRepository;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MemberRepository memberRepository;
    @GetMapping("/")
    public String user_data(Model model) {
        return "/users/home_page";
    }
    @GetMapping("/edit")
    public String edit_user_view(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Member member = memberRepository.findByUsername(username);
        model.addAttribute("user", member);
        return "/users/edit_user";
    }
    @PostMapping("/edit/{id}")
    public String edit_user(@PathVariable("id") Long id,
                            @RequestParam("username") String username,
                            @RequestParam("surname") String surname,
                            @RequestParam("name") String name,
                            @RequestParam("secondname") String secondname,
                            @RequestParam("birth") String birth,
                            @RequestParam("passport") String passport,
                            @RequestParam("password") String password,
                            Model model) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setUsername(username);
        member.setSurname(surname);
        member.setName(name);
        member.setSecondname(secondname);
        member.setBirth(birth);
        member.setPassport(passport);
        if (password != null && !password.equals("")) {
            member.setPassword(password);
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
