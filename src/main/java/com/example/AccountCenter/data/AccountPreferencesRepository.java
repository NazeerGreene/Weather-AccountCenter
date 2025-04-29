package com.example.AccountCenter.data;

import com.example.AccountCenter.models.AccountPreferences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountPreferencesRepository extends CrudRepository<AccountPreferences, Long> {
    Optional<AccountPreferences> findByAccount_Id(Long accountId);
}
