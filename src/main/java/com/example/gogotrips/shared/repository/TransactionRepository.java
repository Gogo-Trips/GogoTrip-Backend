package com.example.gogotrips.shared.repository;


import com.example.gogotrips.shared.model.Account;
import com.example.gogotrips.shared.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account.accountId = :accountId ORDER BY t.date DESC")
    List<Transaction> findAllByAccountOrderByDateDesc(@Param("accountId") Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.account = :account AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findByAccountAndDateBetween(Account account, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT t FROM Transaction t WHERE t.account = :account")
    List<Transaction> getAllTransactionsByAccount(Account account);

}