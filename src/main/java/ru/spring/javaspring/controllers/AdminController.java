package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.Employee;
import ru.spring.javaspring.model.Post;
import ru.spring.javaspring.model.Publisher;
import ru.spring.javaspring.repo.EmployeeRepository;
import ru.spring.javaspring.repo.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String home_page() {
        return "admin/home_page";
    }

//    posts
    @GetMapping("/posts/all-posts")
    public String all_posts() {
        return "/admin/post/all_posts";
    }
    @GetMapping("/posts/add-post")
    public String add_post_view() {
        return "/admin/post/add_post";
    }
    @GetMapping("/posts/edit/{id}")
    public String edit_post_view(@PathVariable("id") Long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/admin/posts/all-posts";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> arrayList = new ArrayList<>();
        post.ifPresent(arrayList::add);
        model.addAttribute("post", arrayList);
        return "/admin/post/edit_post";
    }
    @PostMapping("/posts/add-post")
    public String add_post(Post post) {
        postRepository.save(post);
        return "redirect:/admin/posts/all-posts/";
    }
    @PostMapping("/posts/edit/{id}")
    public String edit_post(@PathVariable("id") Long id,
                            @RequestParam("name") String name,
                            @RequestParam("price") String price,
                            Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setName(name);
        post.setPrice(price);
        postRepository.save(post);
        return "redirect:/admin/posts/all-posts/";
    }
    @PostMapping("/posts/delete/{id}")
    public String delete_post(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/admin/posts/all-posts/";
    }

//    employees
    @GetMapping("/employees/all-employees")
    public String all_employees() {
        return "/admin/employee/all_employees";
    }
    @GetMapping("/employees/add-employee")
    public String add_employee_view(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "/admin/employee/add_employee";
    }
    @GetMapping("/employees/edit/{id}")
    public String edit_employee_view(@PathVariable("id") Long id, Model model) {
        if (!employeeRepository.existsById(id)) {
            return "redirect:/admin/employees/all-employees";
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> arrayList = new ArrayList<>();
        employee.ifPresent(arrayList::add);
        model.addAttribute("employee", arrayList);
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "/admin/employee/edit_employee";
    }
    @PostMapping("/employees/add-employee")
    public String add_employee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/admin/employees/all-employees/";
    }
    @PostMapping("/employees/edit/{id}")
    public String edit_employee(@PathVariable("id") Long id,
                                @RequestParam("surname") String surname,
                                @RequestParam("name") String name,
                                @RequestParam("secondname") String secondname,
                                Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setSurname(surname);
        employee.setName(name);
        employee.setSecondname(secondname);
        employeeRepository.save(employee);
        return "redirect:/admin/employees/all-employees/";
    }
    @PostMapping("/employees/delete/{id}")
    public String delete_employee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/admin/employees/all-employees/";
    }
}
