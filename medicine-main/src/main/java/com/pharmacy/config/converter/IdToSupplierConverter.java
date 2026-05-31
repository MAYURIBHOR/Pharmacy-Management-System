package com.pharmacy.config.converter;

import com.pharmacy.model.Supplier;
import com.pharmacy.service.SupplierService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull; // <-- NEW: Import the annotation

@Component
public class IdToSupplierConverter implements Converter<String, Supplier> {

    // 1. FIX: Removed @Autowired annotation on field (using final and constructor)
    private final SupplierService supplierService;

    // 2. FIX: Implemented Constructor Injection (removes the need for @Autowired)
    public IdToSupplierConverter(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    // 3. FIX: Added @NonNull to the parameters as required by the Converter
    // interface
    public Supplier convert(@NonNull String sourceId) {
        if (sourceId.isEmpty() || sourceId.equals("null")) {
            return null; // Handles the "-- Select a Supplier --" option
        }

        try {
            // FIX: Handle potential NumberFormatException if the ID isn't a number
            Long id = Long.valueOf(sourceId);

            // Use the service to fetch the full Supplier object
            return supplierService.getSupplier(id);
        } catch (NumberFormatException e) {
            // Log this error, as it means the form sent invalid data
            System.err.println("Conversion Error: Invalid Supplier ID format received: " + sourceId);
            return null;
        }
    }
}