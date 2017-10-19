package io.volkan.repositories;

import io.volkan.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Services do the legwork.

// Service is a class that contains business logic and
// it is a boundary for transactions.

// Repository is just your data access layer.

@Repository
@Profile("test")
public class JdbcAccountRepository implements AccountRepository {
    private JdbcTemplate template;
    private static long nextId = 4;

    private class AccountMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Account(rs.getLong("id"), rs.getBigDecimal("balance"));
        }
    }

    @Autowired
    public JdbcAccountRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Account> getAccounts() {
        return template.query("SELECT * FROM account", new AccountMapper());
    }

    @Override
    public Account getAccount(Long id) {
        return template.queryForObject("SELECT * from account WHERE id=?", new AccountMapper(), id);
    }

    @Override
    public int getNumberOfAccounts() {
        return template.queryForObject("SELECT COUNT(*) FROM account", Integer.class);
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        long id = nextId++;

        template.update("INSERT INTO account(id, balance) VALUES(?, ?)", id, initialBalance);

        return id;
    }

    @Override
    public int deleteAccount(Long id) {
        return template.update("DELETE FROM account where id = ?", id);
    }

    @Override
    public void updateAccount(Account account) {
        template.update("UPDATE account SET balance = ? WHERE id = ?", account.getBalance(), account.getId());
    }
}
