package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate saleDate;
    private double totalAmount;
    private String paymentType;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "prescrption_id", nullable = true)
    private Prescription prescription;

    // CRITICAL FIX: Add REMOVE cascade to ensure the join table records are wiped
    // when a Sale or Medicine is deleted.
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "sale_medicines", joinColumns = @JoinColumn(name = "sale_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicines;
}