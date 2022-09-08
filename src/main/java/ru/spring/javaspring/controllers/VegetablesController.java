package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.model.Vegetables;
import ru.spring.javaspring.repo.VegetablesRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("vegetables", new Vegetables());
        return "vegetables/AddVegetable";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Vegetables> vegetables = vegetablesRepository.findAll();
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("pageTitle", "All vegetables");
        return "vegetables/AllVegetables";
    }

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Vegetables> vegetables = vegetablesRepository.findById(id);
        ArrayList<Vegetables> arrayList = new ArrayList<>();
        vegetables.ifPresent(arrayList::add);
        model.addAttribute("vegetables", arrayList);
        return "vegetables/Vegetable";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        vegetablesRepository.deleteById(id);
        return "redirect:/vegetables/all";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!vegetablesRepository.existsById(id)) {
            return "redirect:/vegetables/all";
        }
        Optional<Vegetables> user = vegetablesRepository.findById(id);
        ArrayList<Vegetables> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("vegetables", arrayList);
        model.addAttribute("vegetablesE", new Vegetables());
        return "vegetables/Editvegetables";
    }

    //    Post mappings
//    @PostMapping("/add")
//    public String add(@RequestParam("name") String name,
//                      @RequestParam("color") String color,
//                      @RequestParam("sun") String sun,
//                      @RequestParam("fertilizer") String fertilizer,
//                      @RequestParam("waterTemperature") Integer waterTemperature,
//                      Model model) {
//
//        Vegetables newVegetable = new Vegetables(name, color, sun, fertilizer, waterTemperature);
//        vegetablesRepository.save(newVegetable);
//        return "redirect:/vegetables/all";
//    }

    @PostMapping("/add")
    public String add(@ModelAttribute("vegetables") @Valid Vegetables newVegetable,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            return "vegetables/AddVegetable";
        }
        vegetablesRepository.save(newVegetable);
        return "redirect:/vegetables/all";
    }

//    @PostMapping("/edit/{id}")
//    public String edit (@PathVariable("id") Long id,
//                        @RequestParam("name") String name,
//                        @RequestParam("color") String color,
//                        @RequestParam("sun") String sun,
//                        @RequestParam("fertilizer") String fertilizer,
//                        @RequestParam("waterTemperature") Integer waterTemperature,
//                        Model model) {
//
//        Vegetables vegetables = vegetablesRepository.findById(id).orElseThrow();
//
//        vegetables.setName(name);
//        vegetables.setColor(color);
//        vegetables.setFertilizer(fertilizer);
//        vegetables.setSun(sun);
//        vegetables.setWaterTemperature(waterTemperature);
//
//        vegetablesRepository.save(vegetables);
//        return "redirect:/vegetables/all";
//    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("name") String name,
                        @RequestParam("color") String color,
                        @RequestParam("sun") String sun,
                        @RequestParam("fertilizer") String fertilizer,
                        @RequestParam("waterTemperature") Integer waterTemperature,
                        @ModelAttribute("user") @Valid User newUser,
                        BindingResult bindingResult,
                        Model model) {

        if (bindingResult.hasErrors()) {
            return "vegetables/AddVegetable";
        }

        Vegetables vegetables = vegetablesRepository.findById(id).orElseThrow();

        vegetables.setName(name);
        vegetables.setColor(color);
        vegetables.setFertilizer(fertilizer);
        vegetables.setSun(sun);
        vegetables.setWaterTemperature(waterTemperature);

        vegetablesRepository.save(vegetables);
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
