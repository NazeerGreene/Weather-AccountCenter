package com.example.AccountCenter.data;

import com.example.AccountCenter.models.Account;
import com.example.AccountCenter.models.AccountPreferences;
import com.example.AccountCenter.models.Language;
import com.example.AccountCenter.models.UnitGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AccountPrefsRepositoryTests {
    @Autowired
    private AccountPreferencesRepository preferencesRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UnitGroupRepository unitGroupRepo;

    @Autowired
    private LanguageRepository languageRepo;

    @Test
    void itShouldSaveAndFindPreferencesByAccount() {
        Account account = new Account();
        account.setEmail("test@example.com");
        Account savedAccount = accountRepo.save(account);

        UnitGroup unitGroup = new UnitGroup();
        unitGroup.setCode("metric");
        UnitGroup savedUnitGroup = unitGroupRepo.save(unitGroup);

        Language language = new Language();
        language.setCode("en");
        language.setName("English");
        Language savedLanguage = languageRepo.save(language);

        AccountPreferences prefs = new AccountPreferences();
        prefs.setAccount(savedAccount);
        prefs.setLocation("77471");
        prefs.setUnitGroup(savedUnitGroup);
        prefs.setLanguage(savedLanguage);

        preferencesRepo.save(prefs);
        AccountPreferences found = preferencesRepo.findByAccount_Id(savedAccount.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getLocation()).isEqualTo("77471");
        assertThat(found.getUnitGroup().getCode()).isEqualTo("metric");
        assertThat(found.getLanguage().getCode()).isEqualTo("en");
    }
}
