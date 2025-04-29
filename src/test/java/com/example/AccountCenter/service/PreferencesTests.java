package com.example.AccountCenter.service;

import com.example.AccountCenter.data.AccountPreferencesRepository;
import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.models.AccountPreferences;
import com.example.AccountCenter.models.Language;
import com.example.AccountCenter.models.UnitGroup;
import com.example.AccountCenter.utils.Result;
import com.example.AccountCenter.utils.ResultType;
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
public class PreferencesTests {

    private Account registered;

    @Mock
    private AccountPreferencesRepository repository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountPrefService service;

    @BeforeEach
    void setup() {
        registered = new Account();
        registered.setId(1L);
        registered.setEmail("example@email.com");
    }

    @Test
    void itShouldAddNewPreferencesForExistingAccount() {
        AccountPreferences prefs = new AccountPreferences();
        prefs.setId(registered.getId());
        prefs.setLocation("77471");
        prefs.setAccount(registered);

        Language language = new Language();
        language.setCode("en");
        language.setName("English");
        prefs.setLanguage(language);

        UnitGroup unitGroup = new UnitGroup();
        unitGroup.setCode("metric");
        unitGroup.setDescription("Metric system");
        prefs.setUnitGroup(unitGroup);

        when(accountService.existsById(registered.getId())).thenReturn(true);
        when(repository.save(prefs)).thenReturn(prefs);

        Result<AccountPreferences> result = service.setAccountPreferences(prefs);

        assertThat(result).isNotNull();
        assertThat(result.type()).isEqualTo(ResultType.SUCCESS);
        assertThat(result.payload().getLanguage().getCode()).isEqualTo("en");
        assertThat(result.payload().getUnitGroup().getCode()).isEqualTo("metric");
        assertThat(result.payload().getAccount().getEmail()).isEqualTo(registered.getEmail());
    }

    @Test
    void itShouldNotAddNewPreferencesForNonExistingAccount() {
        when(accountService.existsById(registered.getId())).thenReturn(false);

        AccountPreferences prefs = new AccountPreferences();
        prefs.setAccount(registered);

        Result<AccountPreferences> found = service.getPreferencesByAccountId(registered.getId());

        assertThat(found).isNotNull();
        assertThat(found.type()).isEqualTo(ResultType.FAILED);
        assertThat(found.messages().get(0)).contains("does not exist");
    }

    @Test
    void itShouldNotReturnEmptyPreferencesForNewAccounts() {
        when(accountService.existsById(registered.getId())).thenReturn(true);
        when(accountService.getAccountDetails(registered.getId())).thenReturn(Optional.of(registered));

        Result<AccountPreferences> prefs = service.getPreferencesByAccountId(registered.getId());

        assertThat(prefs).isNotNull();
        assertThat(prefs.payload().getAccount().getId()).isEqualTo(registered.getId());
        assertThat(prefs.payload().getAccount().getEmail()).isEqualTo(registered.getEmail());
        // should be empty
        assertThat(prefs.payload().getLanguage()).isNull();
        assertThat(prefs.payload().getLocation()).isNull();
    }
}
