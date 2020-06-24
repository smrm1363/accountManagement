package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.RateDto;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/*
This is an implementation for finding the exchange rate from the 3rd party. It is a Adaptor pattern
 */
@Component
public class EurBasedAdaptorImpl implements MoneyExchangeAdaptor {

  private final String url;
  private final HttpClient client;

  public EurBasedAdaptorImpl(@Value("${MoneyExchange.url}")String url) {
    this.url = url;
    this.client = getClient();
  }
  /*
  It gives the exchanged amount
  */
  @Override
  public BigDecimal exchange(BigDecimal amount, CurrencyEnum sourceCurrency,CurrencyEnum destCurrency)
      throws IOException {
    if(sourceCurrency.equals(destCurrency))
      return amount;
    BigDecimal amountInEur = amount.divide(BigDecimal.valueOf(findRateInEur(sourceCurrency)),BigDecimal.ROUND_HALF_DOWN);
    BigDecimal exchangedAmount = amountInEur.multiply(BigDecimal.valueOf(findRateInEur(destCurrency)));
    return exchangedAmount;
  }
  /*
  It finds the rats based in EUR
   */
  private Double findRateInEur(CurrencyEnum currency) throws IOException {
    if(currency.equals(CurrencyEnum.EUR))
      return 1.0;
    HttpGet request = new HttpGet(url+currency.toString());
    HttpResponse response = client.execute(request);
    ObjectMapper objectMapper=new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    RateDto rateDto = objectMapper.readValue(response.getEntity().getContent(), RateDto.class);
    return rateDto.getRates().get(currency.toString());
  }

  public HttpClient getClient()
  {
    return new DefaultHttpClient();
  }
}
