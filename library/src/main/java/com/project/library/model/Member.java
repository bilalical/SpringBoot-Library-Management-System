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
@JsonPropertyOrder({"memberID", "name", "cms", "department", "lastUpdated"})
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberID;

    private String name;

    @Column(unique = true, nullable = false)
    private long cms = -1;
    @Enumerated(EnumType.STRING)
    private Department department;
    @LastModifiedDate
    private LocalDateTime lastUpdated;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime joinDate;

}
