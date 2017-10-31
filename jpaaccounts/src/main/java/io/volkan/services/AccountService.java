package io.volkan.services;

import io.volkan.entities.Account;
import io.volkan.io.volkan.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountService {
    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BigDecimal getBalance(Long id) {
        return repository.getAccount(id).getBalance();
    }

    public BigDecimal deposit(Long id, BigDecimal amount) {
        Account account = repository.getAccount(id);
        BigDecimal newBalance = account.getBalance().add(amount);

        account.setBalance(newBalance);
        repository.updateAccount(account);

        return newBalance;
    }

    public BigDecimal withdraw(Long id, BigDecimal amount) {
        return deposit(id, amount.negate());
    }

    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }
}
