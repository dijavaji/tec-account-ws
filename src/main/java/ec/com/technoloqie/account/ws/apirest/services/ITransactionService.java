package ec.com.technoloqie.account.ws.apirest.services;

import java.util.List;

import ec.com.technoloqie.account.ws.apirest.models.Transaction;

public interface ITransactionService {
	
	Transaction createTransaction(Transaction transaction);
	Transaction getTransactionId(Integer code);
	Transaction updateTransaction(Transaction trans, int id);
	void deleteTransaction(Integer code);
	List<Transaction> getListTransaction();
	Transaction payTransaction(Transaction transaction);
	
}
