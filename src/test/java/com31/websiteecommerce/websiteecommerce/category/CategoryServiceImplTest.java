package com31.websiteecommerce.websiteecommerce.category;

import com31.websiteecommerce.websiteecommerce.category.entity.Category;
import com31.websiteecommerce.websiteecommerce.category.service.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Before
    public void setUp() throws Exception {
        categoryService=new CategoryServiceImpl();
    }

    @Test
    public void createTest(){
        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        categoryService.create(categoryA);
        Category categoryB=new Category(Long.valueOf(1),"Fashion");
        categoryService.create(categoryB);
        Assert.assertTrue("Array size must be 1", categoryService.findAll().size() == 1);
        Assert.assertFalse("Category can't have similar id",
                categoryService.findById(categoryB.getId())== null);
    }

    @Test
    public void findByIdTest(){

        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        categoryService.create(categoryA);
        Optional<Category> searchCategory1= categoryService.findById(Long.valueOf(1));
        Optional<Category> searchCategory2= categoryService.findById(Long.valueOf(13));
        Assert.assertTrue("Must return Electronic Category Details", categoryA.equals(searchCategory1));
        Assert.assertTrue("Can't find unregistered id",searchCategory2==null);
    }

    @Test
    public void findAllTest(){
        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        categoryService.create(categoryA);
        Category categoryB=new Category(Long.valueOf(2),"Fashion");
        categoryService.create(categoryB);;
        Assert.assertTrue("Array size must be 2", categoryService.findAll().size()==2);

    }

    @Test
    public void updateTest(){
        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        categoryService.create(categoryA);
        Optional<Category> updateCategory1= categoryService.update(new Category(Long.valueOf(1),"Fashion"));
        Optional<Category> updateCategory2= categoryService.update(new Category(Long.valueOf(11),"Fashion"));

        Assert.assertTrue("Category name must change to Fashion",updateCategory1.get().getName().equals("Fashion"));
        Assert.assertTrue("Can't change the unregistered id",updateCategory2 == null);

    }

    @Test
    public void deleteTest(){

        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        categoryService.create(categoryA);
        Category categoryB=new Category(Long.valueOf(2),"Fashion");
        categoryService.create(categoryB);
        Category categoryC=new Category(Long.valueOf(3),"Sport");
        categoryService.create(categoryC);

        Category deleteCategory=categoryService.delete(Long.valueOf(2));
        Assert.assertTrue("Can't find category with id=2",categoryService.findById(deleteCategory.getId())==null);
        Assert.assertTrue("Current list size is 2",categoryService.findAll().size()==2);
        Assert.assertTrue("Can't delete id if id isn't registered",categoryService.delete(Long.valueOf(12))==null);


    }
}
