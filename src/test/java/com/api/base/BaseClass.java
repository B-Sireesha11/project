package com.api.base;

import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.log4j.PropertyConfigurator;
public class BaseClass {

	public static RequestSpecification request;
	public static Response response;
	public static  String empid = " ";
	 
	public Logger logger;
	    
  @BeforeClass
	public void setup() {
		 logger = Logger.getLogger(BaseClass.class);
		 PropertyConfigurator.configure("./Log4j/log4j.properties");
	}
}
