package com.evolvedadrian.inventory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {
    @Size(max = 150, message = "Name must be at most 150 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 150, message = "Email must be at most 150 characters")
    private String email;

    @Size(max = 30, message = "Phone must be at most 30 characters")
    private String phone;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
