package com.dodson.backendcoderockr.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_model")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JdbcTypeCode(SqlTypes.CHAR)
	@Column(name = "user_id", nullable = false)
	private UUID userId;

	@Column(name = "first_name", length = 25, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 25, nullable = false)
	private String lastName;

	@Column(name = "creation_date")
	private long creationDate;

	@OneToMany(mappedBy = "parent")
	private List<InvestmentModel> investments;

	public Long getId() {
		return id;
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public List<InvestmentModel> getInvestments() {
		return investments;
	}

	public void addInvestment(InvestmentModel investment) {
		if (investments == null) {
			investments = new ArrayList<>();
		}
		investments.add(investment);
		investment.setParent(this);
	}
}
