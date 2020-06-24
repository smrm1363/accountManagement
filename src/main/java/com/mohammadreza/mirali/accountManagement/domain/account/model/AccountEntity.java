package com.mohammadreza.mirali.accountManagement.domain.account.model;

import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class AccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long accountNumber;
  @NotBlank
  private String customerId;
  @NotNull
  private CurrencyEnum currency=CurrencyEnum.EUR;
  @NotNull
  private BigDecimal balance=BigDecimal.ZERO;

}
