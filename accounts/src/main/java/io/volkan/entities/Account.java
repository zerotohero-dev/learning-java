package io.volkan.entities;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long id;
    private BigDecimal balance;

    public Account() {}

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Account(id:%d, balance:%s)", id, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Account account = (Account) o;

        Boolean idsMatch = id == null ? account.id == null : id.equals(account.id);
        Boolean balancesMatch = balance == null ? account.balance == null : balance.equals(account.balance);

        return idsMatch && balancesMatch;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
