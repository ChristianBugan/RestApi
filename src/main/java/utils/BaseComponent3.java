package utils;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class BaseComponent3 {

	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public void requestSpecificationBuilder() {
		
	requestSpec =  new RequestSpecBuilder().
			setBaseUri("https://keytrcrud.herokuapp.com").
			setBasePath("/api/users/").
			setContentType(ContentType.JSON).
			addHeader("accept", "application/json").
			build();
	}
	
	@BeforeClass
	public void repsonseSpecificationBuilder() {
		responseSpec = new ResponseSpecBuilder().
				expectContentType(ContentType.JSON).
				expectStatusCode(either(is(200)).or(is(201)).or(is(204)))
				.build();

	}
	
	public static Response doRequest(String HTTPMethod, String id, String body) {
		
		Response result = null;
		
		switch(HTTPMethod.toUpperCase()) {
		
		case "GET" : 
			result = given().spec(requestSpec).get(id);
			break;
		case "GET_ALL" : 
			result = given().spec(requestSpec).get();
			break;
		case "POST" :
			result = given().spec(requestSpec).body(body).post();
			break;
		case "PUT" :
			result = given().spec(requestSpec).body(body).put(id);
			break;
		case "DELETE":
			result = given().spec(requestSpec).delete(id);
			break;
		case "DELETE_ALL" :
			result = given().spec(requestSpec).delete();
			break;
		
		}
		
		if(result != null) {
			result = result.then().spec(responseSpec).extract().response();
		}
		
		
		return result;
	}
	
	
	
}
