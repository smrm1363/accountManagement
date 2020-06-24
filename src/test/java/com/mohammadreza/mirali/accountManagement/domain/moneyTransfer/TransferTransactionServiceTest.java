package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mohammadreza.mirali.accountManagement.domain.account.AccountException;
import com.mohammadreza.mirali.accountManagement.domain.account.AccountService;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange.EurBasedAdaptorImpl;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionEntity;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@TestInstance(Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
class TransferTransactionServiceTest {
  @Mock
  TransferTransactionRepository transferTransactionRepositoryMock;
  @Mock
  EurBasedAdaptorImpl eurBasedAdaptorMock;
  @Mock
  AccountService accountServiceMock;
  TransferTransactionService transferTransactionService;
  @BeforeAll
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    transferTransactionService = new TransferTransactionService(transferTransactionRepositoryMock,eurBasedAdaptorMock,accountServiceMock);
  }
  @Test
  void transfer() throws AccountException, IOException {
    TransferTransactionEntity transferTransactionEntity = TransferTransactionEntity.builder().id(1l).amount(
        BigDecimal.valueOf(1000)).build();
    AccountEntity accountEntitySource = new AccountEntity();
    accountEntitySource.setCurrency(CurrencyEnum.EUR);
    accountEntitySource.setBalance(BigDecimal.TEN);
    AccountEntity destEntitySource = new AccountEntity();
    accountEntitySource.setCurrency(CurrencyEnum.USD);
    accountEntitySource.setBalance(BigDecimal.ZERO);
    when(accountServiceMock.find(any())).thenReturn(accountEntitySource);
    when(eurBasedAdaptorMock.exchange(any(),any(),any())).thenReturn(BigDecimal.valueOf(9));
    when(transferTransactionRepositoryMock.save(any())).thenReturn(transferTransactionEntity);
    TransferTransactionDto transferTransactionDto = new TransferTransactionDto();
    transferTransactionDto.setAmount(BigDecimal.valueOf(1000));
    assertEquals(java.util.Optional.of(1l).get(),transferTransactionService.transfer(transferTransactionDto));
  }

  @Test
  void findAllTransactionsInDates() {
    List<TransferTransactionEntity> transferTransactionEntities = new ArrayList<>();
    transferTransactionEntities.add(new TransferTransactionEntity());
    when(transferTransactionRepositoryMock.findAllByDateTimeBetween(any(),any())).thenReturn(transferTransactionEntities);
    assertEquals(1,transferTransactionService.findAllTransactionsInDates(LocalDateTime.now(),LocalDateTime.now()).size());
  }
}
