package com.example.gogotrips.shared.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private TransactionType type;
}
