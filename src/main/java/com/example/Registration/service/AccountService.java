package com.example.Registration.service;

import com.example.Registration.data.AccountRepository;
import com.example.Registration.models.Account;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    void registerNewUser(@NonNull Account user) {
        return;
    }

    void getUserPreferenceDetails() {
        return;
    }

    void deleteUserById() {
        return;
    }

}
