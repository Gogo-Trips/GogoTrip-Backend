package com.example.gogotrips.shared.mappers;

import com.example.gogotrips.shared.dto.AccountDTO;
import com.example.gogotrips.shared.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account mapToModel(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    public AccountDTO mapToDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }
}