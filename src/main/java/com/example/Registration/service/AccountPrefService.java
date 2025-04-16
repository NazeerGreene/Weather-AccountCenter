package com.example.Registration.service;

import com.example.Registration.data.AccountPreferencesRepository;
import com.example.Registration.models.AccountPreferences;
import com.example.Registration.utils.Result;
import com.example.Registration.utils.ResultType;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountPrefService {
    private final AccountPreferencesRepository repository;
    private final AccountService accountService;

    public Result<AccountPreferences> setAccountPreferences(@NonNull AccountPreferences prefs) {
        Result<AccountPreferences> result = new Result<>();

        if (!accountService.confirmAccountExists(prefs.getAccountId())) {
            return accountNotFound(prefs.getAccountId(), result);
        }

        // nothing has to be immediately set
        AccountPreferences savedPrefs = repository.save(prefs);
        result.type(ResultType.SUCCESS);
        result.payload(savedPrefs);
        return result;
    }

    Result<AccountPreferences> getPreferencesByAccountId(long accountId) {
        Result<AccountPreferences> result = new Result<>();
        // only returns user id and preferences, no email, no createdAt

        if (!accountService.confirmAccountExists(accountId)) {
            return accountNotFound(accountId, result);
        }

        Optional<AccountPreferences> found = repository.findByAccountId(accountId);

        if (found.isPresent()) {
            result.type(ResultType.SUCCESS);
            result.payload(found.get());
        } else {
            result.type(ResultType.FAILED);
            result.add("set preferences for account first");
        }

        return result;
    }


    private Result<AccountPreferences> accountNotFound(long accountId, Result<AccountPreferences> result) {
        result.type(ResultType.FAILED);
        result.add(String.format("account with id{%d} does not exist", accountId));
        return result;
    }
}
