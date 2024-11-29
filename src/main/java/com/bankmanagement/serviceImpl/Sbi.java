package com.bankmanagement.serviceImpl;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.bankmanagement.model.Account;
import com.bankmanagement.service.Rbi;
import com.dbConfigue.hibernateUtil;
import com.dbConfigue.*;

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Account ac = null;
	Random r = new Random();

	SessionFactory sf = hibernateUtil.getSessionFactory();

	public void createAccount() {
		ac = new Account();

		try {

			Session session = sf.openSession();
			long accNo = 10000000000L + (long) (r.nextDouble() * 90000000000L);
			System.out.println(accNo);
			ac.setAccNo(accNo);

			System.out.println("Enter Full Name  Name : ");
			String name = sc.next() + sc.nextLine();
			ac.setName(name);

			System.out.println("Enter 10 Digit Mobile no : ");
			String mobNo = sc.next();
			ac.setMobNo(mobNo);

			System.out.println("Enter Adhar No :");
			String adharNo = sc.next() + sc.nextLine();
			ac.setAdharNo(adharNo);

			System.out.println("Enter PAN Number :");
			String panNo = sc.next() + sc.nextLine();
			ac.setPanNo(panNo);

			System.out.println("Enter Email ID :");
			String emailId = sc.next() + sc.nextLine();
			ac.setEmailId(emailId);

			System.out.println("Enter Gender : ");
			String gender = sc.next() + sc.nextLine();
			ac.setGender(gender);

			System.out.println("Enter Age : ");
			int age = sc.nextInt();
			ac.setAge(age);

			System.out.println("Enter Initial Balance minimum 1000 :");
			double balance = sc.nextDouble();
			ac.setBalance(balance);

			System.out.println("Create 4 Digit Pin to Access account :");
			int pin = sc.nextInt();
			ac.setPin(pin);

			session.save(ac);
			session.beginTransaction().commit();
			session.close();

			System.out.println("Account Created Successfully");

			System.out.println("Account Details are:");
			System.out.println("----------------------");
			System.out.println("Account No: " + ac.getAccNo());
			System.out.println("Account holders Name: " + ac.getName());
			System.out.println("Mobile No :" + ac.getMobNo());
			System.out.println("Adhar No :" + ac.getAdharNo());
			System.out.println("PAN No : " + ac.getPanNo());
			System.out.println("Email :" + ac.getEmailId());
			System.out.println("Gender : " + ac.getGender());
			System.out.println("Age: " + ac.getAge());
			System.out.println("Initail Balance" + ac.getBalance());
			System.out.println("----------------------");
			System.out.println(" ");
			
			System.out.println("View More options press 0 , To Exit Press 6");

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}

	public void displayAllDetails() {
		try (Session session = sf.openSession()) {
			System.out.println("Enter Account No to View Account Details:");
			long accNo = sc.nextLong();

			Account acc = session.get(Account.class, accNo);

			if (acc != null) {

				System.out.println("Account Details are:");
				System.out.println("----------------------");
				System.out.println("Account No: " + acc.getAccNo());
				System.out.println("Account Holder's Name: " + acc.getName());
				System.out.println("Mobile No: " + acc.getMobNo());
				System.out.println("Aadhar No: " + acc.getAdharNo());
				System.out.println("PAN No: " + acc.getPanNo());
				System.out.println("Email: " + acc.getEmailId());
				System.out.println("Gender: " + acc.getGender());
				System.out.println("Age: " + acc.getAge());
				System.out.println("Available Balance: " + acc.getBalance());
				System.out.println("----------------------");
				System.out.println(" ");
				
				System.out.println("View More options press 0 , To Exit Press 6");
				

			} else {
				System.out.println("Account Not Found!");
			}
			session.close();

			System.out.println("Data retrieved successfully...");
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void depositeMoney() {
		ac = new Account();
		try (Session session = sf.openSession()) {
			System.out.println("Enter Account No to Deposit amount :");
			long accNo = sc.nextLong();
			ac.setAccNo(accNo);

			System.out.println("Enter amount to Deposit :");
			double amount = sc.nextDouble();

			Transaction tx = session.beginTransaction();

			String getBalance = "Select balance from account where accNo= :accNo";

			Double currentBalance = (Double) session.createSQLQuery(getBalance).setParameter("accNo", accNo)
					.uniqueResult();

//			if (currentBalance == null) {
//				System.out.println("Account does not exist!");
//				return;
//			}

			double dpAmount = amount + currentBalance;

			String updateBal = "update account set balance= :dpAmount where accNo= :accNo";

			session.createSQLQuery(updateBal).setParameter("dpAmount", dpAmount).setParameter("accNo", accNo)
					.executeUpdate();

			tx.commit();

			System.out.println("Amount Rs" + amount + " Deposited Successfully..!");
			System.out.println("----------------------");
			System.out.println(" ");
			
			System.out.println("View More options press 0 , To Exit Press 6");

		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void withdrawal() {
		
		ac = new Account();
		try (Session session = sf.openSession()) {
			System.out.println("Enter Account No :");
			long accNo = sc.nextLong();
			ac.setAccNo(accNo);

			System.out.println("Enter amount to withdraw :");
			double amount = sc.nextDouble();

			Transaction tx = session.beginTransaction();

			String getBalance = "Select balance from account where accNo= :accNo";

			Double currentBalance = (Double) session.createSQLQuery(getBalance).setParameter("accNo", accNo)
					.uniqueResult();
//
//			if (currentBalance == null) {
//				System.out.println("Account does not exist!");
//				return;
//			}

			double wdAmount =currentBalance- amount;

			String updateBal = "update account set balance= :wdAmount where accNo= :accNo";

			session.createSQLQuery(updateBal).setParameter("wdAmount", wdAmount).setParameter("accNo", accNo)
					.executeUpdate();

			tx.commit();

			System.out.println("Amount Rs" + amount + "withdraw Successfully..!");
			System.out.println("----------------------");
			System.out.println(" ");
			
			System.out.println("View More options press 0 , To Exit Press 6");

		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		
		

	}

	public void balanceCheck() {

		try (Session session = sf.openSession()) {
			System.out.println("Enter Account No to View Account Details:");
			long accNo = sc.nextLong();

			Account acc = session.get(Account.class, accNo);

			if (acc != null) {

				System.out.println("----------------------");
				System.out.println("Account No: " + acc.getAccNo());
				System.out.println("Available Balance: " + acc.getBalance());
				
				
				System.out.println("----------------------");
				System.out.println(" ");
				
				System.out.println("View More options press 0 , To Exit Press 6");

			} else {
				System.out.println("Account Not Found!");
			}
			session.close();

		
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}



}
