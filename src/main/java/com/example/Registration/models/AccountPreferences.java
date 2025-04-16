package com.example.Registration.models;

import com.example.Registration.models.database.Language;
import com.example.Registration.models.database.UnitGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bridge table between user & unit_groups, languages, and location
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_preferences")
public class AccountPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // NOTE: Keeping this separate from Account
    // to keep lean table schema and ORM
    @Column(name = "account_id", nullable = false)
    private Long accountId;
    private String location;

    @ManyToOne
    @JoinColumn(name = "unit_group_id", referencedColumnName = "id")
    private UnitGroup unitGroup;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
}
