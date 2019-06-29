package com.userfront.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model,Principal principal) {
		List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(principal.getName());
		User user= userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount=user.getPrimaryAccount();
		
		model.addAttribute("primaryAccount",primaryAccount);
		model.addAttribute("primaryTransactionList",primaryTransactionList);
		return "primaryAccount";
	}
	
	@RequestMapping("/savingsAccount")
	public String savingsAccount(Model model,Principal principal) {
		List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName()); 
		User user= userService.findByUsername(principal.getName());
		SavingsAccount savingsAccount=user.getSavingsAccount();
		
		model.addAttribute("savingsAccount",savingsAccount);
		model.addAttribute("savingsTransactionList",savingsTransactionList);
		
		
		return "savingsAccount";
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.GET)
	public String deposit(Model model) {
	 
		model.addAttribute("accountType","");
		model.addAttribute("amount","");
		return "deposit";
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.POST)
	public String depositPost(@RequestParam("amount") String amount,
			                 @RequestParam("accountType") String accountType,
			                 Model model,Principal principal) {
	accountService.deposit(accountType,Double.parseDouble(amount),principal);
		return "redirect:/userFront";
	}
	
	@RequestMapping(value="/withdraw",method=RequestMethod.GET)
	public String withdrawal(Model model) {
		model.addAttribute("accountType","");
		model.addAttribute("amount", "");
		return "withdraw";
	}
	
	@RequestMapping(value="withdraw",method=RequestMethod.POST)
	public String withdrawalPost(@RequestParam("accountType") String accountType,
			                     @RequestParam("amount") String amount,Principal principal) {
		
		accountService.withdrawal(accountType,Double.parseDouble(amount),principal);
		return "redirect:/userFront";
	}

}
