package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({"issueID", "issuedTo", "borrowedBook", "issueDate", "dueDate", "returned", "lastUpdated"})
public class Issue
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long issueID;

    @ManyToOne
    @JoinColumn(name = "issued_to_id", nullable = false)
    private Member issuedTo;

    @ManyToOne
    @JoinColumn(name = "borrowed_book_id", nullable = false)
    private Book borrowedBook;

    @CreatedDate
    private LocalDateTime issueDate;

    private LocalDateTime dueDate;

    private boolean returned;

    @LastModifiedDate
    private LocalDateTime lastUpdated;


}
