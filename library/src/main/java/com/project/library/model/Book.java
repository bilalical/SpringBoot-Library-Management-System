package com.project.library.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)

public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookID;

    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(unique = true)
    private String ISBN;

    private boolean available = true;
    private int copies = -1;

    @LastModifiedDate
    private LocalDateTime lastUpdated;
}
