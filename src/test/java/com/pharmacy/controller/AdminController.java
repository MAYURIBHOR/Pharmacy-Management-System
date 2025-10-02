package com.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.repository.MedicineRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MedicineRepository medicineRepository;

    public AdminController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Medicine> medicines;
        if (search != null && !search.isEmpty()) {
            medicines = medicineRepository.findByNameContainingIgnoreCase(search);
        } else {
            medicines = medicineRepository.findAll();
        }

        // Add flags for low stock and near expiry
        medicines.forEach(med -> {
            boolean lowStock = med.getQuantity() <= 5;
            boolean nearExpiry = med.getExpiryDate().isBefore(java.time.LocalDate.now().plusDays(30));
            med.setLowStock(lowStock);
            med.setNearExpiry(nearExpiry);
        });

        model.addAttribute("medicines", medicines);
        return "admin-dashboard";
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "add-medicine";
    }

    @PostMapping("/add")
    public String addMedicine(@ModelAttribute Medicine medicine) {
        medicineRepository.save(medicine);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editMedicineForm(@PathVariable Long id, Model model) {
        Medicine med = medicineRepository.findById(id).orElseThrow();
        model.addAttribute("medicine", med);
        return "edit-medicine";
    }

    @PostMapping("/edit")
    public String editMedicine(@ModelAttribute Medicine medicine) {
        medicineRepository.save(medicine);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }
}
