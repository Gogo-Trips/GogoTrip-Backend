package com.example.gogotrips.shared.repository;


import com.example.gogotrips.shared.model.Account;
import com.example.gogotrips.shared.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUser(User user);
    @Query("SELECT a.balance FROM Account a WHERE a.id = :accountId")
    BigDecimal getAccountBalanceById(Account account);
}
