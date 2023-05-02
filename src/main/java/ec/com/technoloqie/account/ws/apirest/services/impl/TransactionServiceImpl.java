package ec.com.technoloqie.account.ws.apirest.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.account.ws.apirest.commons.AccountConstants;
import ec.com.technoloqie.account.ws.apirest.commons.exception.AccountException;
import ec.com.technoloqie.account.ws.apirest.commons.log.AccountLog;
import ec.com.technoloqie.account.ws.apirest.dao.ITransactionDao;
import ec.com.technoloqie.account.ws.apirest.models.Account;
import ec.com.technoloqie.account.ws.apirest.models.AccountTransaction;
import ec.com.technoloqie.account.ws.apirest.models.Transaction;
import ec.com.technoloqie.account.ws.apirest.models.TransactionType;
import ec.com.technoloqie.account.ws.apirest.services.IAccountService;
import ec.com.technoloqie.account.ws.apirest.services.IAccountTransactionService;
import ec.com.technoloqie.account.ws.apirest.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ITransactionDao transactionDao;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountTransactionService accTranService;

	@Override
	@Transactional
	public Transaction createTransaction(Transaction transaction) {
		return this.transactionDao.save(transaction);
	}

	@Override
	@Transactional(readOnly = true)
	public Transaction getTransactionId(Integer code) {
		return this.transactionDao.findById(code).orElse(null);
	}

	@Override
	@Transactional
	public Transaction updateTransaction(Transaction trans, int id) {
		Transaction existTrans = getTransactionId(id); 
		existTrans.setAmount(trans.getAmount());
		existTrans.setModifiedBy(trans.getModifiedBy());
		existTrans.setModifiedDate(new Date());
		existTrans.setStatus(trans.getStatus());
		this.transactionDao.save(existTrans);
		return existTrans;
	}

	@Override
	public void deleteTransaction(Integer code) {
		this.transactionDao.deleteById(code);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> getListTransaction() {
		return this.transactionDao.findAll();
	}
	
	@Override
	@Transactional
	public Transaction payTransaction(Transaction transaction) throws AccountException{
		Transaction newTransaction = null;
		Transaction operationNew = transaction;
		try {
			Account senderAccount = accountService.findOneByAccNumber(transaction.getAccNumber());
			AccountLog.getLog().info("consulto cuenta"+ senderAccount.getBalance());
			
			if (senderAccount == null) {
				AccountLog.getLog().error("Error al realizar operacion de movimiento la cuenta no existe");
				throw new AccountException("Error al realizar operacion de movimiento la cuenta no existe");
			}
			
			if (senderAccount.getBalance() == 0 && transaction.getAmount() <= 0) {
				AccountLog.getLog().info("Error al realizar operacion de movimiento el saldo es cero");
				throw new AccountException("Saldo no disponible");
			}
			
			TransactionType transactionType = new TransactionType();
			transactionType.setId(AccountConstants.TRANSACTION_TYPE_SIMPLE);
			operationNew.setTransactionType(transactionType);
			newTransaction = this.createTransaction(operationNew);
			
			AccountTransaction accountTrans = new AccountTransaction();
			accountTrans.setTransactionId(newTransaction.getId());
			accountTrans.setAccountId(senderAccount.getId());
			accountTrans.setBalanceInit(senderAccount.getBalance());
			accountTrans.setBalanceAvailable(senderAccount.getBalance() + transaction.getAmount());
			accountTrans.setCreatedBy(transaction.getCreatedBy());
			AccountTransaction accTransaction = this.accTranService.createAccountTransaction(accountTrans);
			
			newTransaction.setBalanceInit(accTransaction.getBalanceInit());
			
			senderAccount.setBalance(accTransaction.getBalanceAvailable());
			senderAccount.setModifiedDate(new Date());
			senderAccount.setModifiedBy(operationNew.getCreatedBy());
			this.accountService.createAccount(senderAccount);
			
		}catch(AccountException e) {
			AccountLog.getLog().error(e.getMessage());
			throw e;
		}catch(Exception e) {
			AccountLog.getLog().error("Error al realizar el movimiento", e);
			throw new AccountException("Error al realizar el movimiento", e);
		}
		
		return newTransaction;
	}

}
