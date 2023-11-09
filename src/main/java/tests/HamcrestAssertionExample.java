package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HamcrestAssertionExample {

	
	@SuppressWarnings("unchecked")
	@Test
	public void hamcrestMatchers() {
		
		Response resp = 
				given().
					get("https://swapi.dev/api/planets/1/").
				then().
					extract().
					response();
		
		System.out.println(resp.asString());
		
		JsonPath jsnPath = resp.jsonPath();
		String name = jsnPath.getString("name");
		System.out.println(name);
		
		//TestNG assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
		assertThat(name, equalTo("Tatooine"));
		
		assertThat(name, is("Tatooine")); //face acelasi lucru ca linia de mai sus
		// is (T Value)
		
		assertThat(name, is(equalTo("Tatooine"))); //same thing ca mai sus 
		// is(Matcher<T>)
		
		//TestNG assert
		assertNotEquals(name, "Terra");
		
		//Hamcrest assert
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		
		//starts-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(name, startsWith("Tato"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));
		
		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		
		//contains
		assertThat(name, containsStringIgnoringCase("tooi"));
		assertThat(resp.asString(), containsString("desert"));
		assertThat(name, containsString("tooi"));
		
		//pattern
		assertThat(name, matchesPattern("[A-Za-z]+"));
		
		List<String> movies = jsnPath.getList("films");
		System.out.println(movies.get(1));
		
		assertThat(movies, contains(
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(movies, contains(
				startsWith("https://sw"), 
		        endsWith("3/"), 
		        equalTo("https://swapi.dev/api/films/4/"), 
		        startsWith("https://swapi.dev/"), 
		        endsWith("api/films/6/")));
		
		assertThat(movies, hasItem("https://swapi.dev/api/films/6/"));
		
		assertThat(movies, hasItems("https://swapi.dev/api/films/6/",  "https://swapi.dev/api/films/4/"));
		
		assertThat(movies, hasItem(startsWith("http")));
		assertThat(movies, hasItem(containsString("swapi")));
		
		assertThat(movies, hasSize(5));
		assertThat(movies, hasSize(lessThan(10)));
		assertThat(movies, hasSize(greaterThan(3)));
		
		
	}
	
	
}
