package com.mangakousei.mangakousei_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mangakousei.mangakousei_backend.entity.entity.Series;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    long countByCreatorUserId(Long userId);
    long countByEditorUserId(Long userId);
    @Query("SELECT COUNT(c) FROM Chapter c WHERE c.series.creator.userId = :userId")
    long countChaptersByCreatorUserId(@Param("userId") Long userId);
    @Query("SELECT COUNT(p) FROM Page p WHERE p.chapter.series.creator.userId = :userId")
    long countPagesByCreatorUserId(@Param("userId") Long userId);

    List<Series> findByCreatorUserIdOrderByApprovedAtDesc(Long userId);
    List<Series> findByEditorUserIdOrderByApprovedAtDesc(Long editorId);
}
