package com.bankmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private long accNo;
	private String name;
	private String mobNo;
	private String adharNo;
	private String panNo;
	private String emailId;
	private String gender;
	private int age;
	private double balance;
	private int pin;

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public long getAccNo() {
		return accNo;
	}

	public String getName() {
		return name;
	}

	public String getMobNo() {
		return mobNo;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public double getBalance() {
		return balance;
	}

	public int getPin() {
		return pin;
	}

}
