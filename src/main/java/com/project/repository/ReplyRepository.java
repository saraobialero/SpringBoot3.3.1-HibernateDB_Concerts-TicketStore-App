package com.project.repository;

import com.project.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Query("SELECT c FROM Reply c WHERE (c.replyDate > CURRENT_DATE)")
    List<Reply> findAllFromNow();
}
