package com.akshay.springboot.ioc.aws;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
//import com.amazonaws.auth.AWSCredentialsProviderChain;
//import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

//@Profile("local")
@Configuration
public class AWSConfiguration {
	
	@Autowired
	Environment springEnv;
	
	@Value("${cloud.aws.profile}")
	private String profile;
	
	@Value("${akshay.proxy.host}")
	private String host;
	
	@Value("${akshay.proxy.port}")
	private String port;
	
	//@Profile("local")
	@Bean
	public AWSCredentialsProvider awsCredentialsProvider() {
		if(Arrays.asList(springEnv.getActiveProfiles()).contains("local")){
			return new ProfileCredentialsProvider(profile);
		}	
		return new InstanceProfileCredentialsProvider(false);
		
	}
	
	
	@Bean
	public ClientConfiguration getClientConfiguration(){
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setProtocol(Protocol.HTTPS);
		clientConfiguration.setProxyHost(host);
		clientConfiguration.setProxyPort(Integer.parseInt(port));
		
		return clientConfiguration;
		
	}
	
}
