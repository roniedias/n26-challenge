package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.domain.Statistic;
import com.n26.service.TransactionService;

@RestController
@RequestMapping("/")
public class StatisticsController {
	
	@Autowired
	TransactionService transactionService;
	
	
	@RequestMapping( value = "statistics", method = RequestMethod.GET )
	public Statistic getStatistics() {
		return transactionService.getStatistics();
	}


}
