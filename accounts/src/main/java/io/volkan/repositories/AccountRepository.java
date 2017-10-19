package io.volkan.repositories;

import io.volkan.entities.Account;

import java.math.BigDecimal;
import java.util.List;

// Account DAO

public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(Long id);

    int getNumberOfAccounts();

    Long createAccount(BigDecimal initialBalance);

    int deleteAccount(Long id);

    void updateAccount(Account account);
}
