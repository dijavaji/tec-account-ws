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
import ec.com.technoloqie.account.ws.apirest.models.Transaction;
import ec.com.technoloqie.account.ws.apirest.services.ITransactionService;

/**
 * 
 * @author root
 *
 */
@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
@RequestMapping("/api")
public class TransactionRestController {
	
	@Autowired
	private ITransactionService transService;
	
	
	@GetMapping("/transactions")
	public List<Transaction> getListTransactions() {
		return transService.getListTransaction();
	}
	
	@PostMapping("/transactions")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createTransaction(@Valid @RequestBody Transaction transaction, BindingResult result) {
		Transaction transNew = null;
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			transNew = transService.payTransaction(transaction);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de crear transaccion.");
			response.put("mensaje", "Error al momento de crear transaccion");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Transaccion creado correctamente");
		response.put("transaccion", transNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/transactions/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction, @PathVariable Integer id) {
		Transaction transActual = transService.getTransactionId(id);

		Transaction transUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (transActual == null) {
			AccountLog.getLog().error("Error al momento de actualizar transaccion: ".concat(id.toString()) + " no existe.");
			response.put("mensaje", "Error al momento de actualizar transaccion: ".concat(id.toString()) + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			
			//customerActual.getPerson().setModifiedBy(customer.getModifiedBy());
			//customerActual.getPerson().setModifiedDate(new Date());
			transActual.setModifiedBy(transaction.getModifiedBy());
			transActual.setModifiedDate(new Date());

			transUpdated = transService.createTransaction(transActual);

		} catch (DataAccessException e) {
			AccountLog.getLog().error("Error al momento de actualizar transaccion.");
			response.put("mensaje", "Error al momento de actualizar transaccion");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Transaccion actualizado correctamente");
		response.put("cliente", transUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/transactions/{id}")
	public ResponseEntity<?> getTransactionId(@PathVariable Integer id) {
		Transaction transaction = null;
		Map <String, Object> response = new HashMap<>();
		try {
			transaction = transService.getTransactionId(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de consultar transaccion.");
			response.put("mensaje", "Error al momento de consultar transaccion");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(transaction == null) {
			AccountLog.getLog().error("La transaccion id: ".concat(id.toString().concat(" no existe")));
			response.put("mensaje", "La transaccion id: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	
	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable Integer id) {
		Map <String, Object> response = new HashMap<>();
		try {
			transService.deleteTransaction(id);
		}catch(DataAccessException e) {
			AccountLog.getLog().error("Error al momento de eliminar transaccion.");
			response.put("mensaje", "Error al momento de eliminar transaccion");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Transaction eliminado correctamente");
		response.put("transaccion", id);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
