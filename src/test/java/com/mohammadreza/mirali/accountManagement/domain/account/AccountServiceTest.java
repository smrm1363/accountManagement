package com.mohammadreza.mirali.accountManagement.domain.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountDto;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionEntity;
import com.mohammadreza.mirali.accountManagement.domain.common.CurrencyEnum;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
@TestInstance(Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
class AccountServiceTest {
  @Mock
  AccountRepository accountRepositoryMock;
  @Mock
  AccountTransactionRepository accountTransactionRepositoryMock;

  AccountService accountService;
  @BeforeAll
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    accountService = new AccountService(accountRepositoryMock,accountTransactionRepositoryMock);
  }
  @Test
  void openAccount() throws AccountException {
    AccountEntity accountEntity = new AccountEntity();
    AccountDto accountDto= new AccountDto();
    accountDto.setCurrency(CurrencyEnum.EUR);
    accountDto.setCustomerId("000111");
    accountEntity.setAccountNumber(1l);
    when(accountRepositoryMock.save(any())).thenReturn(accountEntity);
    Long result = accountService.openAccount(accountDto);
    assertEquals(Optional.of(1l).get(),result);
  }

  @Test
  void find() throws AccountException {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setAccountNumber(1l);
    when(accountRepositoryMock.findById(any())).thenReturn(Optional.of(accountEntity));
    AccountEntity result = accountService.find(1l);
    assertEquals(accountEntity,result);
  }

  @Test
  void findUnHappy() throws AccountException {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setAccountNumber(1l);
    when(accountRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(null));
    AccountException thrown = assertThrows( AccountException.class,
        () -> accountService.find(1l));
  }
  @Test
  void deposit() throws AccountException {
    AccountService accountServiceSpy= Mockito.spy(accountService);
    AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
    AccountEntity accountEntity =new AccountEntity();
    accountEntity.setAccountNumber(1l);
    accountTransactionDto.setAccountNumber(1l);
    accountTransactionDto.setAmount(BigDecimal.TEN);
    when(accountTransactionRepositoryMock.save(any())).thenReturn(AccountTransactionEntity.builder().account(accountEntity).build());
    doReturn(accountEntity).when(accountServiceSpy).find(1l);
    accountServiceSpy.deposit(accountTransactionDto);
  }

  @Test
  void withdrawal() throws AccountException {
    AccountService accountServiceSpy= Mockito.spy(accountService);
    AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
    AccountEntity accountEntity =new AccountEntity();
    accountEntity.setAccountNumber(1l);
    accountTransactionDto.setAccountNumber(1l);
    accountTransactionDto.setAmount(BigDecimal.TEN);
    when(accountTransactionRepositoryMock.save(any())).thenReturn(AccountTransactionEntity.builder().account(accountEntity).build());
    doReturn(accountEntity).when(accountServiceSpy).find(1l);
    assertThrows(AccountException.class,()->accountServiceSpy.withdrawal(accountTransactionDto));
  }
}
