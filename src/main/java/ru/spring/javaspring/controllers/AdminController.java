package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.*;
import ru.spring.javaspring.repo.EmployeeRepository;
import ru.spring.javaspring.repo.MemberRepository;
import ru.spring.javaspring.repo.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String home_page() {
        return "admin/home_page";
    }

//    posts
    @GetMapping("/posts/all-posts")
    public String all_posts(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
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
    @GetMapping("/posts/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Post> postList = postRepository.findByNameContains(name);
        model.addAttribute("posts", postList);
        return "admin/post/all_posts";
    }

//    employees
    @GetMapping("/employees/all-employees")
    public String all_employees(Model model) {
        Iterable<Employee> employes = employeeRepository.findAll();
        model.addAttribute("employees", employes);
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

//    users
    @GetMapping("/users/all-users")
    public String all_users(Model model) {
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("users", members);
        return "admin/employee/all_users";
    }
    @GetMapping("/users/edit/{id}")
    public String edit_user(@PathVariable("id") Long id, Model model) {
        Optional<Member> user_raw = memberRepository.findById(id);
        ArrayList<Member> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user", userArrayList);
        model.addAttribute("roles", Role.values());
        return "admin/employee/edit_roles";
    }

    @PostMapping("/users/edit/{id}")
    public String edit_role(
            @RequestParam("userId") Member user,
            @RequestParam("username") String username,
            @RequestParam(name = "roles[]", required = false)
            String[] roles
    )
    {
        user.setUsername(username);
        user.getRoles().clear();

        if (roles != null)
        {
            for (String role_name:
                    roles) {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        memberRepository.save(user);
        return "redirect:/admin/users/all-users";
    }
}
