package io.volkan.repositories;

import io.volkan.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> { // simply putting this line,
                                                                          // we’ve implemented 15-18 methods w/o doing any work whatsoever.

    List<Account> findAccountsByBalanceGreaterThanEqual(BigDecimal amount); // if you name it according to a set of rules,
                                                                            // springdata will generate the method for you.
}
