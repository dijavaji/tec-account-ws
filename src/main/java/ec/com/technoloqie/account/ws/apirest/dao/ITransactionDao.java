package ec.com.technoloqie.account.ws.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.account.ws.apirest.models.Transaction;

public interface ITransactionDao extends JpaRepository<Transaction, Integer>{

}
