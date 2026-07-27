package com.evolvedadrian.inventory.dto.request;

public class WarehouseRequestDTO {
    private String name;

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
