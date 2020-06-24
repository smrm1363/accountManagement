package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@TestInstance(Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
class EurBasedAdaptorImplTest {
  EurBasedAdaptorImpl eurBasedAdaptor;
  @BeforeAll
  public void setUp()
  {
    eurBasedAdaptor = new EurBasedAdaptorImpl("https://api.exchangeratesapi.io/latest?symbols=");
  }
  @Test
  void exchange() throws IOException {
    HttpResponse httpResponse = mock(HttpResponse.class);
    HttpEntity httpEntity = mock(HttpEntity.class);
    HttpClient httpClient = mock(HttpClient.class);
    EurBasedAdaptorImpl eurBasedAdaptorSpy= Mockito.spy(eurBasedAdaptor);
    InputStream inputStream = new ByteArrayInputStream("{\"rates\":{\"USD\":0.90328},\"base\":\"EUR\",\"date\":\"2020-06-22\"}".getBytes());
    when(httpResponse.getEntity()).thenReturn(httpEntity);
    when(httpEntity.getContent()).thenReturn(inputStream);
    doReturn(httpClient).when(eurBasedAdaptorSpy).getClient();
    when(httpClient.execute(any())).thenReturn(httpResponse);
    BigDecimal result =eurBasedAdaptor.exchange(BigDecimal.TEN, CurrencyEnum.EUR,CurrencyEnum.USD);
    assertEquals("11",result.toString().substring(0,2));
  }
}
