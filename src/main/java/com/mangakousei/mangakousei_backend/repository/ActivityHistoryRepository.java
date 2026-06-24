package com.mangakousei.mangakousei_backend.repository;

import com.mangakousei.mangakousei_backend.entity.system.ActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {


    List<ActivityHistory> findByUserId(Long userId);


    List<ActivityHistory> findByUserIdAndActionType(Long userId, String actionType);


    @Modifying
    @Query("DELETE FROM ActivityHistory a WHERE a.timestamp < :cutoffDate")
    void deleteOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);
}