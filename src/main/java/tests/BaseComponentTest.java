package tests;

import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;

public class BaseComponentTest extends BaseComponent{
	String id;
	
	@Test(priority=1)
	public void postTodo() {
		Response response = doPostRequest("/api/save", DataBuilder.buildTodo().toJSONString(), 200);
		id = response.jsonPath().getString("id");
		assertEquals(response.jsonPath().getString("info"), "Todo saved! Nice job!");//Testng assert
		assertThat(response.jsonPath().getString("info"), is(equalTo("Todo saved! Nice job!")));//Hamcrest assert
		
	}
	
	@Test(priority=2)
	public void getTodo() {
		Response response = doGetRequest("/api/", id, 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("_id"), is(id) );//Hamcrest
		assertEquals(response.jsonPath().getString("_id"), id);//Testng assert

	}
	
	@Test(priority=3)
	public void updateTodo() {
		Response response = doPutRequest("/api/todos/", id, DataBuilder.buildTodo().toJSONString(), 201);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("Item updated")));//Hamcrest assert

		
	}
	

}
