package com31.websiteecommerce.websiteecommerce.category.controller;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import com31.websiteecommerce.websiteecommerce.category.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping(value = "/categories/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Category findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping(value = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping(value = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping(value = "/categories/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category delete(@PathVariable Long id){
        return categoryService.delete(id);
    }
}
