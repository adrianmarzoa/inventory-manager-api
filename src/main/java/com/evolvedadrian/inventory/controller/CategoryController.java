package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.CategoryRequestDTO;
import com.evolvedadrian.inventory.dto.response.CategoryResponseDTO;
import com.evolvedadrian.inventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Integer id) {
        return this.categoryService.getCategoryById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<CategoryResponseDTO> findCategoryByName(@PathVariable String name) {
        return this.categoryService.findCategoryByName(name);
    }

    @PostMapping
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return this.categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        this.categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody CategoryRequestDTO category) {
        return this.categoryService.updateCategory(id, category);
    }
}
