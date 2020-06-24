package com.mohammadreza.mirali.accountManagement.domain.account.model;

import com.mohammadreza.mirali.accountManagement.domain.account.TransactionTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
public class AccountTransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  private LocalDateTime dateTime;
  @NotNull
  private TransactionTypeEnum transactionType;
  @DecimalMin("0")
  private BigDecimal amount=BigDecimal.ZERO;
  @NotNull
  @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
  private AccountEntity account;
}
