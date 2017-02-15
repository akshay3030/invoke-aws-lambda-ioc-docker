package com.akshay.springboot.ioc.service;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.springboot.ioc.model.LambdaInput;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LambdaService {

	//@Inject is javax.inject.Inject JavaEE5 way of dependency injection
	//@Autowired is spring way of doing it

	@Autowired
	AWSCredentialsProvider awsCredentialsProvider;
	
	//below is for proxy config
	@Autowired
	ClientConfiguration clientConfiguration;

	//GET
	public String processLambdaRequest(String lambdaName,String input1,String input2){

		String lambdaoutput = null,responsetime=null,final_response=null;
		long start = new Date().getTime();

		//AWSLambdaClient awsLambdaClient = new AWSLambdaClient(awsCredentialsProvider,clientConfiguration);
		AWSLambdaClient awsLambdaClient = new AWSLambdaClient(awsCredentialsProvider);

		
		try{
			InvokeRequest invokeRequest = new InvokeRequest();
			invokeRequest.setFunctionName(lambdaName);
			invokeRequest.setPayload("{\"x\":"+input1+",\"y\":"+input2+"}");
			lambdaoutput = byteBufferToString(awsLambdaClient.invoke(invokeRequest).getPayload(), Charset.forName("UTF-8"));
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			lambdaoutput = e.getMessage();
		}

		responsetime = "HTTPGET: Response Time is : "+ ( new Date().getTime()- start ) +"ms";
		final_response = "HTTPGET: Response from AWS Lambda - "+lambdaName+" :  "+lambdaoutput;

		System.out.println(final_response);
		System.out.println(responsetime);

		return lambdaoutput;

	}

	//POST
	public String processLambdaRequest(String lambdaName,LambdaInput lambdaInput){

		String lambdaoutput = null,responsetime=null,final_response=null;
		long start = new Date().getTime();

		//AWSLambdaClient awsLambdaClient = new AWSLambdaClient(awsCredentialsProvider,clientConfiguration);
		AWSLambdaClient awsLambdaClient = new AWSLambdaClient(awsCredentialsProvider);

		try{
			ObjectMapper objectMapper = new ObjectMapper();
			String modelInputJson = objectMapper.writeValueAsString(lambdaInput);

			InvokeRequest invokeRequest = new InvokeRequest();
			invokeRequest.setFunctionName(lambdaName);
			invokeRequest.setPayload(modelInputJson);
			lambdaoutput = byteBufferToString(awsLambdaClient.invoke(invokeRequest).getPayload(), Charset.forName("UTF-8"));
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			lambdaoutput = e.getMessage();
		}

		responsetime = "HTTPPOST: Response Time is : "+ ( new Date().getTime()- start ) +"ms";
		final_response = "HTTPPOST: Response from AWS Lambda - "+lambdaName+" :  "+lambdaoutput;

		System.out.println(final_response);
		System.out.println(responsetime);

		return lambdaoutput;

	}

	public static String byteBufferToString(ByteBuffer buffer, Charset charset){

		byte[] bytes;
		if(buffer.hasArray()){
			bytes = buffer.array();
		}else{
			bytes = new byte[buffer.remaining()];
			buffer.get(bytes);
		}
		return new String(bytes, charset);


	}

}
