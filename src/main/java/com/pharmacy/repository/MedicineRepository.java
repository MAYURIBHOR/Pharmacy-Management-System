package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pharmacy.pharmacy_management.model.Medicine;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByNameContainingIgnoreCase(String name); // For search
}
