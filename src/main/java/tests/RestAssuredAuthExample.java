package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataBuilder;

import static io.restassured.RestAssured.given;

public class RestAssuredAuthExample {

	String token;
	String id;
	
	@Test(priority=1)
	public void createToken() {
		
		Credentials credentials = new Credentials("admin", "password123");
		
		
		Response result = given().
				contentType(ContentType.JSON).
			//	body(DataBuilder.buildToken().toJSONString()).
				body(credentials).
				post("https://restful-booker.herokuapp.com/auth").
				then().extract().response();
		
		System.out.println(result.asPrettyString());
		token = result.jsonPath().getString("token");
		
	}
	
	@Test(priority=2)
	public void createBooking() {
		
		
		Response result = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildBooking().toJSONString()).
				post("https://restful-booker.herokuapp.com/booking").
				then().extract().response();
		
		System.out.println(result.asPrettyString());
		id = result.jsonPath().getString("bookingid");
	}
	
	@Test(priority=3)
	public void deleteBooking() {
		
		/* Response result = given().
				contentType(ContentType.JSON).
				header("Cookie", "token=" +token).
				delete("https://restful-booker.herokuapp.com/booking/" +id).
				then().extract().response();
		
		System.out.println(result.asPrettyString()); */
		
		Response result = given().
				contentType(ContentType.JSON).
				auth().preemptive().basic("admin", "password123").
			//	header("Cookie", "token=" +token).
				delete("https://restful-booker.herokuapp.com/booking/" +id).
				then().extract().response();
		
		System.out.println(result.asPrettyString());
	}
	
	
}
