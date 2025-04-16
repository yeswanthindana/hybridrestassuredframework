package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userendpointsfromprop {
	
	static ResourceBundle getURL(){
		//METHOD CREATED FOR READING URL from properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes");  //Load properties file
		return routes;
	}
	
	public static Response createUser(User payload){
		{
		String post_url = getURL().getString("posturl");
		Response res =
			given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url)			
				;
			
			
			return res;
		}

}
	
	
	public static Response readUser(String userName) 
	{
		String get_url = getURL().getString("geturl");
		Response res = 
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
			.pathParam("username", userName)
		.when()
				.get(get_url);	
		return res;
		
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String put_url = getURL().getString("puturl");
		Response res =
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				
				.when()
					.put(put_url)
					;
				
		
		return res;
	}
	
	public static Response deleteUser(String userName) {
		String del_url = getURL().getString("deleteurl");
		Response res =
				
				given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)

				.when()
					.delete(del_url);
				
				return res;
				
	}

}
