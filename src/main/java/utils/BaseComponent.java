package utils;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseComponent {

	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com";
		
	}
	
	
	public static Response doPostRequest(String path,String requestBody, int statusCode) {
		Response response = 
				given().
					contentType(ContentType.JSON).
					body(requestBody).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();
		return response;	
		
	}
	
	
	public static Response doGetRequest(String path, String id, int statusCode) {
		
		Response response = 
				given().
					contentType(ContentType.JSON).
				when().
					get(path + id).
				then().
					statusCode(statusCode).
					extract().response();
		return response;
		
	}
	
	
	public static Response doPutRequest(String path,String id, String requestBody, int statusCode) {
		Response response = 
				given().
					contentType(ContentType.JSON).
					body(requestBody).
				when().
					put(path + id).
				then().
					statusCode(statusCode).
					extract().response();
		return response;	
		
	}
	
}
