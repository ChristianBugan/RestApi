package utils;

import org.codehaus.groovy.ast.AstToTextHelper;
import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	
	
	
	public static JSONObject buildTodo() {
		
		JSONObject todoBuilder =  new JSONObject();
		Faker faker =  new Faker();
		todoBuilder.put("title", faker.cat().name());
		todoBuilder.put("body", faker.chuckNorris().fact());
		
		return todoBuilder;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		
		JSONObject user = new JSONObject();
		Faker faker = new Faker();
		
		user.put("name", faker.name().firstName());
		user.put("email", faker.internet().safeEmailAddress());
		user.put("age", faker.number().numberBetween(5, 130));
		user.put("gender", "f");
		
		return user;
		
	}

}
