package com.n26.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.n26.domain.Statistic;
import com.n26.domain.Transaction;
import com.n26.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);	
	
	private List<Transaction> transactionLst = Collections.synchronizedList(new ArrayList<>());

	@Override
	public ResponseEntity<?> createTransaction(Transaction transaction) {
		
		int secs = secondsElapsed(transaction.getTimestamp());
				
		if(secs <= 60) {
			synchronized(transactionLst) {				
				transactionLst.add(transaction);
			}
			return new ResponseEntity<>(HttpStatus.CREATED);
		}		
		// If it is greater than 60 seconds, does not add transaction to list
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
		
	@Override
	public Statistic getStatistics() { 
		
		Statistic sDTO = new Statistic();
		double sum = 0;
		
		synchronized (transactionLst) {
			
		
			transactionLst.removeIf(t -> secondsElapsed(t.getTimestamp()) > 60);
			transactionLst.sort((Transaction t2, Transaction t1)->t2.getAmount().compareTo(t1.getAmount()));
		
			for(Transaction t : transactionLst) {
				sum += t.getAmount();
			}
		
				
			sDTO.setSum(sum);
		
			if(transactionLst.size() == 0) {
				sDTO.setAvg(0);
				sDTO.setMax(0.0);
				sDTO.setMin(0.0);
			}
			else {
				sDTO.setAvg(sum/transactionLst.size());
				sDTO.setMax(transactionLst.get(transactionLst.size() - 1).getAmount());
				sDTO.setMin(transactionLst.get(0).getAmount());
			}
		
			sDTO.setCount(transactionLst.size());
		}
	
		return sDTO;
		
	}
	

	// Returns the difference (in seconds) between current and timestamp informed   
	private int secondsElapsed(long timestamp) {
		long elapsed = System.currentTimeMillis() - timestamp;
		return (int) elapsed / 1000;
	}
	
	// Temporary
	@Override
	public List<Transaction> getTransactionLst() {
		return transactionLst;
	}








		

}
