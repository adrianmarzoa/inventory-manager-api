package com.evolvedadrian.inventory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {
    @Size(max = 100, message = "Name must be at most 100 characters")
    @NotBlank(message = "Name is required")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
