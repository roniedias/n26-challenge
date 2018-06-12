package com.n26.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.n26.domain.Statistic;
import com.n26.domain.Transaction;

@Service
public interface TransactionService  {
	ResponseEntity<?> createTransaction(Transaction transaction);
	Statistic getStatistics();
	List<Transaction> getTransactionLst();
		
	

}
