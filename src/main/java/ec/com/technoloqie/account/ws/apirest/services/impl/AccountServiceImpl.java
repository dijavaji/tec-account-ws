package ec.com.technoloqie.account.ws.apirest.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.account.ws.apirest.dao.IAccountDao;
import ec.com.technoloqie.account.ws.apirest.models.Account;
import ec.com.technoloqie.account.ws.apirest.services.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private IAccountDao accountDao;

	@Override
	@Transactional
	public Account createAccount(Account account) {
		return this.accountDao.save(account);
	}

	@Override
	@Transactional(readOnly = true)
	public Account getAccountId(Integer code) {
		return this.accountDao.findById(code).orElse(null);
	}

	@Override
	@Transactional
	public Account updateAccount(Account account, int id) {
		Account existAccount = getAccountId(id); //tenemos que comprobar si con la identificación dada existe en la db o no
		existAccount.setBalance(account.getBalance());
		existAccount.setModifiedBy(account.getModifiedBy());
		existAccount.setModifiedDate(new Date());
		existAccount.setStatus(null);
		this.accountDao.save(existAccount);
		return existAccount;
	}

	@Override
	public void deleteAccount(Integer code) {
		this.accountDao.deleteById(code);
	}
	
}
