package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer;

import com.mohammadreza.mirali.accountManagement.domain.account.AccountException;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionDto;
import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionEntity;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
This is a Rest controller for transferring money
 */
@Api
@RestController
@RequestMapping(path = "/transfer")
public class TransferTransactionRestController {
  private final TransferTransactionService transferTransactionService;
  @Autowired
  public TransferTransactionRestController(
      TransferTransactionService transferTransactionService) {
    this.transferTransactionService = transferTransactionService;
  }
  /*
  It transfers the money from an account to the other account. The rats are based on the account rats
   */
  @PostMapping
  public ResponseEntity<String> transfer(@RequestBody @Validated TransferTransactionDto transferTransaction)
      throws AccountException, IOException {
    return ResponseEntity.ok("{transactionNo : " + transferTransactionService.transfer(transferTransaction) + "}");
  }
  /*
  It returns a list of transfer transactions
   */
  @GetMapping
  public ResponseEntity<List<TransferTransactionEntity>> findAllTransactionsInDates(
      @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
      @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime)
  {
    return ResponseEntity.ok(transferTransactionService.findAllTransactionsInDates(startDateTime,endDateTime));
  }
}
