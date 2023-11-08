package com.example.gogotrips.shared.mappers;

import com.example.gogotrips.shared.dto.PaymentDTO;
import com.example.gogotrips.shared.dto.TransactionDTO;
import com.example.gogotrips.shared.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transaction mapToTransaction(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Transaction.class);
    }

    public PaymentDTO mapToPaymentDTO(Transaction transaction) {
        return modelMapper.map(transaction, PaymentDTO.class);
    }

    public TransactionDTO mapToTransactionDTO(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    public List<TransactionDTO> mapToTransactionDTOList(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::mapToTransactionDTO)
                .collect(Collectors.toList());
    }
}
