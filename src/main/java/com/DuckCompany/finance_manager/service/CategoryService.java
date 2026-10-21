package com.DuckCompany.finance_manager.service;


import com.DuckCompany.finance_manager.model.Category;
import com.DuckCompany.finance_manager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//1. Controller
//
//Receives the request to create a category.
//
//2. Service
//
//Coordinates what needs to happen.
//
//3. Repository
//
//Saves the category in PostgreSQL.
@Service //tells spring that the class is a componenet it should manage, Spring can created a instance of that class.
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //    private: only the CategoryService class can access this field directly.
//    final: the field must be assigned once and cannot later be reassigned CategoryRepository: the type of object we want to use.
//    categoryRepository: the variable that holds the reference ID;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
//  this.categoryRepository refers to the field belonging to the current object.
//  categoryRepository refers to the parameter received by the constructor.
//  = assigns the parameter's value to the field.
        }

        public List<Category> getAllCategories() { //connect the service to the repository and returned list of categories
        return categoryRepository.findAll();
        }

//provides the save entity so our service method can return a single Category
        public Category saveCategory(Category category) {
            return categoryRepository.save(category); //ask spring data jpa to save the category in database
        }


    public void deleteCategory(Long id) { //deletes the category with the specific ID
        categoryRepository.deleteById(id);
    }
    }
