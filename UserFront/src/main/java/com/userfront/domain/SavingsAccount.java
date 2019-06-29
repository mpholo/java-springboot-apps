package com.userfront.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public @Data class SavingsAccount  extends Account  {

	
	
	@OneToMany(mappedBy="savingsAccount",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonIgnore
	private List<SavingsTransaction> savingsTransactionList;
	
}
