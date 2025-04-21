package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountPreferencesRepository;
import com.example.AccountCenter.models.AccountPreferences;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
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
        long accountId = prefs.getAccount().getId();

        if (!accountService.existsById(accountId)) {
            return accountNotFound(accountId, result);
        }

        // nothing has to be immediately set
        AccountPreferences savedPrefs = repository.save(prefs);
        result.type(ResultType.SUCCESS);
        result.payload(savedPrefs);
        return result;
    }

    Result<AccountPreferences> getPreferencesByAccountId(long accountId) {
        Result<AccountPreferences> result = new Result<>();

        if (!accountService.existsById(accountId)) {
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
