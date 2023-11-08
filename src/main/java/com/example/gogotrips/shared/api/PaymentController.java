package com.example.gogotrips.shared.api;

import com.example.gogotrips.shared.dto.PaymentDTO;
import com.example.gogotrips.shared.dto.TransactionDTO;
import com.example.gogotrips.shared.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Payment", description = "Payment management APIs")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    //@PreAuthorize("hasRole('USER')")
    @PostMapping("/{accountId}")
    public ResponseEntity<PaymentDTO> makePayment(@PathVariable Long accountId, @Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO payment = paymentService.makePayment(accountId, paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @Operation(
            summary = "Retrieve a Payment by accpuntId",
            description = "Get a Payment by account object by specifying its id. The response is Paymenr object with id, accountId, amount, " +
                    " description, date and type transaction .",
            tags = { "payments", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PaymentController.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountAndDateRange(
            @PathVariable Long accountId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @NotBlank(message = "La fecha de inicio es requerida") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") @NotBlank(message = "La fecha de fin es requerida") LocalDate endDate) {

        List<TransactionDTO> transactions = paymentService.getTransactionHistory(accountId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{accountId}/transactions/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByAccount(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = paymentService.getAllTransactionsByAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

}