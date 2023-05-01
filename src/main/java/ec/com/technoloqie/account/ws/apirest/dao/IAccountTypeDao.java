package ec.com.technoloqie.account.ws.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.account.ws.apirest.models.AccountType;

public interface IAccountTypeDao extends JpaRepository<AccountType, Integer>{

}
