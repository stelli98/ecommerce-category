package com31.websiteecommerce.websiteecommerce.category.service;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private ArrayList<Category> categories = new ArrayList<>();
    @Override
    public Category create(Category category) {
        if(findById(category.getId())==null){
            categories.add(category);
            return category;
        }
        return null;
    }

    @Override
    public Category findById(Long id) {
        for(Category p:categories){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Category update(Category category) {
        Category updateCategory= findById(category.getId());
        if(updateCategory!=null){
            BeanUtils.copyProperties(category,updateCategory);
            return updateCategory;
        }
        return null;
    }

    @Override
    public Category delete( Long id) {
        Category category= findById(id);
        if(category!=null){
            categories.remove(category);
            return category;
        }
        return null;
    }
}
