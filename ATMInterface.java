package AtmInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class User {
	private String userId;
	private String pin;
	private double balance;
	private Map<Integer,String> transactionHistory;
	public User(String userId,String pin)
	{
		this.userId=userId;
		this.pin=pin;
		this.balance=0.0;
		this.transactionHistory=new HashMap<>();
	}
	public String getUserId() {
		return userId;
	}
	public String getPin() {
		return pin;
	}
	public double getBalance() {
		return balance;
	}
	public Map<Integer, String> getTransactionHistory() {
		return transactionHistory;
	}
	public void deposit(double amount)
	{
		balance+=amount;
		transactionHistory.put(transactionHistory.size()+1,"Deposite:+"+amount);
	}
	public void withdraw(double amount)
	{
		if(amount<=balance)
		{
			balance -=amount;
			transactionHistory.put(transactionHistory.size()+1,"Withdraw: +"+amount);
		}
		else
		{
			System.out.println("Insufficient funds");
		}
	}
	public void transfer(User recipient,double amount)
	{
		if(amount<=balance)
		{
			balance -=amount;
			recipient.deposit(amount);
			transactionHistory.put(transactionHistory.size()+1,"Transfer to"+recipient.getUserId()+":-"+amount);
		}
		else
		{
			System.out.println("insufficient funds for transfer");
		}
	}
	public User get(String recipientId,Map<String,User> users)
	{
		return users.get(recipientId);
	}
}

public class ATMInterface {
		public static void main(String[]args)
		{
			Scanner scn=new Scanner(System.in);
			Map<String,User> users=new HashMap<>();
			
//			Creating some sample users
			users.put("user1",new User("user1","1234"));
			users.put("user2",new User("user2","5678"));
			System.out.println("welcome to the ATM sYSTEM!");
//			User authentication
			System.out.println("Enter user id:");
			String userId=scn.nextLine();
			System.out.println("enter pin:");
			String pin=scn.nextLine();
			
			User currentUser=users.get(userId);
			if(currentUser!=null && currentUser.getPin().equals(pin))
			{
				System.out.println("Authentication succesfull.Welcome,"+userId+"!");
				performATMOperations(currentUser,scn,users);
			}
			else
			{
				System.out.println("Invalid credential.Existing");
			}
			scn.close();
		}
		private static void performATMOperations(User user,Scanner scn,Map<String,User>users)
		{
			while(true)
			{
				System.out.println("\nATM Operation");
				System.out.println("1.Transaction History");
				System.out.println("2.Withdraw");
				System.out.println("3.Deposite");
				System.out.println("4.Tranfer");
				System.out.println("5.Quit");
				System.out.println("enter your choice");
				int choice=scn.nextInt();
				
			switch(choice)
			{
			case 1:
				displayTransactionHistory(user);
				break;
			case 2:
				System.out.println("enter withdrawl amount:");
				double withdrawAmount=scn.nextDouble();
				user.withdraw(withdrawAmount);
				break;
			case 3:
				System.out.println("enter deposite amount:");
				double depositAmount=scn.nextDouble();
				user.deposit(depositAmount);
			case 4:
				System.out.println("enter recipient's user Id:");
				String  recipientId=scn.next();
				User recipient=user.get(recipientId, users);
				if(recipient!=null)
				{
					System.out.println("enter transfer amount:");
					double transferAmount=scn.nextDouble();
					user.transfer(recipient, transferAmount);
				}
				else {
					System.out.println("Recipient not found.");
				}
				break;
			case 5:
				System.out.println("thank you using ATM,.HAVE AGOOD DAY!");
				System.exit(0);
			default:
				System.out.println("invalid choice please try again.");
				
			}
			}
		}
		private static void displayTransactionHistory(User user)
		{
			System.out.println("\nTransaction History for" + user.getUserId()+":");
			Map<Integer,String> transactionHistory=user.getTransactionHistory();
			if(transactionHistory.isEmpty())
			{
				System.out.println("No transaction yet:");
			}
			else
			{
				for(Map.Entry<Integer, String> entry:transactionHistory.entrySet())
				{
					System.out.println(entry.getKey()+". "+entry.getValue());
				}
			}
		}
		
	}


