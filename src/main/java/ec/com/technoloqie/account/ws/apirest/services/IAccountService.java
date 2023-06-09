package ec.com.technoloqie.account.ws.apirest.services;

import java.util.List;

import ec.com.technoloqie.account.ws.apirest.models.Account;

public interface IAccountService {
	
	Account createAccount(Account account);
	Account getAccountId(Integer code);
	Account updateAccount(Account account, int id);
	void deleteAccount(Integer code);
	List<Account> getListAccounts();
	Account findOneByAccNumber(Integer code);

}
