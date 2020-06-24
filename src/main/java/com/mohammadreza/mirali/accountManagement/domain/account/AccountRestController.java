package com.mohammadreza.mirali.accountManagement.domain.account;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountDto;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
This is a Rest controller for account management
 */
@Api
@RestController
@RequestMapping(path = "/account")
public class AccountRestController {
  private final AccountService accountService;
  @Autowired
  public AccountRestController(
      AccountService accountService) {
    this.accountService = accountService;
  }
  /*
  Finds a account information
   */
  @GetMapping("{accountNo}")
  public ResponseEntity<AccountEntity> findAccount(@PathVariable("accountNo") String id)  {
    try {
      return ResponseEntity.ok(accountService.find(Long.valueOf(id)));
    } catch (AccountException e) {
      e.printStackTrace();
      return new ResponseEntity<>(null,null, HttpStatus.NOT_FOUND);
    }
  }
  /*
  Opens an account
  */
  @PostMapping
  public ResponseEntity<String> openAccount(@RequestBody @Validated AccountDto account)
      throws AccountException {
    return ResponseEntity.ok("{accountNumber : "+accountService.openAccount(account)+"}");
  }
  /*
  Deposits money to an account
   */
  @PostMapping(path = "/deposit")
  public ResponseEntity<String> deposit(@RequestBody @Validated AccountTransactionDto accountTransaction)
  {
    try {
      return ResponseEntity.ok("{transactionNo : "+accountService.deposit(accountTransaction)+"}");
    } catch (AccountException e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(),null,HttpStatus.EXPECTATION_FAILED);
    }
  }
  /*
  Withdraws money from an account
   */
  @PostMapping(path = "/withdrawal")
  public ResponseEntity<String> withdrawal(@RequestBody @Validated AccountTransactionDto accountTransaction)
  {
    try {
      return ResponseEntity.ok("{transactionNo : "+accountService.withdrawal(accountTransaction)+"}");
    } catch (AccountException e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(),null,HttpStatus.EXPECTATION_FAILED);
    }
  }
}
