package org.arhor.diploma.repository;

import org.arhor.diploma.TestExecutionContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.arhor.diploma.TestUtils.generateAccountWithFilledFields;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class AccountRepositoryTest extends TestExecutionContext {

    @Autowired
    private AccountRepository repository;

    @Test
    @Transactional
    void shouldSoftlyDeleteAccount() {
        // given
        final var account = repository.save(generateAccountWithFilledFields());
        final var accountId = account.getId();

        // when
        repository.delete(account);

        // then
        assertThat(accountId).isNotNull();
        assertThat(repository.findById(accountId)).isEmpty();
        assertThat(repository.findDeletedIds()).contains(accountId);
    }
}
