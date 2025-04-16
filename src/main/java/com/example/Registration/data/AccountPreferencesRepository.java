package com.example.Registration.data;

import com.example.Registration.models.AccountPreferences;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountPreferencesRepository extends CrudRepository<AccountPreferences, Long> {
    Optional<AccountPreferences> findByAccountId(Long accountId);
}
