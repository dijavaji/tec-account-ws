package ec.com.technoloqie.account.ws.apirest.services;

import ec.com.technoloqie.account.ws.apirest.models.AccountTransaction;

public interface IAccountTransactionService {
	
	AccountTransaction createAccountTransaction(AccountTransaction transaction);
}
