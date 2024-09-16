package com.project.repository;

import com.project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :idUser AND o.ticket.reply.id = :idTicket")
    Optional<Order> findByUserIdAndTicketId(@Param("idUser") Integer idUser, @Param("idTicket") Integer idTicket);

    @Query("SELECT o FROM Order o WHERE o.user.id = :idUser")
    List<Order> findByUserId(@Param("idUser") Integer idUser);

}

