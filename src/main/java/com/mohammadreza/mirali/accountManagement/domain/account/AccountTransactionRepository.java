package com.mohammadreza.mirali.accountManagement.domain.account;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountTransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransactionEntity,Long> {

}
