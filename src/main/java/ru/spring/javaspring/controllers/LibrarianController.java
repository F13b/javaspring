package ru.spring.javaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.javaspring.model.*;
import ru.spring.javaspring.repo.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    EditionRepository editionRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    SheetRepository sheetRepository;
    @Autowired
    LimitRepository limitRepository;

    @GetMapping("/")
    public String home_page() {
        return "librarian/home_page";
    }

    // Authors mappings
    @GetMapping("/authors/all-authors")
    public String all_authors(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "librarian/authors/all_authors";
    }

    @GetMapping("/authors/add-author")
    public String add_author_view() {
        return "librarian/authors/add_author";
    }

    @GetMapping("/authors/edit/{id}")
    public String edit_author_view(@PathVariable("id") Long id, Model model) {
        if (!authorRepository.existsById(id)) {
            return "redirect:/librarian/authors/all-authors";
        }
        Optional<Author> author = authorRepository.findById(id);
        ArrayList<Author> arrayList = new ArrayList<>();
        author.ifPresent(arrayList::add);
        model.addAttribute("author", arrayList);
        return "librarian/authors/edit_author";
    }

    @PostMapping("/authors/add-author")
    public String add_author(Author author, Model model) {
        authorRepository.save(author);
        return "redirect:/librarian/authors/all-authors";
    }

    @PostMapping("/authors/edit/{id}")
    public String edit_author(@PathVariable("id") Long id,
                              @RequestParam("surname") String surname,
                              @RequestParam("name") String name,
                              @RequestParam("secondname") String secondname,
                              Model model) {

        Author author = authorRepository.findById(id).orElseThrow();
        author.setSurname(surname);
        author.setName(name);
        author.setSecondname(secondname);
        authorRepository.save(author);
        return "redirect:/librarian/authors/all-authors";
    }

    @PostMapping("/authors/delete/{id}")
    public String delete_author(@PathVariable("id") Long id, Model model) {
        authorRepository.deleteById(id);
        return "redirect:/librarian/authors/all-authors";
    }

    //Branches mappings
    @GetMapping("/branches/all-branches")
    public String all_branches(Model model) {
        Iterable<Branch> branches = branchRepository.findAll();
        model.addAttribute("branches", branches);
        return "librarian/branches/all_branches";
    }

    @GetMapping("/branches/add-branch")
    public String add_branch_view() {
        return "librarian/branches/add_branch";
    }

    @GetMapping("/branches/edit/{id}")
    public String edit_branch_view(@PathVariable("id") Long id, Model model) {
        if (!branchRepository.existsById(id)) {
            return "redirect:/librarian/branches/all-branches";
        }
        Optional<Branch> branch = branchRepository.findById(id);
        ArrayList<Branch> arrayList = new ArrayList<>();
        branch.ifPresent(arrayList::add);
        model.addAttribute("branch", arrayList);
        return "librarian/branches/edit_branch";
    }

    @PostMapping("/branches/add-branch")
    public String add_branch(Branch branch, Model model) {
        branchRepository.save(branch);
        return "redirect:/librarian/branches/all-branches";
    }

    @PostMapping("/branches/edit/{id}")
    public String edit_branch(@PathVariable("id") Long id,
                              @RequestParam("address") String address,
                              Model model) {

        Branch branch = branchRepository.findById(id).orElseThrow();
        branch.setAddress(address);
        branchRepository.save(branch);
        return "redirect:/librarian/branches/all-branches";
    }

    @PostMapping("/branches/delete/{id}")
    public String delete_branch(@PathVariable("id") Long id, Model model) {
        branchRepository.deleteById(id);
        return "redirect:/librarian/branches/all-branches";
    }

    //Editions mappings
    @GetMapping("/editions/all-editions")
    public String all_editions(Model model) {
        Iterable<Edition> editions = editionRepository.findAll();
        model.addAttribute("editions", editions);
        return "librarian/editions/all_editions";
    }

    @GetMapping("/editions/add-edition")
    public String add_edition_view() {
        return "librarian/editions/add_edition";
    }

    @GetMapping("/editions/edit/{id}")
    public String edit_edition_view(@PathVariable("id") Long id, Model model) {
        if (!editionRepository.existsById(id)) {
            return "redirect:/librarian/editions/all-editions";
        }
        Optional<Edition> edition = editionRepository.findById(id);
        ArrayList<Edition> arrayList = new ArrayList<>();
        edition.ifPresent(arrayList::add);
        model.addAttribute("edition", arrayList);
        return "librarian/editions/edit_edition";
    }

    @PostMapping("/editions/add-edition")
    public String add_edition(Edition edition, Model model) {
        editionRepository.save(edition);
        return "redirect:/librarian/editions/all-editions";
    }

    @PostMapping("/editions/edit/{id}")
    public String edit_edition(@PathVariable("id") Long id,
                              @RequestParam("type") String type,
                              Model model) {

        Edition edition = editionRepository.findById(id).orElseThrow();
        edition.setType(type);
        editionRepository.save(edition);
        return "redirect:/librarian/editions/all-editions";
    }

    @PostMapping("/editions/delete/{id}")
    public String delete_edition(@PathVariable("id") Long id, Model model) {
        editionRepository.deleteById(id);
        return "redirect:/librarian/editions/all-editions";
    }
    //Genres mappings
    @GetMapping("/genres/all-genres")
    public String all_genres(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "librarian/genres/all_genres";
    }

    @GetMapping("/genres/add-genre")
    public String add_genre_view() {
        return "librarian/genres/add_genre";
    }

    @GetMapping("/genres/edit/{id}")
    public String edit_genre_view(@PathVariable("id") Long id, Model model) {
        if (!genreRepository.existsById(id)) {
            return "redirect:/librarian/genres/all-genres";
        }
        Optional<Genre> genre = genreRepository.findById(id);
        ArrayList<Genre> arrayList = new ArrayList<>();
        genre.ifPresent(arrayList::add);
        model.addAttribute("genre", arrayList);
        return "librarian/genres/edit_genre";
    }

    @PostMapping("/genres/add-genre")
    public String add_genre(Genre genre, Model model) {
        genreRepository.save(genre);
        return "redirect:/librarian/genres/all-genres";
    }

    @PostMapping("/genres/edit/{id}")
    public String edit_genre(@PathVariable("id") Long id,
                              @RequestParam("name") String name,
                              Model model) {

        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setName(name);
        genreRepository.save(genre);
        return "redirect:/librarian/genres/all-genres";
    }

    @PostMapping("/genres/delete/{id}")
    public String delete_genre(@PathVariable("id") Long id, Model model) {
        genreRepository.deleteById(id);
        return "redirect:/librarian/genres/all-genres";
    }
    //Limits mappings
    @GetMapping("/limits/all-limits")
    public String all_limits(Model model) {
        Iterable<Limit> limits = limitRepository.findAll();
        model.addAttribute("limits", limits);
        return "librarian/limits/all_limits";
    }

    @GetMapping("/limits/add-limit")
    public String add_limit_view(Model model) {
        model.addAttribute("limit", new Limit());
        return "librarian/limits/add_limit";
    }

    @GetMapping("/limits/edit/{id}")
    public String edit_limit_view(@PathVariable("id") Long id, Model model) {
        if (!limitRepository.existsById(id)) {
            return "redirect:/librarian/limits/all-limits";
        }
        Optional<Limit> limit = limitRepository.findById(id);
        ArrayList<Limit> arrayList = new ArrayList<>();
        limit.ifPresent(arrayList::add);
        model.addAttribute("limitData", arrayList);
        model.addAttribute("limit", new Limit());
        return "librarian/limits/edit_limit";
    }

    @PostMapping("/limits/add-limit")
    public String add_limit(@Valid Limit limit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/librarian/limits/add_limit";
        }
        limitRepository.save(limit);
        return "redirect:/librarian/limits/all-limits";
    }

    @PostMapping("/limits/edit/{id}")
    public String edit_limits(@PathVariable("id") Long id,
                             @RequestParam("age_limit") String value,
                             @ModelAttribute("limit") @Valid Limit newLimit,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            Optional<Limit> limit = limitRepository.findById(id);
            ArrayList<Limit> arrayList = new ArrayList<>();
            limit.ifPresent(arrayList::add);
            model.addAttribute("limitData", arrayList);
            model.addAttribute("limit", new Limit());
            return "librarian/limits/edit_limit";
        }
        Limit limit = limitRepository.findById(id).orElseThrow();
        limit.setAge_limit(value);
        limitRepository.save(limit);
        return "redirect:/librarian/limits/all-limits";
    }

    @PostMapping("/limits/delete/{id}")
    public String delete_limit(@PathVariable("id") Long id, Model model) {
        limitRepository.deleteById(id);
        return "redirect:/librarian/limits/all-limits";
    }
    //Publishers mappings
    @GetMapping("/publishers/all-publishers")
    public String all_publishers(Model model) {
        Iterable<Publisher> publishers = publisherRepository.findAll();
        model.addAttribute("publishers", publishers);
        return "librarian/publisher/all_publishers";
    }

    @GetMapping("/publishers/add-publisher")
    public String add_publisher_view() {
        return "librarian/publisher/add_publisher";
    }

    @GetMapping("/publishers/edit/{id}")
    public String edit_publishers_view(@PathVariable("id") Long id, Model model) {
        if (!publisherRepository.existsById(id)) {
            return "redirect:/librarian/publishers/all-publishers";
        }
        Optional<Publisher> publisher = publisherRepository.findById(id);
        ArrayList<Publisher> arrayList = new ArrayList<>();
        publisher.ifPresent(arrayList::add);
        model.addAttribute("publisher", arrayList);
        return "librarian/publisher/edit_publisher";
    }

    @PostMapping("/publishers/add-publisher")
    public String add_publisher(Publisher publisher, Model model) {
        publisherRepository.save(publisher);
        return "redirect:/librarian/publishers/all-publishers";
    }

    @PostMapping("/publishers/edit/{id}")
    public String edit_publishers(@PathVariable("id") Long id,
                              @RequestParam("name") String name,
                              Model model) {

        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        publisher.setName(name);
        publisherRepository.save(publisher);
        return "redirect:/librarian/publishers/all-publishers";
    }

    @PostMapping("/publishers/delete/{id}")
    public String delete_publisher(@PathVariable("id") Long id, Model model) {
        publisherRepository.deleteById(id);
        return "redirect:/librarian/publishers/all-publishers";
    }
    //Books mappings
    @GetMapping("/books/all-books")
    public String all_books(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "librarian/books/all_books";
    }

    @GetMapping("/books/add-book")
    public String add_book_view(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        Iterable<Branch> branches = branchRepository.findAll();
        Iterable<Edition> editions = editionRepository.findAll();
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<Limit> limits = limitRepository.findAll();
        Iterable<Publisher> publishers = publisherRepository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("branches", branches);
        model.addAttribute("editions", editions);
        model.addAttribute("genres", genres);
        model.addAttribute("limits", limits);
        model.addAttribute("publishers", publishers);
        return "librarian/books/add_book";
    }

    @GetMapping("/books/edit/{id}")
    public String edit_book_view(@PathVariable("id") Long id, Model model) {
        if (!bookRepository.existsById(id)) {
            return "redirect:/librarian/books/all-books";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> arrayList = new ArrayList<>();
        book.ifPresent(arrayList::add);
        model.addAttribute("book", arrayList);
        return "librarian/books/edit_book";
    }

    @PostMapping("/books/add-books")
    public String add_book(Book book, Model model) {
        bookRepository.save(book);
        return "redirect:/librarian/books/all-books";
    }

    @PostMapping("/books/edit/{id}")
    public String edit_book(@PathVariable("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("pages") Integer pages,
                              @RequestParam("price") Double price,
                              @RequestParam("year") String year,
                              @RequestParam("amount") Integer amount,
                              @RequestParam("authors") Author authors,
                              @RequestParam("branches") Branch branches,
                              @RequestParam("edition") Edition edition,
                              @RequestParam("publisher") Publisher publisher,
                              @RequestParam("genres") Genre genres,
                              @RequestParam("book_age_limit") Limit book_age_limit,
                              Model model) {

        Book book = bookRepository.findById(id).orElseThrow();
        book.setName(name);
        bookRepository.save(book);
        return "redirect:/librarian/books/all-books";
    }

    @PostMapping("/books/delete/{id}")
    public String delete_book(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/librarian/books/all-books";
    }
    //Sheets mappings
    @GetMapping("/sheets/all-sheets")
    public String all_sheets(Model model) {
        Iterable<Sheet> sheets = sheetRepository.findAll();
        model.addAttribute("sheets", sheets);
        return "librarian/sheets/all_sheets";
    }

    @GetMapping("/sheets/add-sheets")
    public String add_sheets_view() {
        return "librarian/sheets/add_sheets";
    }

    @GetMapping("/sheets/edit/{id}")
    public String edit_sheets_view(@PathVariable("id") Long id, Model model) {
        if (!sheetRepository.existsById(id)) {
            return "redirect:/librarian/sheets/all-sheets";
        }
        Optional<Sheet> sheet = sheetRepository.findById(id);
        ArrayList<Sheet> arrayList = new ArrayList<>();
        sheet.ifPresent(arrayList::add);
        model.addAttribute("sheet", arrayList);
        return "librarian/sheets/edit_sheet";
    }

    @PostMapping("/sheets/add-sheets")
    public String add_sheet(Sheet sheet, Model model) {
        sheetRepository.save(sheet);
        return "redirect:/librarian/sheets/all-sheets";
    }

    @PostMapping("/sheets/edit/{id}")
    public String edit_sheet(@PathVariable("id") Long id,
                              @RequestParam("date_of_issue") String date_of_issue,
                              Model model) {

        Sheet sheet = sheetRepository.findById(id).orElseThrow();
        sheet.setDate_of_issue(date_of_issue);
        sheetRepository.save(sheet);
        return "redirect:/librarian/sheets/all-sheets";
    }

    @PostMapping("/sheets/delete/{id}")
    public String delete_sheets(@PathVariable("id") Long id, Model model) {
        sheetRepository.deleteById(id);
        return "redirect:/librarian/sheets/all-sheets";
    }
}
