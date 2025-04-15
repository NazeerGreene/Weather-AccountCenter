package com.example.Registration.service;

import com.example.Registration.data.AccountRepository;
import com.example.Registration.models.Account;
import com.example.Registration.utils.Result;
import com.example.Registration.utils.ResultType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    Result<Account> registerNewUser(@NonNull Account user) {
        Result<Account> result = new Result<>();

        // 1. user can't have id, otherwise wrong endpoint
        if (user.getId() != 0) {
            result.type(ResultType.FAILED);
            result.add("id cannot be set");

        // 2. user must have unique email
        } else if (accountRepository.findByEmail(user.getEmail()).isPresent()) {
            result.type(ResultType.FAILED);
            result.add("email already taken");

        // 3. no preferences will be mandatory
        } else {
            Account _registered = accountRepository.save(user);
            result.type(ResultType.SUCCESS);
            result.payload(_registered);
        }

        return result;
    }

    void getUserPreferenceDetails(@NonNull Account user) {
        // only returns user id and preferences, no email, no createdAt
        Optional<Account> account = accountRepository.findById(user.getId());
        return;
    }

    void deleteUserById() {
        // only returns success statement and user id (now-deleted)
        return;
    }

}
