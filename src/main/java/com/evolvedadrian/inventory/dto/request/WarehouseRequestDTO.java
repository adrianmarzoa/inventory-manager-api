package com.evolvedadrian.inventory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WarehouseRequestDTO {
    @Size(max = 150, message = "Name must be at most 150 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 255, message = "Location must be at most 255 characters")
    private String location;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
