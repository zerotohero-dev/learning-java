package io.volkan.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity // <- This class will map to a database table.
public class Account {

    @Id // <- Primary key.
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
}
