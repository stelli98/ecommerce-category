package com31.websiteecommerce.websiteecommerce.category;

import com31.websiteecommerce.websiteecommerce.category.entity.Category;
import com31.websiteecommerce.websiteecommerce.category.repository.CategoryRepository;
import com31.websiteecommerce.websiteecommerce.category.service.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.Optional;

public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService=new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void createTest(){
        Category categoryA=new Category(Long.valueOf(1), "Electronic");

        Mockito.when(categoryRepository.save(categoryA)).thenReturn(categoryA);
        Category save=categoryService.create(categoryA);

        Assert.assertNotNull("Category isn't null",save);
        Assert.assertTrue("Category name is electronic", categoryA.getName().equals(save.getName()));
    }

    @Test
    public void findByIdTest(){
        Category categoryA=new Category(1L, "Electronic");
        Mockito.when(categoryRepository.save(categoryA)).thenReturn(categoryA);
        categoryService.create(categoryA);

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(categoryA));
        Optional<Category> searchCategory1= categoryService.findById(1L);
        Assert.assertTrue("Must return Electronic Category Details", categoryA.equals(searchCategory1.get()));


        Mockito.when(categoryRepository.findById(13L)).thenReturn(Optional.empty());
        Optional<Category> searchCategory2= categoryService.findById(13L);
        Assert.assertFalse("Can't find unregistered id",searchCategory2.isPresent());
    }

    @Test
    public void findAllTest(){
        Category categoryA=new Category(Long.valueOf(1), "Electronic");
        Mockito.when(categoryRepository.save(categoryA)).thenReturn(categoryA);
        categoryService.create(categoryA);

        Category categoryB=new Category(Long.valueOf(2),"Fashion");
        Mockito.when(categoryRepository.save(categoryA)).thenReturn(categoryA);
        categoryService.create(categoryB);

        ArrayList<Category> categories=new ArrayList<>();
        categories.add(categoryA);
        categories.add(categoryB);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
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
