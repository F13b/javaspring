package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.javaspring.model.Abonement;
import ru.spring.javaspring.model.Member;
import ru.spring.javaspring.model.Role;
import ru.spring.javaspring.repo.AbonementRepository;
import ru.spring.javaspring.repo.MemberRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

@Controller
public class RegistrationController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AbonementRepository abonementRepository;

    @GetMapping("/registration")
    public String reg_view(Model model) {
        return "registration";
    }

    @GetMapping("/login")
    public String log_view(Model model) {
        return "login";
    }

    @PostMapping("/registration")
    public String reg_action(Member member,
                             Model model) {
        Member memberFromDB = memberRepository.findByUsername(member.getUsername());
        if (memberFromDB != null) {
            model.addAttribute(
                    "error",
                    "Такой пользователь уже существует");
            return "/registration";
        }

        LocalDate date = LocalDate.now();
        int abonementNumber = (int)(1000000 + Math.random() * 9000000);

        Abonement userAbonement = new Abonement(Integer.toString(abonementNumber), date.toString(), date.plusYears(1).toString());
        abonementRepository.save(userAbonement);

        member.setAbonement(userAbonement);
        member.setActive(true);
        member.setRoles(Collections.singleton(Role.USER));
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return "redirect:/login";
    }
}
