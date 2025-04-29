package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountRepository;
import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The service class for Account-related operations, including:
 * 1. creating a new account
 * 2. getting account details
 * 3. deleting an account
 *
 * Accounts are only required to have an email for registration.
 */

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    /**
     * Add a new account (only email mandatory)
     * @param user The Account details
     * @return Success if account created, otherwise Failure
     */
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

    /**
     * Return account details according to account ID
     * @param id The account ID
     * @return The Account
     */
    public Optional<Account> getAccountDetails(long id) {
        return id > 0 ? repository.findById(id) : Optional.empty();
    }

    /**
     * Return account details according to account email
     * @param email The account email
     * @return The Account
     */
    public Optional<Account> getAccountDetails(String email) {
        return email.isBlank() ? Optional.empty() : repository.findByEmail(email);
    }

    /**
     * Helper function for other services to check if account exists
     * @param id The account ID
     * @return True if exists, false otherwise
     */
    boolean existsById(long id) {
        return id > 0 && repository.existsById(id);
    }

    /**
     * Delete account according to its ID
     * @param id The account ID
     * @return True if the account existed, false otherwise
     */
    public boolean deleteAccountById(long id) {
        if (existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
