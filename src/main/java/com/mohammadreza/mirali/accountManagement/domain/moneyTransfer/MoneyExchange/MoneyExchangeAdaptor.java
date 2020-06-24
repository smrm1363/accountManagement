package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange;

import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import java.io.IOException;
import java.math.BigDecimal;
/*
This is an interface for finding the exchange rate from the 3rd party. It is a Adaptor pattern
 */
public interface MoneyExchangeAdaptor {
  BigDecimal exchange(BigDecimal amount, CurrencyEnum sourceCurrency,CurrencyEnum destCurrency)
      throws IOException;
}
