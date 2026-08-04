package com.evolvedadrian.inventory.mapper;

import com.evolvedadrian.inventory.dto.request.SupplierRequestDTO;
import com.evolvedadrian.inventory.dto.response.SupplierResponseDTO;
import com.evolvedadrian.inventory.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierRequestDTO dto) {
        Supplier supplier = new Supplier();

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());

        return supplier;
    }

    public SupplierResponseDTO toResponse(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();

        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setEmail(supplier.getEmail());
        dto.setPhone(supplier.getPhone());

        return dto;
    }
}
