package tests;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;

public class RestApiExample {

	@SuppressWarnings("unchecked")
	@Test
	public void postATodoTest( ) {
		
		File fisier = new File("data.json");
		Faker faker = new Faker();
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("title", faker.yoda().quote());
		requestBody.put("body", faker.chuckNorris().fact());
		
		Response response = RestAssured
				.given()
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					//.body(new File("data.json"))
					//.body(fisier)
					.body(requestBody.toJSONString())	
				.when()
					.post("https://keytodorestapi.herokuapp.com/api/save")
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(response.asPrettyString());
		String info = response.jsonPath().getString("info");
		System.out.println(info);
		assertEquals(info, "Todo saved! Nice job!");
		
	}
	
	
	
	
}
