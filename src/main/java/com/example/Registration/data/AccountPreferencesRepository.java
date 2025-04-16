package com.example.Registration.data;

import com.example.Registration.models.UserPreferences;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {
    Optional<UserPreferences> findByAccountId(Long accountId);
}
