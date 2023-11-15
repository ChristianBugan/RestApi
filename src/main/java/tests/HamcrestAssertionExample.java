package tests;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static utils.NumberChecker.*;
import static utils.NumberIsPositive.*;

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
		
		JsonPath jsnPath =  resp.jsonPath();
		String name = jsnPath.getString("name");
		System.out.println(name);
		
		//TestNg assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
	//	assertThat(name, "Tatooine");
	//	assertThat(name, "Tatooine");	
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine")); // is (T value)
		assertThat(name, is(equalTo("Tatooine")));// is(Matcher<T>)
		
		//TestNg assert
		assertNotEquals(name, "Terra");
		
		//Hamcrest assert
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));

		//starts-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(name, startsWith("Tato"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));

		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("aPi/pLaNeTs/1/\"}"));

		//containes
		assertThat(name, containsStringIgnoringCase("tooi"));
		assertThat(resp.asString(), containsString("desert"));
		assertThat(name, containsString("tooi"));
		
		//pattern
		assertThat(name, matchesPattern("[A-Za-z]+"));
		name="Tatooine123";
		assertThat(name, matchesPattern("[A-Za-z0-9]+"));
		String diameter =  jsnPath.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
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
		        startsWith("https://swapi.dev"), 
		        endsWith("api/films/6/")));
		
		assertThat(movies, contains(
				startsWith("https://sw"), 
		        endsWith("3/"), 
		        equalTo("https://swapi.dev/api/films/4/"), 
		        startsWith("https://swapi.dev"), 
		        equalTo("https://swapi.dev/api/films/6/")));
	
		
		assertThat(movies, hasItem("https://swapi.dev/api/films/6/"));
		assertThat(movies, hasItems("https://swapi.dev/api/films/6/","https://swapi.dev/api/films/4/"));
		assertThat(movies, hasItem(startsWith("http")));
		assertThat(movies, hasItem(containsString("swapi")));
		assertThat(movies, hasItems(startsWith("http"), containsString("swapi")));
		
		
		assertThat(movies, hasSize(5));
		assertThat(movies, hasSize(lessThan(10)));
		assertThat(movies, hasSize(greaterThan(3)));
		
		assertThat(movies, both(hasSize(lessThan(6))).and(hasToString(containsString("films/6"))));

		String[] array = {jsnPath.getString("climate"), jsnPath.getString("terrain"),
				jsnPath.getString("diameter"), jsnPath.getString("gravity"),
				jsnPath.getString("name")};

		System.out.println(array[0]);
		assertThat(array, is(not(emptyArray())));
		assertThat(array, is(not(nullValue())));
		
		System.out.println(Arrays.toString(array));
		
		assertThat(array, arrayContaining(
				"arid", "desert", "10465", "1 standard", "Tatooine"
				));
		
		
		assertThat(array, arrayContainingInAnyOrder(
				"Tatooine","arid", "desert", "10465", "1 standard" ));
		
		System.out.println(resp.asString());
		
		assertThat(resp.asString(), containsStringIgnoringCase("ARID") );
		assertThat(resp.asString(), stringContainsInOrder("name","rotation_period" ));

		//and
		assertThat(resp.asString(), both(containsString("url")).and(containsString(diameter)));
		//or
		assertThat(name, either(is("Tatooine")).or(is("Tatooine2")).or(is(not("Tatooine5"))));
	
		/*
		 *  "rotation_period": "23", 
    "orbital_period": "304", 
    "diameter": "10465", 
    "climate": "arid", 
    "gravity": "1 standard", 
    "terrain": "desert", 
    "surface_water": "1", 
		 */
		
		String rotation = jsnPath.getString("rotation_period");
		String climate = jsnPath.getString("climate");
		String gravity = jsnPath.getString("gravity");
		
		System.out.println("------------------------------------");
		System.out.println(rotation);
		System.out.println(climate);
		System.out.println(gravity);
	
		
		assertThat(rotation, is(numbersOnly()));
		assertThat(gravity, is(not(numbersOnly())));
		
		assertThat(Integer.parseInt(rotation), is(postiveNumber()));
		
		assertThat(-7, is(not(postiveNumber())));

	//	assertThat(-7, is(postiveNumber()));
		
	}
	

}
