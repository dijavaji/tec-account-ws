package ec.com.technoloqie.account.ws.apirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.account.ws.apirest.commons.log.AccountLog;
import ec.com.technoloqie.account.ws.apirest.models.Customer;
import ec.com.technoloqie.account.ws.apirest.services.ICustomerService;

/**
 * 
 * @author root
 *
 */
@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private ICustomerService customerService;
	
	
	@GetMapping("/customers")
	public List<Customer> getListCustomers() {
		return customerService.getListCustomers();
	}
	
	@PostMapping("/customers")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		Customer customerNew = null;
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			customerNew = customerService.createCustomer(customer);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de crear cliente.");
			response.put("mensaje", "Error al momento de crear cliente");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente creado correctamente");
		response.put("cliente", customerNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
		Customer customerActual = customerService.getCustomerId(id);

		Customer customerUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (customerActual == null) {
			AccountLog.getLog().error("Error al momento de actualizar cliente: ".concat(id.toString()) + " no existe.");
			response.put("mensaje", "Error al momento de actualizar cliente: ".concat(id.toString()) + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			customerActual.setPass(customer.getPass());
			customerActual.setPerson(customerActual.getPerson());
			customerActual.getPerson().setFirstName(customer.getPerson().getFirstName());
			customerActual.getPerson().setLastName(customer.getPerson().getLastName());
			customerActual.getPerson().setAddress(customer.getPerson().getAddress());
			customerActual.getPerson().setPhone(customer.getPerson().getPhone());
			customerActual.getPerson().setModifiedBy(customer.getModifiedBy());
			customerActual.getPerson().setModifiedDate(new Date());
			customerActual.setModifiedBy(customer.getModifiedBy());
			customerActual.setModifiedDate(new Date());

			customerUpdated = customerService.createCustomer(customerActual);

		} catch (DataAccessException e) {
			AccountLog.getLog().error("Error al momento de actualizar cliente.");
			response.put("mensaje", "Error al momento de actualizar cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente actualizado correctamente");
		response.put("cliente", customerUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getcustomerId(@PathVariable Integer id) {
		Customer customer = null;
		Map <String, Object> response = new HashMap<>();
		try {
			customer = customerService.getCustomerId(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de consultar cliente.");
			response.put("mensaje", "Error al momento de consultar cliente");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(customer == null) {
			AccountLog.getLog().error("El cliente id: ".concat(id.toString().concat(" no existe")));
			response.put("mensaje", "El cliente id: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		//Customer customer = null;
		Map <String, Object> response = new HashMap<>();
		try {
			customerService.deleteCustomer(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de eliminar cliente.");
			response.put("mensaje", "Error al momento de eliminar cliente");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado correctamente");
		response.put("cliente", id);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
