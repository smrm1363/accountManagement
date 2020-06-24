package com.mohammadreza.mirali.accountManagement.domain.account;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountDto;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
This is the Service layer for the Account management
 */
@Service
public class AccountService {
private final AccountRepository accountRepository;
private final AccountTransactionRepository accountTransactionRepository;
  @Autowired
  public AccountService(
      AccountRepository accountRepository,
      AccountTransactionRepository accountTransactionRepository) {
    this.accountRepository = accountRepository;
    this.accountTransactionRepository = accountTransactionRepository;
  }
  /*
  This method is for saving an account
   */
  public Long openAccount(AccountDto accountDto) throws AccountException {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setCustomerId(accountDto.getCustomerId());
    accountEntity.setCurrency(accountDto.getCurrency());
    return Optional.of(accountRepository.save(accountEntity).getAccountNumber()).orElseThrow(()->new AccountException("Problem in opening the account"));
  }
  /*
  This method finds an account vis account Number
  */
  public AccountEntity find(Long accountNo) throws AccountException {
    return accountRepository.findById(accountNo).orElseThrow(()->new AccountException("No account found"));
  }
  /*
  This method is for depositing in an account, it adds the amount of the account
   */
  @Transactional
  public Long deposit(AccountTransactionDto accountTransactionDto) throws AccountException {
    AccountEntity account = find(accountTransactionDto.getAccountNumber());
    account.setBalance(account.getBalance().add(accountTransactionDto.getAmount()));
    accountRepository.save(account);
    AccountTransactionEntity accountTransactionEntity=AccountTransactionEntity.builder().
        account(account).amount(accountTransactionDto.getAmount()).transactionType(TransactionTypeEnum.DEPOSIT).dateTime(LocalDateTime.now()).build();
    return accountTransactionRepository.save(accountTransactionEntity).getId();
  }
  /*
  This method is for withdrawing from an account, it deduct the amount
   */
  @Transactional
  public Long withdrawal(AccountTransactionDto accountTransactionDto) throws AccountException {
    AccountEntity account = find(accountTransactionDto.getAccountNumber());
    BigDecimal amount=account.getBalance().subtract(accountTransactionDto.getAmount());
    if(amount.compareTo(BigDecimal.ZERO)<0)
      throw new AccountException("The balance is not enough");
    account.setBalance(amount);
    accountRepository.save(account);
    AccountTransactionEntity accountTransactionEntity=AccountTransactionEntity.builder().
        account(account).amount(accountTransactionDto.getAmount()).transactionType(TransactionTypeEnum.WITHDRAWAL).dateTime(LocalDateTime.now()).build();
    return accountTransactionRepository.save(accountTransactionEntity).getId();
  }
}
