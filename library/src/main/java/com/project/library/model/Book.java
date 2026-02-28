package com.project.library.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)  // Automatically updates the lastUpdated attribute
@JsonPropertyOrder({"bookID", "title", "author", "genre", "isbn", "available", "copies", "lastUpdated"})

public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookID;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)    // maps the enums by strings instead of their index
    private Genre genre;
    @Column(unique = true)
    private String isbn;

    private boolean available = true;
    private int copies = -1;

    @LastModifiedDate
    private LocalDateTime lastUpdated;

}
