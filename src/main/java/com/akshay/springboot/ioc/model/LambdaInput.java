package com.akshay.springboot.ioc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LambdaInput {
	
	@JsonProperty("x")
	private int input1;
	
	@JsonProperty("y")
	private int input2;
	
	public int getInput1() {
		return input1;
	}
	public void setInput1(int input1) {
		this.input1 = input1;
	}
	
	public int getInput2() {
		return input2;
	}
	public void setInput2(int input2) {
		this.input2 = input2;
	}

}
