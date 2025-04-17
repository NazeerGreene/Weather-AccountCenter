package com.example.AccountCenter.data;

import com.example.AccountCenter.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void itShouldSaveUser() {
        Account user = new Account();
        user.setEmail("nazeer@example.com");

        Account saved = accountRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("nazeer@example.com");
    }

    @Test
    void itShouldFindByEmail() {
        Account user = new Account();
        user.setEmail("zeer@example.com");
        accountRepository.save(user);

        Optional<Account> found = accountRepository.findByEmail("zeer@example.com");

        assertThat(found).isPresent();
    }

    @Test
    void itShould_Not_AllowDuplicateEmail() {
        Account user1 = new Account();
        user1.setEmail("unique@example.com");
        accountRepository.save(user1);

        Account user2 = new Account();
        user2.setEmail("unique@example.com");

        assertThrows(DataIntegrityViolationException.class, () -> {
            accountRepository.save(user2);
        });
    }

    @Test
    void itShouldDeleteUser() {
        Account user = new Account();
        user.setEmail("temp@example.com");
        accountRepository.save(user);

        accountRepository.delete(user);

        Optional<Account> deleted = accountRepository.findByEmail("temp@example.com");
        assertThat(deleted).isEmpty();
    }
}