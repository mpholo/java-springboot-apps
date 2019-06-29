package com.userfront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 11223145;

	@Autowired
	private PrimaryAccountDao primaryAccountDao;

	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private UserService userService;

	
	@Autowired
	private TransactionService transactionService;
	

	@Override
	public PrimaryAccount createPrimaryAccount() {

		PrimaryAccount account = new PrimaryAccount();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(accountGen());

		primaryAccountDao.save(account);

		return primaryAccountDao.findByAccountNumber(account.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {

		SavingsAccount account = new SavingsAccount();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(accountGen());

		savingsAccountDao.save(account);

		return savingsAccountDao.findByAccountNumber(account.getAccountNumber());
	}
	
	public void deposit(String accountType,double amount,Principal principal) {
		User user= userService.findByUsername(principal.getName());
		
		LocalDateTime date = LocalDateTime.now();
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount=user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			PrimaryTransaction primaryTransaction = 
					new PrimaryTransaction(date,"Deposit to Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.savePrimaryDepositTransaction(primaryTransaction);	
		
		}else if(accountType.equalsIgnoreCase("Savings")){
			SavingsAccount savingsAccount =user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
		    savingsAccountDao.save(savingsAccount);
		    
			SavingsTransaction savingsTransaction = 
					new SavingsTransaction(date,"Deposit to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
		
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		}
		
		
		
		
	}
	
	public 	void withdrawal(String accountType,double amount,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		LocalDateTime dateTime = LocalDateTime.now();
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			PrimaryTransaction primaryTransaction = new 
					PrimaryTransaction(dateTime,"Withdrawal from Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			
		 transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
		} else if (accountType.equalsIgnoreCase("Savings")) {
			
			SavingsAccount savingsAccount =user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
		    savingsAccountDao.save(savingsAccount);
		    
			SavingsTransaction savingsTransaction = 
					new SavingsTransaction(dateTime,"Withdrawal to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
			transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
			
		}
		
		
		
	}
	
	

	private static int accountGen() {
		return ++nextAccountNumber;
	}

}
