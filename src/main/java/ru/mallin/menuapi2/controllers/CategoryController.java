package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mallin.menuapi2.entity.Category;
import ru.mallin.menuapi2.repos.CategoryRepo;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepo repo;

    public CategoryController(CategoryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return (List<Category>) repo.findAll();
    }

    @PostMapping("/categories/add")
    public Category saveCategory(@RequestBody Category category){
        return repo.save(category);
    }

}
