package ec.com.technoloqie.account.ws.apirest.services;

import java.util.List;

import ec.com.technoloqie.account.ws.apirest.models.Customer;

public interface ICustomerService {
	
	List<Customer> getListCustomers();
	Customer createCustomer(Customer customer);
	Customer getCustomerId(Integer code);
	Customer updateCustomer(Customer customer, int id);
	void deleteCustomer(Integer code);

}
