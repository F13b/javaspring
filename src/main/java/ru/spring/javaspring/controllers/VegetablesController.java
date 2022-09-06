package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.model.Vegetables;
import ru.spring.javaspring.repo.VegetablesRepository;

import java.util.List;

@Controller
@RequestMapping("/vegetables")
public class VegetablesController {
    @Autowired
    VegetablesRepository vegetablesRepository;

//    Get mappings
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/vegetables/all";
    }

    @GetMapping("/add")
    public String reg(Model model) {
        model.addAttribute("pageTitle", "Add vegetable");
        return "vegetables/AddVegetable";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Vegetables> vegetables = vegetablesRepository.findAll();
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("pageTitle", "All vegetables");
        return "vegetables/AllVegetables";
    }

    //    Post mappings
    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("color") String color,
                      @RequestParam("sun") String sun,
                      @RequestParam("fertilizer") String fertilizer,
                      @RequestParam("waterTemperature") Integer waterTemperature,
                      Model model) {

        Vegetables newVegetable = new Vegetables(name, color, sun, fertilizer, waterTemperature);
        vegetablesRepository.save(newVegetable);
        return "redirect:/vegetables/all";
    }

    //    Search post mappings
    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Vegetables> vegetablesList = vegetablesRepository.findByNameContains(name);
        model.addAttribute("vegetables", vegetablesList);
        return "vegetables/AllVegetables";
    }

}
