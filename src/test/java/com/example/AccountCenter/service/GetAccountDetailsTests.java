package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountRepository;
import com.example.AccountCenter.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class GetAccountDetailsTests {

    private Account registeredAccount;

    @BeforeEach
    void setup() {
        registeredAccount = new Account();
        registeredAccount.setId(1L);
        registeredAccount.setEmail("example@email.com");
    }

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    @Test
    void itShouldGetAccountDetailsById() {
        long accountId = 1;

        when(repository.findById(accountId)).thenReturn(Optional.of(registeredAccount));
        Optional<Account> found = service.getAccountDetails(accountId);

        assertThat(found).isPresent();
        Account account = found.get();

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(registeredAccount.getId());
        assertThat(account.getEmail()).isEqualTo(registeredAccount.getEmail());
    }

    @Test
    void itShould_Not_GetAccountDetailsForZeroId() {
        long accountId = 0;

        when(repository.findById(accountId)).thenReturn(Optional.empty());
        Optional<Account> found = service.getAccountDetails(accountId);

        assertThat(found).isEmpty();
    }

    @Test
    void itShould_Not_GetAccountDetailsForNegativeId() {
        long accountId = -1;

        when(repository.findById(accountId)).thenReturn(Optional.empty());
        Optional<Account> found = service.getAccountDetails(accountId);

        assertThat(found).isEmpty();
    }

    @Test
    void itShouldGetAccountDetailsByEmail() {
        String email = registeredAccount.getEmail();

        when(repository.findByEmail(email)).thenReturn(Optional.of(registeredAccount));
        Optional<Account> found = service.getAccountDetails(email);

        assertThat(found).isPresent();
        Account account = found.get();

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(registeredAccount.getId());
        assertThat(account.getEmail()).isEqualTo(registeredAccount.getEmail());
    }

    @Test
    void itShould_Not_GetAccountDetailsForInvalidEmail() {
        String email = "invalid";

        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<Account> found = service.getAccountDetails(email);

        assertThat(found).isEmpty();
    }
}
