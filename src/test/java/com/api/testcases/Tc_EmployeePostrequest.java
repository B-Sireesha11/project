package com.api.testcases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.api.base.BaseClass;
import com.api.util.Config;
import com.api.util.ExcelDataReadUtility;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc_EmployeePostrequest extends BaseClass {
	Config conf = new Config();
	String empid;
	String url ="https://jsonplaceholder.typicode.com";

	/*
	 * @Test public void postrequest() { //sending request
	 * //System.out.println("json" +conf.postApi());
	 * logger.info("API request has send"); baseURI = url; request = given(); //
	 * Getting the response System.out.println("requesturl:"+request); //response=
	 * request.contentType(ContentType.JSON).body(conf.PostApi( //
	 * )).post("/create"); response = //
	 * given().body(conf.postApi()).with().contentType("application/json").then().
	 * // expect().statusCode(201) .when().post("/posts"); response
	 * =given().contentType(ContentType.JSON).body(conf.postApi()).post().
	 * then().statusCode(201) .extract().response(); logger.info("response data");
	 * String responsebody = response.asString();
	 * System.out.println("Postrespone body:" + responsebody); JsonPath jsonPath =
	 * new JsonPath(responsebody); empid = jsonPath.getString("id");
	 * System.out.println("empid" + empid); }
	 */

	 
	@Test(priority = 1, dataProvider = "empdetails")
	public void postrequest(String userid, String title, String body) {
		baseURI = url;
		request = given();
		JSONObject req = new JSONObject();
		req.put("userid", userid);
		req.put("title", title);
		req.put("body", body);
		request.header("content_Type", "Applicatio/json");
		request.body(req.toJSONString());
		Response res = request.request(Method.POST, "/posts");
		int statuscode = res.getStatusCode();
		System.out.println("status code:" + statuscode);
		String data = res.asString();

		System.out.println("respone body:" + data);
		//JsonPath jsonPath = new JsonPath(data);
		////empid = jsonPath.getString("id");
		//System.out.println("empid" + empid);
	}

	@DataProvider(name = "empdetails")
    String[][] getData() throws IOException {
	String path = System.getProperty("user.dir") + "/src/test/java/edetails.xlsx";
	int rowcount = ExcelDataReadUtility.getRowCount(path, "Sheet1");
	int columncount = ExcelDataReadUtility.getCellCount(path, "sheet1", 1);
	String empdata[][] = new String[rowcount][columncount];
	System.out.println("empdetails");
	for (int i = 1; i <= rowcount; i++) 
	{
		for (int j = 0; j <columncount; j++)
		{
			
			empdata[i - 1][j] = ExcelDataReadUtility.getCellData(path, "sheet1", i, j);
		}
	}
	return empdata;
	
	}


  @Test(priority = 2)
  public void getRequest()
  { 
//  System.out.println("eid:" +empid); 
  baseURI = url; 
  request= given();
  response = request.request(Method.GET,"/3");
  String reponsebody = response.asString();

  System.out.println("getresponsebody:" + reponsebody);  
  }
  @Test(priority = 3) 
  public void putRequest() 
  { 
  
  baseURI = url; 
  request =given(); 
  response=given().contentType(ContentType.JSON).body(conf.putApi()).put("/posts/" + empid).then().statusCode(404).extract().response();
  //request.header("content_Type", "Applicatio/json");
  String putresponsedata = response.asString();
  System.out.println("putresponsebody" + putresponsedata);
  JsonPath data=new JsonPath(putresponsedata);
  String body=data.getString("body");
  System.out.println("Put body:"+body);
  
  } }
  
 