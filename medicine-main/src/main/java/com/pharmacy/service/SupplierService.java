package com.pharmacy.service;

import com.pharmacy.model.Supplier;
import com.pharmacy.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    /**
     * Retrieves a supplier by their ID. This method will be used by the Converter.
     * Renamed to the common utility name 'getSupplier' for cleaner service use.
     * It performs the same logic as the old getSupplierById.
     */
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    // Kept for backward compatibility, although 'getSupplier' is preferred.
    public Supplier getSupplierById(Long id) {
        return getSupplier(id);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}