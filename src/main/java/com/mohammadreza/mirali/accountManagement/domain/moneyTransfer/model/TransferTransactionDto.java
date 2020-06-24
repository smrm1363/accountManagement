package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferTransactionDto {
  @NotNull
  private Long sourceAccountNumber;
  @NotNull
  private Long destinationAccountNumber;
  @NotNull
  private BigDecimal amount;
}
