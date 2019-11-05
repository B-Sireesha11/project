package com.api.util;

public class Config {
	public String  postApi()
	{

		String json= "{\n"+"\"userId\":\"5\",\n"+
				"\"title\":\"restassured\",\n"+ "\"body\":\"data\"\n"+"}";
		return json;
}
	public String putApi() {
		String json= "{\n"+"\"userId\":\"4\",\n"+
				"\"title\":\"Put\",\n"+ "\"body\":\"data\"\n"+"}";
		return json;
	}
}
	
