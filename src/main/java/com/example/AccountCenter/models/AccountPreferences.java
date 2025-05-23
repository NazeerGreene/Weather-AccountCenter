package com.example.AccountCenter.models;

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

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_account_to_preferences"))
    private Account account;

    private String location;

    @ManyToOne
    @JoinColumn(name = "unit_group_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_preferences_unit_group"))
    private UnitGroup unitGroup;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_preferences_language"))
    private Language language;
}
