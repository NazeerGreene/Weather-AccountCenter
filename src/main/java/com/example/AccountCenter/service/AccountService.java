package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountRepository;
import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Result<Account> addNewAccount(@NonNull Account user) {
        Result<Account> result = new Result<>();

        // 1. user can't have id, otherwise wrong endpoint
        if (user.getId() != 0) {
            result.type(ResultType.FAILED);
            result.add("id cannot be set");
            return result;
        }

        // 2. user must have unique email
        if (getAccountDetails(user.getEmail()).isPresent()) {
            result.type(ResultType.FAILED);
            result.add("email already taken");
            return result;
        }

        // 3. no preferences will be mandatory
        Account registered = repository.save(user);
        result.type(ResultType.SUCCESS);
        result.payload(registered);

        return result;
    }

    public Optional<Account> getAccountDetails(long id) {
        return id > 0 ? repository.findById(id) : Optional.empty();
    }

    public Optional<Account> getAccountDetails(String email) {
        return email.isBlank() ? Optional.empty() : repository.findByEmail(email);
    }

    boolean existsById(long id) {
        return id > 0 && repository.existsById(id);
    }

    public boolean deleteAccountById(long id) {
        if (existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
