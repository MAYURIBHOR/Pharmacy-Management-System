package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // STOCK_LOW, EXPIRY, PRESCRIPTION_REFILL
    private String message;
    private LocalDateTime createdAt;
    private boolean sent;
    private boolean active; // You added this field

    // CRITICAL FIX: Add cascade = CascadeType.ALL
    // This tells the database: "If the parent Medicine is deleted, delete this
    // Alert."
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}