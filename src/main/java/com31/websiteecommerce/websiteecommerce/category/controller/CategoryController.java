package com31.websiteecommerce.websiteecommerce.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com31.websiteecommerce.websiteecommerce.category.model.ApiKey;
import com31.websiteecommerce.websiteecommerce.category.entity.Category;
import com31.websiteecommerce.websiteecommerce.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping(value = "/categories",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> findAll(ApiKey apiKey){
        return categoryService.findAll();
    }

    @GetMapping(value = "/categories/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Category> findById(@PathVariable Long id, ApiKey apiKey){
        return categoryService.findById(id);
    }

    @PostMapping(value = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category create(@RequestBody Category category, ApiKey apiKey) throws Exception{
        String json = objectMapper.writeValueAsString(category);
        kafkaTemplate.send("category-product",json);
        return categoryService.create(category);
    }

    @PutMapping(value = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Optional<Category> update(@RequestBody Category category, ApiKey apiKey){
        return categoryService.update(category);
    }

    @DeleteMapping(value = "/categories/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category delete(@PathVariable Long id,ApiKey apiKey){
        return categoryService.delete(id);
    }
}
