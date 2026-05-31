package com.pharmacy.service;

import com.pharmacy.model.Prescription;
import com.pharmacy.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    // Save or update a prescription
    @SuppressWarnings("null")
    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    // Get all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    // Get a single prescription by ID
    @SuppressWarnings("null")
    public Prescription getPrescription(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    // Delete a prescription by ID
    @SuppressWarnings("null")
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
