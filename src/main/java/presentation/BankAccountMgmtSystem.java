package presentation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exception.NoBankAccountException;
import exception.SystemException;
import model.BankAccountPOJO;
import model.UserPOJO;
import service.MainService;
import service.MainServiceImpl;

public class BankAccountMgmtSystem {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		MainService mainServ = new MainServiceImpl();
		final String LINE = "--------------------------------------------------";
		final String EXIT = "Thank you for using Bank of OM!";
		
		int option = 0;
		
		System.out.println(LINE);
		System.out.println("Welcome to Bank of OM!");
		
		boolean loggedIn = false;
		boolean failedValidation = false;
		int userID = 0;
		UserPOJO user = null;
		List<BankAccountPOJO> userBankAccounts = null; 
		List<UserPOJO> bankAccountUsers = null;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		while(option != -1) {
			System.out.println(LINE);
			
			//menu differs on login
			if(!loggedIn) {
				//do not print menu after inputting wrong username/password
				if(!failedValidation) {
					System.out.println("1. Register a new account");
					System.out.println("2. Sign in to existing account");
					System.out.println("3. Exit application");
					System.out.println(LINE);
					System.out.println("Please enter an option:");					
				}
				try {
					//do not ask for menu option after inputting wrong username/password
					if(!failedValidation) {
						option = scan.nextInt();						
					}
					
					switch(option) {
						//register new user
						case 1:
							System.out.println(LINE);
							System.out.println("Please enter a username:");
							String username = scan.next();
							scan.nextLine();
							System.out.println("Please enter a password:");
							String password = scan.next();
							UserPOJO newUser = new UserPOJO(username, password);
							try {
								userID = mainServ.addUser(newUser).getUserID();
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							try {
								user = mainServ.getUser(userID);
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							loggedIn = true;
							userBankAccounts = new ArrayList<BankAccountPOJO>();
							break;
						//sign in to existing account
						case 2:
							System.out.println(LINE);
							System.out.println("Enter your username: ");
							String loginUsername = scan.next();
							scan.nextLine();
							System.out.println("Enter your password: ");
							String loginPassword = scan.next();
							int validateResult = 0;
							try {
								validateResult = mainServ.validateUser(loginUsername, loginPassword);
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							if(validateResult == -1) {
								System.out.println(LINE);
								System.out.println("Wrong username/password, please try again");
								failedValidation = true;
							} else {
								loggedIn = true;
								userID = validateResult;
								try {
									user = mainServ.getUser(userID);
								} catch (SystemException e1) {
									e1.printStackTrace();
								}
								try {
									userBankAccounts = mainServ.getUserBankAccounts(userID);
								}catch(NoBankAccountException | SystemException e) {
									userBankAccounts = new ArrayList<BankAccountPOJO>();
								}
							}
							break;
						//exit application
						case 3:
							option = -1;
							System.out.println(EXIT);
							System.out.println(LINE);
							break;
						default:
							System.out.println("Please enter a valid option");
							break;
					}
					
				} catch(InputMismatchException e) {
					System.out.println("Please enter a valid option");
				}
				scan.nextLine();
				
			} else {
				System.out.println("Welcome, " + user.getUsername() + "!");
				System.out.println(LINE);
				System.out.println("1. Create a new bank account");
				System.out.println("2. View your bank account(s)");
				System.out.println("3. Make a deposit");
				System.out.println("4. Make a withdrawal");
				System.out.println("5. Transfer funds");
				System.out.println("6. Exit application");
				System.out.println("Please enter an option: ");
				
				try {
					option = scan.nextInt();
					
					switch(option) {
						//create a new bank account
						case 1:
							System.out.println(LINE);
							System.out.println("Enter 1 for 'Checking' or 2 for 'Savings'");
							int accountType = 0;
							while(accountType == 0) {
								try {
									accountType = scan.nextInt();
									if(accountType < 1 || accountType > 2) {
										System.out.println("Enter 1 for 'Checking' or 2 for 'Savings'");
										accountType = 0;
									}
								} catch(InputMismatchException e) {
									System.out.println("Enter 1 for 'Checking' or 2 for 'Savings'");
									scan.nextLine();
								}
							}
							String accountTypeString = (accountType == 1) ? "Checking" : "Savings";
							try {
								userBankAccounts.add(mainServ.addAccountToUser(accountTypeString, user.getUserID()));
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							System.out.println(userBankAccounts.size());
							break;
						//View all bank accounts
						case 2:
							System.out.println(LINE);
							System.out.println("Your Accounts:");
							for (BankAccountPOJO account : userBankAccounts) {
								System.out.println(LINE);
								System.out.println(account.getAccountType() + " -- Balance: " + formatter.format(account.getBalance()));
							}
							break;
						//Make a deposit
						case 3:
							System.out.println(LINE);
							for (int i = 0; i < userBankAccounts.size(); i++) {
								System.out.println((i+1) + ". " + userBankAccounts.get(i).getAccountType() + " -- Balance: " 
										+ formatter.format(userBankAccounts.get(i).getBalance()));
							}
							System.out.println("Choose an account to make a deposit: ");
							int depositAccount = 0;
							while(depositAccount == 0) {
								try {
									depositAccount = scan.nextInt();
									if(depositAccount < 1 || depositAccount > userBankAccounts.size() + 1) {
										System.out.println("Please enter a valid option: '");
										depositAccount = 0;
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid option: ");
									scan.nextLine();
								}
							}
							System.out.println(depositAccount);
							System.out.println("Enter amount to deposit: ");
							double deposit = 0;
							while(deposit <= 0) {
								try {
									deposit = scan.nextDouble();
									if(deposit <= 0 ) {
										System.out.println("Please enter a valid value: '");
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid value: ");
									scan.nextLine();
								}
							}
							try {
								mainServ.updateBalance(userBankAccounts.get(depositAccount-1), deposit);
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							break;
						//Make a withdrawal
						case 4:
							System.out.println(LINE);
							for (int i = 0; i < userBankAccounts.size(); i++) {
								System.out.println((i+1) + ". " + userBankAccounts.get(i).getAccountType() + " -- Balance: " 
										+ formatter.format(userBankAccounts.get(i).getBalance()));
							}
							System.out.println("Choose an account to make a withdrawal: ");
							int withdrawAccount = 0;
							while(withdrawAccount == 0) {
								try {
									withdrawAccount = scan.nextInt();
									if(withdrawAccount < 1 || withdrawAccount > userBankAccounts.size() + 1) {
										System.out.println("Please enter a valid option: '");
										withdrawAccount = 0;
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid option: ");
									scan.nextLine();
								}
							}
							System.out.println(withdrawAccount);
							System.out.println("Enter amount to withdraw: ");
							double withdrawal = 0;
							while(withdrawal <= 0 || withdrawal > userBankAccounts.get(withdrawAccount-1).getBalance()) {
								try {
									withdrawal = scan.nextDouble();
									if(withdrawal <= 0 ) {
										System.out.println("Please enter a positive value: '");
									}
									if(withdrawal > userBankAccounts.get(withdrawAccount-1).getBalance()) {
										System.out.println("Cannot withdraw more than balance, please enter a valid value: ");
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid value: ");
									scan.nextLine();
								}
							}
							try {
								mainServ.updateBalance(userBankAccounts.get(withdrawAccount-1), -withdrawal);
							} catch (SystemException e1) {
								e1.printStackTrace();
							}
							break;
						//Transfer funds between accounts
						case 5:
							System.out.println(LINE);
							for (int i = 0; i < userBankAccounts.size(); i++) {
								System.out.println((i+1) + ". " + userBankAccounts.get(i).getAccountType() + " -- Balance: " 
										+ formatter.format(userBankAccounts.get(i).getBalance()));
							}
							System.out.println("Choose account to be withdrawn from:");
							int withdrawTransfer = 0;
							while(withdrawTransfer == 0) {
								try {
									withdrawTransfer = scan.nextInt();
									if(withdrawTransfer < 1 || withdrawTransfer > userBankAccounts.size() + 1) {
										System.out.println("Please enter a valid option: '");
										withdrawTransfer = 0;
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid option: ");
									scan.nextLine();
								}
							}
							System.out.println("Choose account to deposit into:");
							int depositTransfer = 0;
							while(depositTransfer == 0) {
								try {
									depositTransfer = scan.nextInt();
									if(depositTransfer < 1 || depositTransfer > userBankAccounts.size() + 1) {
										System.out.println("Please enter a valid option: '");
										depositTransfer = 0;
									}
									System.out.println("wt:" + withdrawTransfer);
									System.out.println("dt:" + depositTransfer);
									if(depositTransfer == withdrawTransfer) {
										System.out.println("Cannot transfer to/from the same account, please enter a valid option: ");
										depositTransfer = 0;
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid option: ");
									scan.nextLine();
								}
							}
							System.out.println("Enter amount to transfer: ");
							double transferWithdrawal = 0;
							while(transferWithdrawal <= 0 || transferWithdrawal > userBankAccounts.get(withdrawTransfer-1).getBalance()) {
								try {
									transferWithdrawal = scan.nextDouble();
									if(transferWithdrawal <= 0 ) {
										System.out.println("Please enter a positive value: '");
									}
									if(transferWithdrawal > userBankAccounts.get(withdrawTransfer-1).getBalance()) {
										System.out.println("Cannot withdraw more than balance, please enter a valid value: ");
									}
								} catch(InputMismatchException e) {
									System.out.println("Please enter a valid value: ");
									scan.nextLine();
								}
							}
							try {
								mainServ.updateBalance(userBankAccounts.get(withdrawTransfer-1), -transferWithdrawal);
								mainServ.updateBalance(userBankAccounts.get(depositTransfer-1), transferWithdrawal);
							} catch (SystemException e) {
								e.printStackTrace();
							}
							break;
						//Exit application
						case 6:
							option = -1;
							System.out.println(EXIT);
							System.out.println(LINE);
						default:
							System.out.println("Please enter a valid option");
							break;
					}
					
				} catch(InputMismatchException e) {
					System.out.println("Please enter a valid option");
				}
				scan.nextLine();
			}
		}
		scan.close();
	}
}
