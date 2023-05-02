package ec.com.technoloqie.account.ws.apirest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.account.ws.apirest.dao.IAccountTransactionDao;
import ec.com.technoloqie.account.ws.apirest.models.AccountTransaction;
import ec.com.technoloqie.account.ws.apirest.services.IAccountTransactionService;

@Service
public class AccountTransactionServiceImpl implements IAccountTransactionService{
	
	@Autowired
	private IAccountTransactionDao accountTransDao;

	@Override
	public AccountTransaction createAccountTransaction(AccountTransaction transAcc) {
		return this.accountTransDao.save(transAcc);
	}

}
