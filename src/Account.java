import java.util.ArrayList;
public class Account {
	private String name;     //account names, like checking, saving
	private double balance; 
	private String uuid; //uuid for each account
	private User holder; //link to User class
	private ArrayList<Transaction> transactions; //The list of transactions for this account.
	/*
	  Create new Account instance
	  name		the name of the account
	  holder	the User object that holds this account
	  theBank	the bank that issues the account
	 */
	public Account(String name, User holder, Bank theBank) {
		
		// set the account name and holder
		this.name = name;
		this.holder = holder;
		
		// get next account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		// init transactions
		this.transactions = new ArrayList<Transaction>();
		
	}
	
	/*
	  Get the account number.
	 	the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}

	/*
	  Add a new transaction in this account.
	  amount	the amount transacted
	 */
	public void addTransaction(double amount) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, this);
		this.transactions.add(newTrans);
		
	}
	
	/*
	  Add a new transaction in this account.
	  amount	the amount transacted
	  memo		the transaction memo
	 */
	public void addTransaction(double amount, String memo) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
		
	}
	
	/*
	  Get the balance of this account by adding the amounts of the 
	  transactions.
	  	the balance value
	 */
	public double getBalance() {
		
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
		
	}
	
	/*
	 Get summary line for account
	 	the summary line
	 */
	public String getSummaryLine() {
		
		// get the account's balance
		double balance = this.getBalance();
		
		// format summary line depending on whether balance is negative
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, 
					this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, 
					this.name);
		}
		
	}
	
	/*
	  Print transaction history for account
	 */
	public void printTransHistory() {
		
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
		
	}

}

