package com.userfront.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class SavingsTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private LocalDateTime date;
	private String description;
	private String type;
	private String status;
	private double amount;
	private BigDecimal availableBalance;
	
	@ManyToOne
	@JoinColumn(name="saving_account_id")
	private SavingsAccount savingsAccount;
	
	public SavingsTransaction(LocalDateTime date, String description, String type, String status, double amount,
			BigDecimal availableBalance, SavingsAccount savingsAccount) {
		super();
		this.date = date;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.savingsAccount = savingsAccount;
	}


	public SavingsTransaction() {
		super();
	}
	
	
	

	
}
