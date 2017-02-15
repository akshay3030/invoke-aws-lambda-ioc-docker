package com.akshay.springboot.ioc.controller;


import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.springboot.ioc.model.LambdaInput;
import com.akshay.springboot.ioc.service.LambdaService;



@RestController
@RequestMapping("/invoke")
public class InvokeLambdaController {

	//@Inject is javax.inject.Inject JavaEE5 way of dependency injection
	//@Autowired is spring way of doing it
	
	@Autowired
	LambdaService lambdaService;
	
	/*
	@Autowired
	AWSCredentialsProvider awsCredentialsProvider;

	@Autowired
	ClientConfiguration clientConfiguration;*/

	@RequestMapping("/lambda/{lname}")
	public String executeLambda(@PathVariable("lname") String lambda_name,@RequestParam String x, @RequestParam String y){
		return lambdaService.processLambdaRequest(lambda_name,x, y);
	}

	//default method is GET
	@RequestMapping(value="/lambda",method=RequestMethod.POST)
	public String executeLambda(@RequestBody LambdaInput lambdaInput,@RequestParam("name") String lambda_name){
		
		return lambdaService.processLambdaRequest(lambda_name,lambdaInput);
	}

}
