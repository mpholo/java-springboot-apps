package com.userfront.domain;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Data;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public @Data  class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int accountNumber;
	private BigDecimal accountBalance;
}
