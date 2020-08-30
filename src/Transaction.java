import java.util.Date;

public class Transaction {
	
	private double amount;//The amount of this transaction
	private Date timestamp; //The time and date of this transaction
	private String memo; //A memo for this transaction 
	private Account inAccount;//The account in which the transaction was performed
	
	/*
	  Create a new transaction.
	   amount		the dollar amount transacted
	  inAccount	the account the transaction belongs to
	 */
	public Transaction(double amount, Account inAccount) {
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
		
	}
	
	/*
	  Create a new transaction with a memo.
	   amount	the dollar amount transacted
	   memo		the memo for the transaction
	   inAccount	the account the transaction belongs to
	 */
	public Transaction(double amount, String memo, Account inAccount) {
		
		// call the single-arg constructor first
		this(amount, inAccount);
		
		this.memo = memo;
		
	}
	
	/*
	  Get the transaction amount.
	  	the amount of the transaction
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/*
	  Get a string summarizing the transaction
	  the summary string
	 */
	public String getSummaryLine() {
		
		if (this.amount >= 0) {
			return String.format("%s, $%.02f : %s", 
					this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s, $(%.02f) : %s", 
					this.timestamp.toString(), -this.amount, this.memo);
		}
	}

}