package com.bankmangement.client;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bankmanagement.model.Account;
import com.bankmanagement.service.Rbi;
import com.bankmanagement.serviceImpl.Sbi;
import com.dbConfigue.hibernateUtil;

public class App {
	Account acc = null;

	public static boolean checklogin(long accNo, int pin) {

		try {
			SessionFactory sf = hibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			String hql = "SELECT pin FROM Account WHERE accNo = :accNo";

			Integer storedPin = (Integer) session.createQuery(hql).setParameter("accNo", accNo).uniqueResult();

			if (storedPin != null && storedPin == pin) {
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false; // Login failed
	}

	public static void displayAcctInfo(Account acc) {

		if (acc != null) {
			System.out.println("");
			System.out.println("Login Success...!!!");
			System.out.println("Welcome...." + acc.getName());
			System.out.println("");

			System.out.println("------------------------------------------");
			System.out.println("Your Account Details are:");
			System.out.println("Account No: " + acc.getAccNo());
			System.out.println("Account Holder's Name: " + acc.getName());
			System.out.println("Mobile No: " + acc.getMobNo());
			System.out.println("Aadhar No: " + acc.getAdharNo());
			System.out.println("PAN No: " + acc.getPanNo());
			System.out.println("Email: " + acc.getEmailId());
			System.out.println("Gender: " + acc.getGender());
			System.out.println("Age: " + acc.getAge());
			System.out.println("Available Balance: " + acc.getBalance());
			System.out.println();

		} else {
			System.out.println("Account Not Found!");
		}
	}

	public static void displayMenu() {
		System.out.println("-----------Bank Management System ------------");
		System.out.println();
		System.out.println(" Select Your Choice.........!! ");
		System.out.println("Press 1 for Account Creation : ");
		System.out.println("Press 2 for  View All Account Details : ");
		System.out.println("Press 3 for Deposit Amount : ");
		System.out.println("Press 4 for Withdraw Amount : ");
		System.out.println("Press 5 for Check balance : ");
		System.out.println("Press 6 for Exit Appication : ");
		System.out.println();
		System.out.println("---------------Enter your Choice--------------");
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Account Number to Login :");
		long accNo = sc.nextLong();

		System.out.println("Enter 4 Digits Pin :");
		int pin = sc.nextInt();

		Rbi bank = new Sbi();

		if (checklogin(accNo, pin)) {

			System.out.println("Login Success...!!!");
			System.out.println("Welcome..!!");

			try {
				SessionFactory sf = hibernateUtil.getSessionFactory();
				Session session = sf.openSession();

				Account acc = session.get(Account.class, accNo);

				displayAcctInfo(acc);

				session.close();

				boolean flag = true;
				displayMenu();
				while (flag) {

					int choice = sc.nextInt();

					switch (choice) {
					case 0:
						displayMenu();

						break;
					case 1:
						bank.createAccount();
						break;
					case 2:
						bank.displayAllDetails();
						break;
					case 3:
						bank.depositeMoney();
						break;

					case 4:
						bank.withdrawal();
						break;
					case 5:
						bank.balanceCheck();
						break;

					case 6:

						flag = false;
						System.out.println("Thank you..........!!");
						break;

					default:
						System.out.println("Enter Valid Choice:");

					}

				}

			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("kindly Presss number only...!!");
			System.out.println("Press 0 to show Available Options...");

		}

		else
			System.out.println("Login Failed..Invalid details!!");
		System.out.println("Kindly Enter Correct Login details.");
	}

}
