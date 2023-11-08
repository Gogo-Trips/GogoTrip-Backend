package com.example.gogotrips.shared.service;

import com.example.gogotrips.shared.dto.AccountDTO;
import com.example.gogotrips.shared.exception.AccountRegistrationException;
import com.example.gogotrips.shared.mappers.AccountMapper;
import com.example.gogotrips.shared.mappers.UserMapper;
import com.example.gogotrips.shared.model.Account;
import com.example.gogotrips.shared.model.User;
import com.example.gogotrips.shared.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserMapper userMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper,UserMapper userMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.userMapper=userMapper;
    }

    @Transactional
    public AccountDTO registerAccount(AccountDTO accountDTO) {
        User user = userMapper.mapToModel(accountDTO.getUser());
        if (accountRepository.existsByUser(user)) {
            throw new AccountRegistrationException("Ya existe una cuenta registrada para este usuario");
        }

        Account account = accountMapper.mapToModel(accountDTO);
        account.setBalance(BigDecimal.ZERO);
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setUser(user);

        Account savedAccount = accountRepository.save(account);

        return accountMapper.mapToDTO(savedAccount);
    }
}
