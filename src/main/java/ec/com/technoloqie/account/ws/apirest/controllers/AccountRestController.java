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
import ec.com.technoloqie.account.ws.apirest.models.Account;
import ec.com.technoloqie.account.ws.apirest.services.IAccountService;

@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	@Autowired
	private IAccountService accountService;
	
	
	@GetMapping("/accounts")
	public List<Account> getListAccounts() {
		return accountService.getListAccounts();
	}
	
	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, BindingResult result) {
		Account accountNew = null;
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			accountNew = accountService.createAccount(account);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de crear cuenta.");
			response.put("mensaje", "Error al momento de crear cuenta");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cuenta creado correctamente");
		response.put("cuenta", accountNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/accounts/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable Integer id) {
		Account accountActual = accountService.getAccountId(id);

		Account accountUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (accountActual == null) {
			AccountLog.getLog().error("Error al momento de actualizar cuenta: ".concat(id.toString()) + " no existe.");
			response.put("mensaje", "Error al momento de actualizar cuenta: ".concat(id.toString()) + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			accountActual.setAccNumber(account.getAccNumber());
			accountActual.setBalance(account.getBalance());
			accountActual.setStatus(account.getStatus());
			//accountActual.getCustomer().setId(account.getCustomer().getId());
			accountActual.getAccountType().setId(account.getAccountType().getId());
			accountActual.setModifiedBy(account.getModifiedBy());
			accountActual.setModifiedDate(new Date());

			accountUpdated = accountService.createAccount(accountActual);

		} catch (DataAccessException e) {
			AccountLog.getLog().error("Error al momento de actualizar cliente.");
			response.put("mensaje", "Error al momento de actualizar cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cuenta actualizado correctamente");
		response.put("cliente", accountUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<?> getAccountId(@PathVariable Integer id) {
		Account account = null;
		Map <String, Object> response = new HashMap<>();
		try {
			account = accountService.getAccountId(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de consultar cuenta.");
			response.put("mensaje", "Error al momento de consultar cuenta");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(account == null) {
			AccountLog.getLog().error("La cuenta id: ".concat(id.toString().concat(" no existe")));
			response.put("mensaje", "La cuenta id: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> deleteAcount(@PathVariable Integer id) {
		Map <String, Object> response = new HashMap<>();
		try {
			accountService.deleteAccount(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de eliminar cuenta.");
			response.put("mensaje", "Error al momento de eliminar cuenta");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cuenta eliminado correctamente");
		response.put("cuenta", id);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
