package ec.com.technoloqie.account.ws.apirest.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.account.ws.apirest.dao.ITransactionDao;
import ec.com.technoloqie.account.ws.apirest.models.Transaction;
import ec.com.technoloqie.account.ws.apirest.services.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ITransactionDao transactionDao;

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

}
