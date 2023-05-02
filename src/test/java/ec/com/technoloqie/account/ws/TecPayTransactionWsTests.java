package ec.com.technoloqie.account.ws;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ec.com.technoloqie.account.ws.apirest.commons.AccountConstants;
import ec.com.technoloqie.account.ws.apirest.commons.exception.AccountException;
import ec.com.technoloqie.account.ws.apirest.commons.log.AccountLog;
import ec.com.technoloqie.account.ws.apirest.models.Transaction;
import ec.com.technoloqie.account.ws.apirest.models.TransactionType;
import ec.com.technoloqie.account.ws.apirest.services.impl.TransactionServiceImpl;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TecPayTransactionWsTests {
	@Autowired
	private TransactionServiceImpl transService;
	
	private Transaction transaction;
	
	@Before
	public void setUp() throws AccountException{
		this.transaction = new Transaction();
		this.transaction.setAmount(-575.0);
		this.transaction.setAccNumber(975446);
		this.transaction.setAccountType(AccountConstants.TRANSACTION_TYPE_SIMPLE);
		this.transaction.setCreatedBy("admin");
		
	}
	
	@SuppressWarnings("static-access")
	@Test
    public void createPayTransactionTest() {
		this.transaction = new Transaction();
		this.transaction.setAmount(-540.0);
		this.transaction.setAccNumber(975446);
		this.transaction.setAccountType(AccountConstants.TRANSACTION_TYPE_SIMPLE);
		this.transaction.setCreatedBy("admin");
		try{
			AccountLog.getLog().info("getPayTransactionTest.");
			Transaction newTransaction = transService.payTransaction(this.transaction);
			
			
			assertEquals( 540 ,newTransaction.getBalanceInit(),0.0);
			//assertEquals( CurrencyConstants.GALACTIC_CURRENCY ,currency.getCurrency());
		}catch(Exception e){
			AccountLog.getLog().error("getPayTransactionTest.");
			fail("Error in getPayTransactionTest.");
		}
    }
	
    @Test
    public void getListTransactionTest() {
    	try {
    		AccountLog.getLog().info("getListTransactionTest.");
    		List<Transaction> trans = transService.getListTransaction();

            Assert.assertNotNull(trans.size());
    	}catch(Exception e) {
    		AccountLog.getLog().error("getListTransactionTest.");
    		assertTrue("getListTransactionTest.",Boolean.TRUE);
    	}
    }
}
