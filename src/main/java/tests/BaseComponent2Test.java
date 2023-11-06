package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class BaseComponent2Test extends BaseComponent2 {

	String id;
	
	@Test(priority = 1)
	public void testCreateUser() {
	
		Response resp = doPost(DataBuilder.buildUser().toJSONString());
		System.out.println(resp.asPrettyString());
		id = resp.jsonPath().getString("result._id");
		System.out.println(id);
	}
	
	@Test(priority= 2)
	public void getUser() {
		Response resp = doGet(id);
		System.out.println(resp.asPrettyString());

	}
	
}
