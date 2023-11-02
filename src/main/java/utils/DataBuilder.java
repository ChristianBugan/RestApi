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

}
