package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.domain.Transaction;
import com.n26.service.TransactionService;

@RestController
@RequestMapping("/")
public class TransactionController {
	
	
	@Autowired
	TransactionService transactionService;
	
			
	@RequestMapping( value = "transactions", method = RequestMethod.POST )
	public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
		return transactionService.createTransaction(transaction);
	}	
		
	
}
