package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer;

import com.mohammadreza.mirali.accountManagement.domain.account.AccountException;
import com.mohammadreza.mirali.accountManagement.domain.account.AccountService;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange.EurBasedAdaptorImpl;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.MoneyExchange.MoneyExchangeAdaptor;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionEntity;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
This is the service component for transferring money
 */
@Service
public class TransferTransactionService {
  private final TransferTransactionRepository transferTransactionRepository;
  private final MoneyExchangeAdaptor moneyExchangeAdaptor;
  private final AccountService accountService;
  @Autowired
  public TransferTransactionService(
      TransferTransactionRepository transferTransactionRepository,
      EurBasedAdaptorImpl eurBasedAdaptor,
      AccountService accountService) {
    this.transferTransactionRepository = transferTransactionRepository;
    this.moneyExchangeAdaptor = eurBasedAdaptor;
    this.accountService = accountService;
  }
  /*
  It transfers money
   */
  @Transactional
  public Long transfer(TransferTransactionDto transferTransactionDto)
      throws AccountException, IOException {
    AccountEntity sourceAccount = accountService.find(transferTransactionDto.getSourceAccountNumber());
    AccountEntity destinationAccount = accountService.find(transferTransactionDto.getDestinationAccountNumber());
    BigDecimal exchangedAmount = moneyExchangeAdaptor.exchange(transferTransactionDto.getAmount(),sourceAccount.getCurrency(),destinationAccount.getCurrency());
    AccountTransactionDto sourceAccountTransaction = new AccountTransactionDto();
    sourceAccountTransaction.setAccountNumber(sourceAccount.getAccountNumber());
    sourceAccountTransaction.setAmount(transferTransactionDto.getAmount());
    accountService.withdrawal(sourceAccountTransaction);
    AccountTransactionDto destinationAccountTransaction = new AccountTransactionDto();
    destinationAccountTransaction.setAccountNumber(destinationAccount.getAccountNumber());
    destinationAccountTransaction.setAmount(exchangedAmount);
    accountService.deposit(destinationAccountTransaction);
    TransferTransactionEntity transferTransactionEntity = TransferTransactionEntity.builder()
    .amount(transferTransactionDto.getAmount()).exchangedAmount(exchangedAmount).dateTime(LocalDateTime.now())
        .sourceAccount(sourceAccount).destinationAccount(destinationAccount).build();
    return transferTransactionRepository.save(transferTransactionEntity).getId();
  }
  public List<TransferTransactionEntity> findAllTransactionsInDates(LocalDateTime startDateTime,LocalDateTime endDateTime)
  {
    return transferTransactionRepository.findAllByDateTimeBetween(startDateTime,endDateTime);
  }
}
