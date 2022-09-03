package com.restapiexample.dummy;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC_Employees{

	@Test
	public void test_getAllEmployees() {
		Response respuesta = 
		given()	
		.when()
			.get("http://dummy.restapiexample.com/api/v1/employees")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonString = respuesta.asString();
		Assert.assertEquals(jsonString.contains("Successfully! All records has been fetched."), true);
	}
}
