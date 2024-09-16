package com.project.model;


import com.project.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Many-to-one relationship with Client entity: many orders for single client
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    // Many-to-one relationship with Ticket entity: many orders for single ticket (Carnet)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket",  referencedColumnName = "id")
    private Ticket ticket;

    @Column(name = "payment", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "qta")
    private int qta;

}
