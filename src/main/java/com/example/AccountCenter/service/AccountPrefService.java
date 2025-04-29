package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountPreferencesRepository;
import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.models.AccountPreferences;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The service class for Account-related preferences.
 * Either set new preferences or receiving existing ones.
 */

@Service
@AllArgsConstructor
public class AccountPrefService {
    private final AccountPreferencesRepository repository;
    private final AccountService accountService;

    /**
     * Set new preferences for an account
     * @param prefs The new preferences (Account mandatory)
     * @return A Result of the preferences
     */
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

    /**
     * Get preferences according to account ID
     * @param accountId The account ID
     * @return The Result of the preferences
     */
    public Result<AccountPreferences> getPreferencesByAccountId(long accountId) {
        Result<AccountPreferences> result = new Result<>();

        if (!accountService.existsById(accountId)) {
            return accountNotFound(accountId, result);
        }

        result.type(ResultType.SUCCESS);

        AccountPreferences found = repository.findByAccount_Id(accountId).orElseGet(() -> {
            Account account = accountService.getAccountDetails(accountId).get();
            AccountPreferences newPrefs = new AccountPreferences();

            newPrefs.setAccount(account);
            repository.save(newPrefs);
            return newPrefs;
        });

        result.type(ResultType.SUCCESS);
        result.payload(found);

        return result;
    }


    private Result<AccountPreferences> accountNotFound(long accountId, Result<AccountPreferences> result) {
        result.type(ResultType.FAILED);
        result.add(String.format("account with id{%d} does not exist", accountId));
        return result;
    }
}
