package com.mohammadreza.mirali.accountManagement.domain.account.model;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountTransactionDto {
  @NotNull
  @DecimalMin("0")
  private BigDecimal amount;
  @NotNull
  private Long accountNumber;
}
