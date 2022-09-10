package ru.spring.javaspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("title", "Simple Calculator");
        return "calculator/calculator";
    }
    @PostMapping("/result")
    public String calculate(@RequestParam("number1") int number1,
                            @RequestParam("number2") int number2,
                            @RequestParam("action") String action,
                            Model model) {
        int result = 0;
        switch (action) {
            case "+" -> {
                result = number1 + number2;
                break;
            }
            case "-" -> {
                result = number1 - number2;
                break;
            }
            case "*" -> {
                result = number1 * number2;
                break;
            }
            case "/" -> {
                result = number1 / number2;
                break;
            }
        }
        model.addAttribute("result", result);
        return "calculator/result_page";
    }
}
