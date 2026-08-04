package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.SupplierRequestDTO;
import com.evolvedadrian.inventory.dto.response.SupplierResponseDTO;
import com.evolvedadrian.inventory.entity.Supplier;
import com.evolvedadrian.inventory.mapper.SupplierMapper;
import com.evolvedadrian.inventory.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public List<SupplierResponseDTO> getAllSuppliers() {
        return this.supplierRepository.findAll().stream().map(this.supplierMapper::toResponse).toList();
    }

    public SupplierResponseDTO getSupplierById(Integer id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found."));
        return this.supplierMapper.toResponse(supplier);
    }

    public SupplierResponseDTO createSupplier(SupplierRequestDTO dto) {
        if(this.supplierRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already exists.");
        }

        Supplier supplier = this.supplierMapper.toEntity(dto);

        Supplier created = this.supplierRepository.save(supplier);

        return this.supplierMapper.toResponse(created);
    }

    public SupplierResponseDTO updateSupplier(Integer id, SupplierRequestDTO dto) {
        if (!this.supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier does not exist.");
        }

        Supplier supplier = this.supplierMapper.toEntity(dto);

        supplier.setId(id);

        Supplier updated = this.supplierRepository.save(supplier);

        return this.supplierMapper.toResponse(updated);
    }

    public void deleteSupplier(Integer id) {
        this.supplierRepository.deleteById(id);
    }

    public List<SupplierResponseDTO> findSupplierByName(String name) {
        List<Supplier> suppliers = this.supplierRepository.findByName(name);

        return suppliers.stream().map(this.supplierMapper::toResponse).toList();
    }
}
