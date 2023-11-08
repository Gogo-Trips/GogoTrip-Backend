package com.example.gogotrips.shared.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;

    @Valid
    private UserDTO user;

    @DecimalMin(value = "0.0", inclusive = false, message = "El saldo debe ser mayor o igual a cero")
    private BigDecimal balance;

    @NotBlank(message = "El número de cuenta no puede estar vacío")
    private String accountNumber;
}
