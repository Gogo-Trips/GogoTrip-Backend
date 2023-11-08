package com.example.gogotrips.shared.service;

import com.example.gogotrips.shared.dto.PaymentDTO;
import com.example.gogotrips.shared.dto.TransactionDTO;
import com.example.gogotrips.shared.exception.InsufficientBalanceException;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import com.example.gogotrips.shared.mappers.TransactionMapper;
import com.example.gogotrips.shared.model.Account;
import com.example.gogotrips.shared.model.Transaction;
import com.example.gogotrips.shared.model.TransactionType;
import com.example.gogotrips.shared.repository.AccountRepository;
import com.example.gogotrips.shared.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final TransactionMapper paymentMapper;

    @Autowired
    public PaymentService(AccountRepository accountRepository, TransactionRepository transactionRepository,
                          TransactionMapper paymentMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    public PaymentDTO makePayment(Long accountId, PaymentDTO paymentDTO) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta bancaria no encontrada"));

        BigDecimal currentBalance = account.getBalance();
        BigDecimal paymentAmount = paymentDTO.getAmount();

        if (currentBalance.compareTo(paymentAmount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente en la cuenta bancaria");
        }

        BigDecimal newBalance = currentBalance.subtract(paymentAmount);
        account.setBalance(newBalance);

        accountRepository.save(account);

        LocalDateTime transactionDate = LocalDateTime.now();

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(paymentAmount)
                .description(paymentDTO.getDescription())
                .type(TransactionType.PAGO)
                .date(transactionDate)
                .build();

        transactionRepository.save(transaction);

        PaymentDTO payment = paymentMapper.mapToPaymentDTO(transaction);

        return payment;
    }

    public List<TransactionDTO> getTransactionHistory(Long accountId, LocalDate startDate, LocalDate endDate) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta bancaria no encontrada"));

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List<Transaction> transactions = transactionRepository.findByAccountAndDateBetween(account, startDateTime, endDateTime);

        List<TransactionDTO> transactionDTOs = paymentMapper.mapToTransactionDTOList(transactions);

        return paymentMapper.mapToTransactionDTOList(transactions);
    }


    public List<TransactionDTO> getAllTransactionsByAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta bancaria no encontrada"));

        List<Transaction> transactions = transactionRepository.getAllTransactionsByAccount(account);
        return paymentMapper.mapToTransactionDTOList(transactions);
    }

}
