package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class BaseComponent2 {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void requestSpecificationBuilder() {
	
	requestSpec = new RequestSpecBuilder().
			setBaseUri("https://keytrcrud.herokuapp.com").
			setBasePath("/api/users/").
			setContentType(ContentType.JSON).
			addHeader("accept", "application/json").
			build();
	}
	
	@BeforeClass
	public void responseSpecificationBuilder() {
		responseSpec = new ResponseSpecBuilder().
				expectContentType(ContentType.JSON).
				expectStatusCode(either(is(200)).or(is(201))).build();
	}
	
	public static Response doPost(String body) {
		
		Response response = 
				given().
					spec(requestSpec).
					body(body).
				when().
					post().
				then().
					spec(responseSpec).
					extract().
					response();
		
		return response;		
	}
	
	public static Response doGet(String id) {
		
		Response response = 
				given().
					spec(requestSpec).
				when().
					get(id).
				then().
				spec(responseSpec).extract().response();
		
		return response;
	}
	
	
}
