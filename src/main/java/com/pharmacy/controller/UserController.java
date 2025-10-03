package com.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.pharmacy_management.model.Medicine;
import com.pharmacy.repository.MedicineRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final MedicineRepository medicineRepository;

    public UserController(MedicineRepository medicineRepository) {
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
        model.addAttribute("medicines", medicines);
        return "user-dashboard";
    }

    @PostMapping("/sell/{id}")
    public String sellMedicine(@PathVariable Long id, @RequestParam int quantity) {
        Medicine med = medicineRepository.findById(id).orElseThrow();
        if (med.getQuantity() >= quantity) {
            med.setQuantity(med.getQuantity() - quantity);
            medicineRepository.save(med);
        }
        return "redirect:/user/dashboard";
    }
}
