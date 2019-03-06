package com31.websiteecommerce.websiteecommerce.category.service;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    Category findById(Long id);

    List<Category> findAll();

    Category update(Category category);

    Category delete(Long id);
}
