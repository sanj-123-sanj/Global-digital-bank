package entities.accounts;

import java.time.LocalDate;

import entities.accounts.Account;

/**
 * The Savings class represents a savings account and extends the Account class.
 * <p>
 * It includes additional attributes specific to savings accounts, such as date of birth,
 * gender, and phone number of the account holder.
 * </p>
 */
public class Savings extends Account {


	/**
	 * The date of birth of the account holder.
	 */
	private LocalDate dateOfBirth;

	/**
	 * The gender of the account holder.
	 */
	private String gender;

	/**
	 * The phone number of the account holder.
	 */
	private String phoneNo;

	/**
	 * Retrieves the date of birth of the account holder.
	 *
	 * @return the date of birth.
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the account holder.
	 *
	 * @param dateOfBirth the date of birth to set.
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Retrieves the gender of the account holder.
	 *
	 * @return the gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the account holder.
	 *
	 * @param gender the gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Retrieves the phone number of the account holder.
	 *
	 * @return the phone number.
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the account holder.
	 *
	 * @param phoneNo the phone number to set.
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}


