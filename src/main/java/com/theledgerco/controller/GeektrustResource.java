package com.theledgerco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theledgerco.model.BalanceCommandImpl;
import com.theledgerco.model.InputCommand;
import com.theledgerco.model.LoanCommandImpl;
import com.theledgerco.model.CommandOutput;
import com.theledgerco.service.GeektrustService;

/* Controller class for handling HTTP request */
@RestController
@RequestMapping("/geektrust")
public class GeektrustResource {
	
	@Autowired
	private GeektrustService service;
	
	@GetMapping("test")
	public String test() {
		System.out.println("Test");
		return "Test!";
	}
	
	@PostMapping("command-output")
	public List<CommandOutput> getCommandOutput(@RequestBody InputCommand inputCommand) {
		return service.getCommandOutput(inputCommand);
	}
	
	
}
