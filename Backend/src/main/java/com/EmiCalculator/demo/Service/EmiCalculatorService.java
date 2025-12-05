package com.EmiCalculator.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service//defines it is a service class(used for business logic)
public class EmiCalculatorService {
	
	public double solve(@RequestParam double loanAmount , @RequestParam double yearlyInterestRate , @RequestParam int loanTermYears) {
		if(loanAmount<=0) {
			throw new IllegalArgumentException("Loan Amount must be a positive number");
		}
		
		if(yearlyInterestRate<=0 || yearlyInterestRate>100) {
			throw new IllegalArgumentException("Yearly Interest rate must be between 0 and 100");
		}
		
		if(loanTermYears<=0 || loanTermYears>30) {
			throw new IllegalArgumentException("Loan Term years should be between 0 and 30");
		}
		//above if conditions are used to handle the exceptions
		
		double p = loanAmount;
		double r = yearlyInterestRate/12/100;
		int n = loanTermYears*12;
		double calc = Math.pow(1+r,n );
		double emi = p*r*calc/(calc-1);//logic to calculate EMI
	
		return Math.round(emi*100.0)/100.0;
	}
}
