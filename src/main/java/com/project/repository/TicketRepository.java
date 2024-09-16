package com.project.repository;

import com.project.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.qta > 0")
    List<Ticket> findAvailableTickets();

    /*
    @Query("SELECT t FROM Ticket t JOIN FETCH t.product c WHERE c.id = :idProduct")
    Optional<Ticket> findWithConcertById(@Param("idProduct") Integer idProduct);


    @Query(value = "SELECT t.id, t.available_qta, t.id_concert, " +
            "c.id as concert_id, c.city, c.band, c.reply, c.available_place, c.date, c.price " +
            "FROM tickets t " +
            "JOIN concerts c ON t.id_concert = c.id " +
            "WHERE c.id = :idConcert",
            nativeQuery = true)
    Optional<Ticket> findByIdConcert(@Param("idConcert") Integer idConcert);*/
}

