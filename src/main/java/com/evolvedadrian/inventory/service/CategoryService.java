package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.CategoryRequestDTO;
import com.evolvedadrian.inventory.dto.response.CategoryResponseDTO;
import com.evolvedadrian.inventory.entity.Category;
import com.evolvedadrian.inventory.mapper.CategoryMapper;
import com.evolvedadrian.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(this.categoryMapper::toResponse).toList();
    }

    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found."));

        return this.categoryMapper.toResponse(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (this.categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Name already exists.");
        }

        Category category = this.categoryMapper.toEntity(dto);

        Category saved = this.categoryRepository.save(category);

        return this.categoryMapper.toResponse(saved);
    }

    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO dto) {
        if (!this.categoryRepository.existsById(id)) {
            throw new RuntimeException("Category does not exist.");
        }

        Category category = this.categoryMapper.toEntity(dto);

        category.setId(id);

        Category saved = this.categoryRepository.save(category);

        return this.categoryMapper.toResponse(saved);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteById(id);
    }

    public Optional<CategoryResponseDTO> findCategoryByName(String name) {
        Optional<Category> category = this.categoryRepository.findByName(name);
        return category.map(this.categoryMapper::toResponse);
    }
}
