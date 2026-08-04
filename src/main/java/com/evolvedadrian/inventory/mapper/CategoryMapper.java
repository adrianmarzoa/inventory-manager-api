package com.evolvedadrian.inventory.mapper;

import com.evolvedadrian.inventory.dto.request.CategoryRequestDTO;
import com.evolvedadrian.inventory.dto.response.CategoryResponseDTO;
import com.evolvedadrian.inventory.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequestDTO dto){
        Category category = new Category();

        category.setName(dto.getName());

        return category;
    }

    public CategoryResponseDTO toResponse(Category category){
        CategoryResponseDTO dto = new CategoryResponseDTO();

        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }
}
