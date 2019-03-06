package com31.websiteecommerce.websiteecommerce.category.service;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import com31.websiteecommerce.websiteecommerce.category.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) {
        if(!findById(category.getId()).isPresent()){
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> update(Category category) {
        Optional<Category> updateCategory= findById(category.getId());
        if(updateCategory.isPresent()){
            BeanUtils.copyProperties(category,updateCategory);
            return updateCategory;
        }
        return null;
    }

    @Override
    public Category delete( Long id) {
        Optional<Category> category= findById(id);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return category.get();
        }
        return null;
    }
}
