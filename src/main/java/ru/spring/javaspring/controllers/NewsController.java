package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.News;
import ru.spring.javaspring.repo.NewsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

//    Get mappings
    @GetMapping("/")
    public String index(Model model) {

        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news/News";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        return "news/Add";
    }

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> arrayList = new ArrayList<>();
        news.ifPresent(arrayList::add);
        model.addAttribute("news", arrayList);
        return "news/Detail";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        newsRepository.deleteById(id);
        return "redirect:/news/";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/news/";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> arrayList = new ArrayList<>();
        news.ifPresent(arrayList::add);
        model.addAttribute("news", arrayList);
        return "news/Edit";
    }

//    Post mappings
    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("text") String text,
                      @RequestParam("author") String author,
                      @RequestParam("views") Integer views,
                      @RequestParam("likes") Integer likes,
                      Model model) {

        News newsOne = new News(title, text, author, views, likes);
        newsRepository.save(newsOne);
        return "redirect:/news/";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("title") String title,
                        @RequestParam("text") String text,
                        @RequestParam("author") String author,
                        @RequestParam("views") Integer views,
                        @RequestParam("likes") Integer likes,
                        Model model) {

        News news = newsRepository.findById(id).orElseThrow();

        news.setTitle(title);
        news.setAuthor(author);
        news.setText(text);
        news.setViews(views);
        news.setLikes(likes);

        newsRepository.save(news);
        return "redirect:/news/";
    }

//    Search get mappings
    @GetMapping("/search")
    public String search(@RequestParam("title") String title,
                      Model model) {
        List<News> newsList = newsRepository.findByTitleContains(title);
        model.addAttribute("news", newsList);
        return "news/News";
    }
}
