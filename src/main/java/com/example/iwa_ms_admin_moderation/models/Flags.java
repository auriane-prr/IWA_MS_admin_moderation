package com.example.iwa_ms_admin_moderation.models;

import jakarta.persistence.*;

@Entity
@Table(name = "flags")
public class Flags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flagId;

    @Column(nullable = true)
    private Integer locationId;

    @Column(nullable = true)
    private Integer userId;

    @Column(nullable = true)
    private Integer commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reason;

    @Column(nullable = true)
    private Integer reviewedBy;

    @Column(length = 100, nullable = false)
    private String status = "pending";

    // Getters et Setters
    public Integer getFlagId() {
        return flagId;
    }

    public void setFlagId(Integer flagId) {
        this.flagId = flagId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(Integer reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
