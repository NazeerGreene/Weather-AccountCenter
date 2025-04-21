package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountRepository;
import com.example.AccountCenter.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class DeleteAccountTests {
    private Account registeredAccount;

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountService service;

    @BeforeEach
    void setup() {
        registeredAccount = new Account();
        registeredAccount.setId(1L);
        registeredAccount.setEmail("example@email.com");
    }

    @Test
    void itShouldDeleteRegisteredAccountAndReturnTrue() {
        long accountId = registeredAccount.getId();

        when(repository.existsById(accountId)).thenReturn(true);

        assertThat(service.deleteAccountById(accountId)).isTrue();
        verify(repository, atLeastOnce()).deleteById(accountId);
    }

    @Test
    void itShould_Not_ReturnTrueForNonExistentAccount() {
        long accountId = -1L;

        when(repository.existsById(accountId)).thenReturn(false);

        assertThat(service.deleteAccountById(accountId)).isFalse();
        verify(repository, never()).deleteById((accountId));
    }
}
