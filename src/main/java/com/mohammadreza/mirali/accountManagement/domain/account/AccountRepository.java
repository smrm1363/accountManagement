package com.mohammadreza.mirali.accountManagement.domain.account;

import com.mohammadreza.mirali.accountManagement.domain.account.model.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

}
