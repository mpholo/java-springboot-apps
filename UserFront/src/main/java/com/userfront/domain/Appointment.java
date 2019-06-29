package com.userfront.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date date;
	private String location;
	private String description;
	private boolean confirmed;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
