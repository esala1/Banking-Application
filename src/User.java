import java.util.ArrayList;
import java.security.MessageDigest;
public class User {

	private String firstName; //user's First name
	private String lastName; // user's last name
	private String uuid; // universally unique identifier for the user
	private byte pinHash[]; //the MD5 hash of the user's pin number
	private ArrayList<Account> accounts; // the # of accounts for the user

	/* comments for constructor
	  Create new user
	  firstName	the user's first name
	  lastName	the user's last name
	  pin				the user's account pin number (as a String)
	  theBank		the bank that the User is a customer of
	 */
	public User (String firstName, String lastName, String pin, Bank theBank) {
		
		// set user's name
		this.firstName = firstName;
		this.lastName = lastName;
		
		// store the pin's MD5 hash, rather than the original value, for 
		// security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (Exception e) {
			System.err.println("error, caught exeption : " + e.getMessage());
			System.exit(1);
		}
		
		// get a new, unique universal unique ID for the user
		this.uuid = theBank.getNewUserUUID();
		
		// create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		// print log message
		System.out.printf("New user %s, %s with ID %s created.\n", 
				lastName, firstName, this.uuid);
		
	}
	
	/*
	  Get the user ID number
	  @return	the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/*
	  Add an account for the user.
	  anAcct	the account to add
	 */
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	/*
	  Get the number of accounts the user has.
	  	the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/*
	  Get the balance of a particular account.
	   acctIdx	the index of the account to use
	  			the balance of the account
	 */
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/*
	  Get the UUID of a particular account.
	   acctIdx	the index of the account to use
	 			the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	/*
	  Print transaction history for a particular account.
	   acctIdx	the index of the account to use
	 */
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
	/*
	  Add a transaction to a particular account.
	   acctIdx	the index of the account
	  amount	the amount of the transaction
	  memo		the memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
	/*
	  Check whether a given pin matches the true User pin
	  aPin	the pin to check
	 		whether the pin is valid or not
	 */
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), 
					this.pinHash);
		} catch (Exception e) {
			System.err.println("error, caught exeption : " + e.getMessage());
			System.exit(1);
		}
		
		return false;
	}
	
	/*
	  Print summaries for the accounts of this user.
	 */
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, 
					this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
		
	}
}
