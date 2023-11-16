package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent3;
import utils.DataBuilder;

public class BaseComponent3Test extends BaseComponent3{

	String id;
	
	@Test(priority =1)
	public void createUser() {
		System.out.println("-----------POST-----------------------------");
		Response resp =  doRequest("post", "", DataBuilder.buildUser().toJSONString());
		id = resp.jsonPath().getString("result._id");
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority=2)
	public void readUser() {
		System.out.println("-----------GET-----------------------------");
		Response resp = doRequest("GET", id, "");
		System.out.println(resp.asPrettyString());

	}
	
	@Test(priority=3)
	public void readALLUser() {
		System.out.println("-----------GET ALL-----------------------------");
		Response resp = doRequest("GET_ALL", "", "");
		System.out.println(resp.asString());
	}
	
	@Test(priority = 4)
	public void updateUser() {
		System.out.println("-----------UPDATE USER-----------------------------");
		Response resp = doRequest("PUT", id, DataBuilder.buildUser().toJSONString());
		System.out.println(resp.asString());

	}
	
	//@Test(priority=5)
	public void deleteUser() {
		System.out.println("-----------DELETE USER-----------------------------");
		Response resp = doRequest("Delete", id, "");
		System.out.println(resp.asString());

	}
}
