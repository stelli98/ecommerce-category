package com31.websiteecommerce.websiteecommerce.category.repository;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void delete(Category category);
    
}
