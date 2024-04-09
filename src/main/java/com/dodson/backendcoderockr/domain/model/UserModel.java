package com.dodson.backendcoderockr.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_model")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", columnDefinition = "varchar(25)", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", columnDefinition = "varchar(25)", nullable = false)
	private String lastName;
}
