package com.pharmacy.service;

import com.pharmacy.model.Sale;
import com.pharmacy.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @SuppressWarnings("null")
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    /**
     * FIX: Adds the missing method required by the SalesController.viewSaleDetails.
     * Fetches a single Sale record by its ID.
     */
    @SuppressWarnings("null")
    public Sale getSale(Long id) {
        // Delegates the find operation to the JpaRepository.
        return saleRepository.findById(id).orElse(null);
    }

    // You would add other methods like deleteSale(Long id) here
}