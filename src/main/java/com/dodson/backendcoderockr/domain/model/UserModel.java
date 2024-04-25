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
public final class UserModel {

        /**
         * The table primary key.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        /**
         * The user id.
         */
        @JdbcTypeCode(SqlTypes.CHAR)
        @Column(name = "user_id", nullable = false)
        private UUID userId;

        /**
         * The user first name.
         */
        @Column(name = "first_name", length = 25, nullable = false)
        private String firstName;

        /**
         * The user last name.
         */
        @Column(name = "last_name", length = 25, nullable = false)
        private String lastName;

        /**
         * The user creation date.
         */
        @Column(name = "creation_date")
        private long creationDate;

        /**
         * The collection of user investments.
         */
        @OneToMany(mappedBy = "parent")
        private List<InvestmentModel> investments;

        /**
         * Gets the primary key for the user.
         * @return the primary key for the user.
         */
        public Long getId() {
                return id;
        }

        /**
         * Gets the user UUID.
         * @return the user UUID.
         */
        public UUID getUserId() {
                return userId;
        }

        /**
         * Sets the user id.
         * @param theUserId the user id to set.
         */
        public void setUserId(final UUID theUserId) {
                this.userId = theUserId;
        }

        /**
         * Gets the first name of the user.
         * @return the first name.
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * Sets the first name of the user.
         * @param theFirstName the first name to set.
         */
        public void setFirstName(final String theFirstName) {
                this.firstName = theFirstName;
        }

        /**
         * Gets the last name of the user.
         * @return the last name.
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * Sets the last name of the user.
         * @param theLastName the last name to set.
         */
        public void setLastName(final String theLastName) {
                this.lastName = theLastName;
        }

        /**
         * Gets the creation date of the user.
         * @return the creation date.
         */
        public long getCreationDate() {
                return creationDate;
        }

        /**
         * Sets the creation date of the user.
         * @param theCreationDate the creation date to set.
         */
        public void setCreationDate(final long theCreationDate) {
                this.creationDate = theCreationDate;
        }

        /**
         * Gets the investments of the user.
         * @return the investments.
         */
        public List<InvestmentModel> getInvestments() {
                return investments;
        }

        /**
         * Add a single investment to the collection of investments of the user.
         * @param investment the investment to add.
         */
        public void addInvestment(final InvestmentModel investment) {
                if (investments == null) {
                        investments = new ArrayList<>();
                }
                investments.add(investment);
                investment.setParent(this);
        }

        @Override
        public String toString() {
                return "UserModel [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
                                + lastName + ", creationDate=" + creationDate + ", investments=" + investments + "]";
        }
}
