package com.example.Registration.data;

import com.example.Registration.models.Account;
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
        user.setPreferredLocation("77471");

        Account saved = accountRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("nazeer@example.com");
    }

    @Test
    void itShouldFindByEmail() {
        Account user = new Account();
        user.setEmail("zeer@example.com");
        user.setPreferredLocation("77001");
        accountRepository.save(user);

        Optional<Account> found = accountRepository.findByEmail("zeer@example.com");

        assertThat(found).isPresent();
    }

    @Test
    void itShouldNotAllowDuplicateEmail() {
        Account user1 = new Account();
        user1.setEmail("unique@example.com");
        user1.setPreferredLocation("77000");
        accountRepository.save(user1);

        Account user2 = new Account();
        user2.setEmail("unique@example.com");
        user2.setPreferredLocation("77001");

        assertThrows(DataIntegrityViolationException.class, () -> {
            accountRepository.save(user2);
        });
    }

    @Test
    void itShouldUpdateUserPreferredLocation() {
        Account user = new Account();
        user.setEmail("geo@example.com");
        user.setPreferredLocation("12345");
        accountRepository.save(user);

        user.setPreferredLocation("99999");
        Account updated = accountRepository.save(user);

        assertThat(updated.getPreferredLocation()).isEqualTo("99999");
    }

    @Test
    void itShouldDeleteUser() {
        Account user = new Account();
        user.setEmail("temp@example.com");
        user.setPreferredLocation("40404");
        accountRepository.save(user);

        accountRepository.delete(user);

        Optional<Account> deleted = accountRepository.findByEmail("temp@example.com");
        assertThat(deleted).isEmpty();
    }

}