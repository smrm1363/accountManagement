package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer;

import com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model.TransferTransactionEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferTransactionRepository extends CrudRepository<TransferTransactionEntity,Long> {

  List<TransferTransactionEntity> findAllByDateTimeBetween(LocalDateTime startDateTime,LocalDateTime endDateTime);
}
