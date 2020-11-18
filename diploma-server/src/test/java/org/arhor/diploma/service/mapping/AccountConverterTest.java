package org.arhor.diploma.service.mapping;

import org.arhor.diploma.commons.Converter;
import org.arhor.diploma.data.persist.domain.Account;
import org.arhor.diploma.service.dto.AccountDTO;
import org.arhor.diploma.testutils.RandomParameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
@ExtendWith({RandomParameter.Resolver.class})
class AccountConverterTest {

    @TestConfiguration
    @ComponentScan("org.arhor.diploma.service.mapping")
    static class TestConfig {}

    @Autowired
    private Converter<Account, AccountDTO> mapper;

    @Test
    void shouldConvertAllFieldsCorrectly(
            @RandomParameter final String username,
            @RandomParameter final String password,
            @RandomParameter final String firstName,
            @RandomParameter final String lastName,
            @RandomParameter final String email) {

        // given
        var account = new Account();

        account.setUsername(username);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);

        // when
        var dto = mapper.entityToDto(account);

        // then
        assertNotNull(dto);

        assertEquals(account.getId(), dto.getId());
        assertEquals(account.getUsername(), dto.getUsername());
        assertEquals(account.getPassword(), dto.getPassword());
        assertEquals(account.getFirstName(), dto.getFirstName());
        assertEquals(account.getLastName(), dto.getLastName());
        assertEquals(account.getEmail(), dto.getEmail());
    }
}
