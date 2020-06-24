package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class TransferTransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
  private AccountEntity sourceAccount;
  @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
  private AccountEntity destinationAccount;
  private BigDecimal amount;
  private BigDecimal exchangedAmount;
  private LocalDateTime dateTime;
}
