package com.project.repository;

import com.project.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    @Query("SELECT c FROM Concert c WHERE (c.date > CURRENT_DATE)")
    List<Concert> findAllFromNow();
}
