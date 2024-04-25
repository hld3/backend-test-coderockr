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
public final class InvestmentModel {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        /**
         * The investment creation date.
         */
        @Column(name = "creation_date", nullable = false)
        private long creationDate;

        /**
         * The investment amount.
        */
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

        public void setCreationDate(final long theCreationDate) {
                this.creationDate = theCreationDate;
        }

        public double getAmount() {
                return amount;
        }

        public void setAmount(final double theAmount) {
                this.amount = theAmount;
        }

        public UserModel getParent() {
                return parent;
        }

        public void setParent(final UserModel theParent) {
                this.parent = theParent;
        }

        @Override
        public String toString() {
                return "InvestmentModel [id=" + id + ", creationDate=" + creationDate + ", amount=" + amount + "]";
        }
}
