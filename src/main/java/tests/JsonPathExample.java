package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;
import static io.restassured.RestAssured.given;

import java.util.List;

public class JsonPathExample extends BaseComponent {

	@Test
	public void jsonPathExamples() {
		
		Response response = given()
				                .get("https://keytrcrud.herokuapp.com/api/users/\r\n"
				                		+ "")
				                .then().extract().response();
		System.out.println(response.asString());
		
		JsonPath jsonPath = response.jsonPath();
		
		// accesez obiect pe baza de index
		System.out.println(jsonPath.getString("[6]"));
		System.out.println(jsonPath.getString("[6].name"));
		System.out.println(jsonPath.getString("[6]._id"));
		System.out.println(jsonPath.getString("[6].age"));
	
		// accesez direct fara atribut fara index
		System.out.println(jsonPath.getString("name"));
		System.out.println(jsonPath.getString("gender"));
		System.out.println("--------------------------------------------");
		
		System.out.println(jsonPath.getList("findAll{it.gender == 'm'}.name"));
		
		List<String> allElla = jsonPath.getList("findAll{it.name == 'Ella'}.age");
		System.out.println(allElla);
	}
	
	
	
}
