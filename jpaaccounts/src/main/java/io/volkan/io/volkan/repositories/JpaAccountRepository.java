package io.volkan.io.volkan.repositories;

import io.volkan.entities.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static Long nextId = 0L;

    @Override
    public List<Account> getAccounts() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Account getAccount(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public int getNumberOfAccounts() {
        return ((Long) entityManager.createQuery("SELECT COUNT(a.id) FROM Account a").getSingleResult()).intValue();
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        Long id = nextId++;

        entityManager.persist(new Account(id, initialBalance));

        return id;
    }

    @Override
    public int deleteAccount(Long id) {
        entityManager.remove(getAccount(id));

        return 1;
    }

    @Override
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }
}
