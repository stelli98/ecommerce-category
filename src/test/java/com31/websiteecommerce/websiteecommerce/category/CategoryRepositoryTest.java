package com31.websiteecommerce.websiteecommerce.category;

import com31.websiteecommerce.websiteecommerce.category.model.Category;
import com31.websiteecommerce.websiteecommerce.category.repository.CategoryRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void tearDown(){
        categoryRepository.deleteAll();
    }


    @Test
    public void saveTest(){
        Category categoryA=new Category();
        categoryA.setName("Electronic");
        Category save= categoryRepository.save(categoryA);
        Assert.assertNotNull("Category id A can't be null", categoryA.getId());
        Assert.assertTrue("Category name must be Stelli", save.getName().equals("Electronic"));
        Assert.assertTrue("Category id must same with the saved one", categoryA.getId() == save.getId());

    }

    @Test
    public void findByIdTest(){
        Category categoryA=new Category();
        categoryA.setName("Electronic");
        Category save= categoryRepository.save(categoryA);
        Optional<Category> find=categoryRepository.findById(save.getId());
        Assert.assertTrue("Category name must show Electronic",save.getName().equals(find.get().getName()));
    }

    @Test
    public void findByAllTest(){
        Category categoryA=new Category();
        categoryA.setName("Electronic");
        Category saveCategoryA= categoryRepository.save(categoryA);


        Category categoryB=new Category();
        categoryB.setName("Fashion");
        Category saveCategoryB= categoryRepository.save(categoryB);

        List<Category> find= categoryRepository.findAll();
        Assert.assertTrue("List size must be 2", find.size()==2);
    }

    @Test
    public void deleteTest(){
        Category categoryA=new Category();
        categoryA.setName("Electronic");
        Category saveCategoryA= categoryRepository.save(categoryA);

        Category categoryB=new Category();
        categoryB.setName("Fashion");
        Category saveCategoryB= categoryRepository.save(categoryB);

        Category categoryC=new Category();
        categoryC.setName("Food");
        Category saveCategoryC= categoryRepository.save(categoryC);


        categoryRepository.delete(saveCategoryB);
        Assert.assertTrue("List size must be 2", categoryRepository.findAll().size()==2);
        Assert.assertFalse("List id from stella must be not found",
                categoryRepository.findById(saveCategoryB.getId()).isPresent());

    }
}
