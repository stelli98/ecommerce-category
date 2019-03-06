package com31.websiteecommerce.websiteecommerce.category.service;

import com31.websiteecommerce.websiteecommerce.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    Optional<Category> update(Category category);

    Category delete(Long id);
}
