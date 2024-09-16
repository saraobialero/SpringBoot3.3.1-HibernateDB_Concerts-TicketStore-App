package com.project.model;

import com.project.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city", nullable = false)
    private String city;

   @Column(name = "location", nullable = false)
    private String location;

   @Column(name= "band", nullable = false)
    private String band;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "qta")
    private int qta;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reply")
    private Reply reply;

    //Many-to-one relationship with Sellers: More ticket for same seller
    @ManyToOne()
    @JoinColumn(name = "id_seller", referencedColumnName = "id")
    private Seller seller;

    //One-to_many relationship with order: one ticket for more orders
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

}
