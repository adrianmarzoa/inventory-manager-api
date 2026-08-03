package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.entity.Category;
import com.evolvedadrian.inventory.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found."));
    }

    public Category createCategory(Category category) {
        if(this.categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Name already exists.");
        }
        return this.categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        if (!this.categoryRepository.existsById(category.getId())) {
            throw new RuntimeException("Category does not exist.");
        }
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        Category category = getCategoryById(id);
        this.categoryRepository.delete(category);
    }

    public Optional<Category> findCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }
}
