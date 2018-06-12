package com.n26.domain;

public class Transaction {
	

	private Double amount;
    private long timestamp;
	

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
		
	public Transaction() {}
	
	public Transaction(Double amount, long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	
	
	
}
