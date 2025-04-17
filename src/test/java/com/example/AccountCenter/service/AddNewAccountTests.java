package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountRepository;
import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddNewAccountTests {
    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    private Account registeredAccount;

    @BeforeEach
    void setup() {
        registeredAccount = new Account();
        registeredAccount.setId(1L);
        registeredAccount.setEmail("@example@email.com");
    }

    @Test
    void itShouldAddNewAccount(){
        Account user = new Account();
        user.setEmail(registeredAccount.getEmail());

        when(repository.save(user)).thenReturn(registeredAccount);
        Result<Account> result = service.addNewAccount(user);

        assertThat(result.type()).isEqualTo(ResultType.SUCCESS);
        assertThat(result.payload()).isEqualTo(registeredAccount);
    }

    @Test
    void itShould_Not_AddAccountWithNonZeroId() {
        Account user = new Account(1L, "n/a");

        Result<Account> result = service.addNewAccount(user);

        assertThat(result.type()).isEqualTo(ResultType.FAILED);
        assertThat(result.payload()).isNull();
        assertThat(result.messages().get(0)).isEqualTo("id cannot be set");
    }

    @Test
    void itShouldAddAccountWithUniqueEmail() {
        String unique = "uniqueuniqueuniqueunique@unique.com";
        // for comparison later
        registeredAccount = new Account(1L, unique);

        Account user = new Account();
        user.setEmail(unique);

        when(repository.save(user)).thenReturn(registeredAccount);
        Result<Account> result = service.addNewAccount(user);

        assertThat(result.type()).isEqualTo(ResultType.SUCCESS);
        assertThat(result.payload()).isEqualTo(registeredAccount);
    }

    @Test
    void itShould_Not_AddDuplicateEmail() {
        Account user = new Account(0L, registeredAccount.getEmail());

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(registeredAccount));
        Result<Account> result = service.addNewAccount(user);

        assertThat(result.type()).isEqualTo(ResultType.FAILED);
        assertThat(result.payload()).isNull();
        assertThat(result.messages().get(0)).isEqualTo("email already taken");
    }
}
