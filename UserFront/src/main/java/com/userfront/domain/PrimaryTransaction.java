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
public @Data class PrimaryTransaction {

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
	@JoinColumn(name="primary_account_id")
	private PrimaryAccount primaryAccount;
	
	public PrimaryTransaction(LocalDateTime date, String description, String type, String status, double amount,
			BigDecimal availableBalance, PrimaryAccount primaryAccount) {
		super();
		this.date = date;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.primaryAccount = primaryAccount;
	}


	public PrimaryTransaction() {
		super();
	}
	
	
	

	
}
