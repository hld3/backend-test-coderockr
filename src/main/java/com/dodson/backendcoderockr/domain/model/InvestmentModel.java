package com.dodson.backendcoderockr.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "investment_model")
public class InvestmentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	private long creationDate;

	@Column(name = "amount", nullable = false)
	private double amount;

	@ManyToOne
	@JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "investment_user_fk"))
	private UserModel parent;

	public Long getId() {
		return id;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public UserModel getParent() {
		return parent;
	}

	public void setParent(UserModel parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "InvestmentModel [id=" + id + ", creationDate=" + creationDate + ", amount=" + amount + "]";
	}
}
