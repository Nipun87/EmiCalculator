package com.EmiCalculator.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmiCalculator.demo.Service.EmiCalculatorService;

@RestController//this annotation referes that this class is a controller(manages HTTP requests)
@CrossOrigin(origins = "http://localhost:4200")//cross origin is used to connect with the frontend
public class EmiCalculatorController {
	
	@Autowired//injecting the service class
	EmiCalculatorService emiCalcServ;
	
//	This is a dummy API for testing whether server is working or not
	@GetMapping("/hello")
	public String display() {
		return "Hello";
	}
//	This API is to solve the value of monthly EMI by taking all the inputs required.
	@GetMapping("/emi")
	public double solve(@RequestParam double loanAmount , @RequestParam double yearlyInterestRate , @RequestParam int loanTermYears) {
		return emiCalcServ.solve(loanAmount,yearlyInterestRate ,loanTermYears);
	}
}
