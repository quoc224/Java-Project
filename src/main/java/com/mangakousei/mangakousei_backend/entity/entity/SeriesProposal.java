package com.mangakousei.mangakousei_backend.entity.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "series_proposals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeriesProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    @EqualsAndHashCode.Include
    private Long proposalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mangaka_id", nullable = false)
    @JsonBackReference("MangakaProposals")
    private User mangaka;

    @Column(name = "working_title", nullable = false)
    private String workingTitle;

    @Column(name = "genre")
    private String genre;

    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    @JsonBackReference("EditorReviewedProposals")
    private User reviewedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "decided_at")
    private LocalDateTime decidedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }
}