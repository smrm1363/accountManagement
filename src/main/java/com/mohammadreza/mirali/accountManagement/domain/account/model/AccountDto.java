package com.mohammadreza.mirali.accountManagement.domain.account.model;

import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {
  @NotBlank
  private String customerId;
  private CurrencyEnum currency=CurrencyEnum.EUR;
}
