package com.corejava.application;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shoppingapp.dao.CustomerDAO;
import com.shoppingapp.dao.CustomerDAOImp;
import com.shoppingapp.dao.ItemDAO;
import com.shoppingapp.dao.ItemDAOImp;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Items;
import com.shoppingapp.utility.ColorsUtility.Colors;

import java.util.InputMismatchException;
import java.util.List;

public class ShoppingApplication {
	
	public static final CustomerDAO customerdao = new CustomerDAOImp();
	public static final ItemDAO itemdao = new ItemDAOImp();
	
	public static void welcome() {
		int choice = 0;
		int choice2 = 0;
		Scanner input = new Scanner(System.in);
		boolean valid = true;
		
		while(valid) {
			System.out.println(Colors.ANSI_BLUE.getColor() + "Standalone Ecommerce App");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "+=======================+");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "1.REGISTER\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "2.LOGIN\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "3.BUY AN ITEM\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "4.REPLACE AN ITEM\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "5.EXIT\t\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "+=======================+");
			System.out.println(Colors.ANSI_GREEN.getColor() + "\nEnter Choice (1,2,3,4, or 5) :" + Colors.ANSI_RESET.getColor());
			
			try{
				choice = input.nextInt();
				switch(choice) {
					case 1:
						register();
						valid = false;
						break;
					case 2:
						login();
						valid = false;
						break;
					case 3:
						System.out.println(Colors.ANSI_RED.getColor() + "You must Register for an Account or Login to Buy an item!!");
						System.out.println("Enter 1=register or 2=login:");
						choice2 = input.nextInt();
						if(choice2 == 1) {
							register();
							valid = false;
							break;
						}else if(choice2 == 2) {
							login();
							valid = false;
							break;
						}
					case 4:
						System.out.println(Colors.ANSI_RED.getColor() + "You must Register for an Account or Login to Replace an item!!");
						System.out.println("Enter 1=register or 2=login:");
						choice2 = input.nextInt();
						if(choice2 == 1) {
							register();
							valid = false;
							break;
						}else if(choice2 == 2) {
							login();
							valid = false;
							break;
						}
					case 5:
						exit();
						valid = false;
						break;
					default:
						System.out.println(Colors.ANSI_RED.getColor() + "Invalid choice!!\n" + Colors.ANSI_RESET.getColor());
						break;
				}
			}catch(InputMismatchException e) {
				input.nextLine();
				System.out.println(Colors.ANSI_RED.getColor() + "Please enter a number!\n");
			}
		}
	}
	
	public static void welcomeAfterLogin(Customer customer) {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		boolean valid = true;
		
		while(valid) {
			System.out.println(Colors.ANSI_BLUE.getColor() + "Standalone Ecommerce App");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "+=======================+");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "1.BUY AN ITEM\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "2.REPLACE AN ITEM\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_CYAN.getColor() + "3.SIGNOUT\t\t" + Colors.ANSI_PURPLE.getColor() + "|");
			System.out.println(Colors.ANSI_PURPLE.getColor() + "+=======================+");
			System.out.println(Colors.ANSI_GREEN.getColor() + "\nEnter Choice (1,2, or 3) :" + Colors.ANSI_RESET.getColor());
			
			try{
				choice = input.nextInt();
				switch(choice) {
					case 1:
						buyItem(customer);
						valid = false;
						break;
					case 2:
						valid = false;
						break;
					case 3:
						signOut();
						break;
					default:
						System.out.println(Colors.ANSI_RED.getColor() + "Invalid choice!!\n" + Colors.ANSI_RESET.getColor());
						break;
				}
			}catch(InputMismatchException e) {
				input.nextLine();
				System.out.println(Colors.ANSI_RED.getColor() + "Please enter a number!\n");
			}
		}
	}
	
	public static void buyItem(Customer customer) {
		int choice = 0;
		int number = 1;
		System.out.println(Colors.ANSI_BLUE.getColor() + "\nStandalone Ecommerce App");
		System.out.println(Colors.ANSI_PURPLE.getColor() + "+=======================+");
		System.out.println("|" + Colors.ANSI_CYAN.getColor() + "\tItems\t  Item Code  Price" + Colors.ANSI_PURPLE.getColor() + "|");
		List<Items> items = itemdao.getAllItems();
		for(Items i : items) {
			System.out.println(Colors.ANSI_PURPLE.getColor() + "|" + Colors.ANSI_YELLOW.getColor() + number + "." + i.getName() + "    " + i.getId() + "     $" + i.getPrice() + Colors.ANSI_PURPLE.getColor() + "|");
			number += 1;
		}
		System.out.println("+=======================+");
		
		System.out.println();
	}
	
	public static boolean verifyPattern(Pattern p, String s) {
		Matcher matcher = p.matcher(s);
		if(matcher.matches() == true) {
			return true;
		}
		return false;
	}
	
	public static void register() {
		String name;
		String address;
		String phone_number;
		String username;
		String password;
		int balance = 0;
		int choice;
		boolean valid = true;
		Scanner input = new Scanner(System.in);
		
		Pattern namePattern = Pattern.compile("^(([a-z]+|[a-zA-Z]+))|([a-zA-Z]+\\s{1}[a-zA-Z]+)$");
		Pattern phonePattern = Pattern.compile("^.?\\d{3}.?(\\s{1}|.)?\\d{3}.?\\d{4}$");
		Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[@#$%!^&])(?=.*[A-Z]).{8}");
		
		while(valid) {
			System.out.println(Colors.ANSI_BLUE.getColor() + "\n+-----------------------------------+\n| Please Complete Registration Form |\n+-----------------------------------+" + Colors.ANSI_RESET.getColor());
			
			try {
				System.out.println(Colors.ANSI_GREEN.getColor() + "Enter Name:");
				System.out.print(Colors.ANSI_CYAN.getColor());
				name = input.nextLine();
				if(verifyPattern(namePattern, name) == false) {
					throw new Exception();
				}
				System.out.println(Colors.ANSI_GREEN.getColor() + "Enter Address:");
				System.out.print(Colors.ANSI_CYAN.getColor());
				address = input.nextLine();
				System.out.println(Colors.ANSI_GREEN.getColor() + "Enter Phone Number:");
				System.out.print(Colors.ANSI_CYAN.getColor());
				phone_number = input.nextLine();
				if(verifyPattern(phonePattern, phone_number) == false) {
					throw new Exception();
				}
				System.out.println(Colors.ANSI_GREEN.getColor() + "Create Username:");
				System.out.print(Colors.ANSI_CYAN.getColor());
				username = input.nextLine();
				Customer usernameCheck = customerdao.getCustomerByUsername(username);
				if(usernameCheck == null) {
					System.out.println(Colors.ANSI_GREEN.getColor() + "Password: 8 Characters With Lower, Upper & Special");
					System.out.print(Colors.ANSI_CYAN.getColor());
					password = input.nextLine();
					if(verifyPattern(passwordPattern, password)) {
						Customer passwordCheck = customerdao.getCustomerByPassword(password);
						if(passwordCheck == null) {
							System.out.println(Colors.ANSI_GREEN.getColor() + "Do you wish to add money to your account? 1=Yes, 2=No");
							System.out.print(Colors.ANSI_CYAN.getColor());
							choice = input.nextInt();
							switch(choice) {
							case 1:
								System.out.println(Colors.ANSI_GREEN.getColor() + "How much do you wish to add?");
								System.out.print(Colors.ANSI_CYAN.getColor());
								balance = input.nextInt();
								if(balance > 0) {
									Customer customer = new Customer(username, name, password, address, phone_number, balance);
									customerdao.addCustomer(customer);
									valid = false;
									System.out.println(Colors.ANSI_GREEN.getColor() + "Successfully Completed Registration!!");
									login();
									break;
								}else {
									throw new Exception();
								}
							case 2:
								Customer customer2 = new Customer(username, name, password, address, phone_number, balance);
								customerdao.addCustomer(customer2);
								valid = false;
								System.out.println(Colors.ANSI_GREEN.getColor() + "Successfully Completed Registration!!");
								login();
								break;
							default:
								System.out.println(Colors.ANSI_RED.getColor() + "Please enter a Valid option!!");
								break;
							}
						}else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				}else {
					throw new Exception();
				}
			}catch(Exception e) {
				System.out.println(Colors.ANSI_RED.getColor() + "Please enter a number!\n");
			}
		}
	}
	
	public static boolean checkLoginSuccess(int num) {
		if(num == 3) {
			System.out.println(Colors.ANSI_RED.getColor() + "Too many unsuccessful logins!!");
			System.out.println(Colors.ANSI_RED.getColor() + "Please Register for an account or try logging in");
			register();
			return false;
		}
		return true;
	}
	
	public static void exit() {
		System.out.println(Colors.ANSI_PURPLE.getColor() + "Thank you for using The Shopping App!! Goodbye!" + Colors.ANSI_RESET.getColor());
	}
	
	public static void signOut() {
		System.out.println(Colors.ANSI_GREEN.getColor() + "Successfully Logged Out!!\n" + Colors.ANSI_RESET.getColor());
		welcome();
	}
	
	public static void login() {
		String username;
		String password;
		boolean valid = true;
		int counter = 0;
		
		Scanner input = new Scanner(System.in);
		
		while(valid) {
			System.out.println(Colors.ANSI_BLUE.getColor() + "\n+---------------------+\n| Enter Login Details |\n+---------------------+" + Colors.ANSI_RESET.getColor());
			System.out.println(Colors.ANSI_GREEN.getColor() + "Username:");
			try {
				System.out.print(Colors.ANSI_CYAN.getColor());
				username = input.nextLine();
				Customer found = customerdao.getCustomerByUsername(username);
				if(found != null) {
					System.out.println(Colors.ANSI_GREEN.getColor() + "Password: 8 Characters With Lower, Upper & Special");
					System.out.print(Colors.ANSI_CYAN.getColor());
					password = input.nextLine();
					if(password.equals(found.getPassword())) {
						System.out.println(Colors.ANSI_GREEN.getColor() + "Login Successful!!\n" + Colors.ANSI_RESET.getColor());
						valid = false;
						welcomeAfterLogin(found);
					}else {
						counter += 1;
						throw new Exception();
					}
				}else {
					counter += 1;
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println(Colors.ANSI_RED.getColor() + "No user found!!");
				valid = checkLoginSuccess(counter);
			}
		}
	}

	public static void main(String[] args) {
		welcome();
	}
}
