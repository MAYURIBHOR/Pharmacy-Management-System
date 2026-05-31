package com.pharmacy.controller;

import com.pharmacy.model.Sale;
import com.pharmacy.service.SaleService;
import com.pharmacy.service.CustomerService; // Added dependency
import com.pharmacy.service.MedicineService; // Added dependency
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
public class SalesController {

    private final SaleService saleService;
    private final CustomerService customerService;
    private final MedicineService medicineService;

    // Constructor Injection
    public SalesController(SaleService saleService, CustomerService customerService, MedicineService medicineService) {
        this.saleService = saleService;
        this.customerService = customerService;
        this.medicineService = medicineService;
    }

    // --- 1. READ (List Recent Sales for Dashboard) ---
    @GetMapping("/sales")
    public String viewSalesPage(Model model) {
        try {
            // FIX: Fetch data for the dashboard view to display recent transactions
            List<Sale> recentSales = saleService.getAllSales();
            model.addAttribute("sales", recentSales); // Passes list as 'sales'
        } catch (Exception e) {
            model.addAttribute("sales", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load recent sales.");
        }
        return "sales"; // Renders sales.html
    }

    // --- 2. START NEW SALE (Populates form with data) ---
    @GetMapping("/sales/new")
    public String startNewSale(Model model) {
        try {
            model.addAttribute("sale", new Sale());
            model.addAttribute("pageTitle", "New Sale Transaction");

            // Load data required by the POS form dropdowns
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("medicines", medicineService.getAllMedicines());

            return "sales_form"; // Renders sales_form.html
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not load essential data (Customers/Medicines).");
            return "sales";
        }
    }

    // --- 3. VIEW SALES HISTORY ---
    @GetMapping("/sales/history")
    public String viewSalesHistory(Model model) {
        try {
            // Data for the full history table
            model.addAttribute("salesHistory", saleService.getAllSales());
        } catch (Exception e) {
            model.addAttribute("salesHistory", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load full sales history.");
        }
        return "sales_history"; // Renders sales_history.html
    }

    // --- 4. VIEW SALE DETAILS (FIXES 404 ERROR ON 'View' Button) ---
    @GetMapping("/sales/view/{id}")
    public String viewSaleDetails(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Sale sale = saleService.getSale(id);

        if (sale == null) {
            ra.addFlashAttribute("errorMessage", "Sale record ID " + id + " not found.");
            return "redirect:/sales/history";
        }

        // FIX: Redirect to the available history page and pass the object (or a success
        // message)
        ra.addFlashAttribute("viewingSaleId", id); // Flash the ID to the next request
        ra.addFlashAttribute("viewingSale", sale); // Flash the object to the next request

        // OPTIONAL: Add a message to confirm which sale is being viewed
        ra.addFlashAttribute("successMessage", "Details for Sale ID " + id + " loaded (Redirected to History page).");

        return "redirect:/sales/history"; // Redirects to the view with data in FlashMap
    }

    // --- 5. SAVE SALE (POST Method) ---
    @PostMapping("/sales/save")
    public String saveSale(@ModelAttribute("sale") Sale sale, RedirectAttributes ra) {
        try {
            saleService.saveSale(sale);
            ra.addFlashAttribute("successMessage", "Sale completed successfully! Invoice ID: " + sale.getId());
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error processing sale: " + e.getMessage());
        }
        return "redirect:/sales";
    }
}