package com.example.Registration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    private String preferredLocation;
    private LocalDateTime createdAt = LocalDateTime.now();
}
