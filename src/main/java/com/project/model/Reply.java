package com.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "replies")
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reply_date")
    private LocalDate replyDate;

    //One-to-one relationship: one reply has one ticket
    @OneToOne(mappedBy = "reply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ticket ticket;


}
