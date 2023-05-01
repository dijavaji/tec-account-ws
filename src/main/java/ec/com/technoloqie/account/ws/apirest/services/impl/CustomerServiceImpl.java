package ec.com.technoloqie.account.ws.apirest.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.account.ws.apirest.dao.ICustomerDao;
import ec.com.technoloqie.account.ws.apirest.dao.IPersonDao;
import ec.com.technoloqie.account.ws.apirest.models.Customer;
import ec.com.technoloqie.account.ws.apirest.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private IPersonDao personDao;

	@Override
	public List<Customer> getListCustomers() {
		return this.customerDao.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		//Person person = this.personDao.save(customer.getPerson());
		return this.customerDao.save(customer);
	}

	@Override
	public Customer getCustomerId(Integer code) {
		return this.customerDao.findById(code).orElse(null);
	}

	@Override
	public Customer updateCustomer(Customer customer, int id) {
		Customer existCustomer = getCustomerId(id); //tenemos que comprobar si con la identificación dada existe en la db o no
		//existAccount.setBalance(account.getBalance());
		//existAccount.setModifiedBy(account.getModifiedBy());
		existCustomer.setModifiedDate(new Date());
		existCustomer.setStatus(Boolean.TRUE);
		this.customerDao.save(existCustomer);
		return existCustomer;
	}

	@Override
	public void deleteCustomer(Integer code) {
		this.customerDao.deleteById(code);
		
	}

}
