package com.project.model;

import com.project.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Many-to-one relationship with Client entity: many orders for single client
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column(name = "qta")
    private int qta;

    @Column (name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // Many-to-one relationship with Ticket entity: many orders for single ticket (Carnet)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;
}
