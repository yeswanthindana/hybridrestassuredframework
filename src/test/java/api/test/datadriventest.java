package api.test;
import org.testng.Assert;
import org.testng.annotations.*;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.*;
import io.restassured.response.Response;

public class datadriventest {
	
	
	@Test(priority = 1, dataProvider = "Data",dataProviderClass  = DataProviderclass.class)
	public void testPostUsers(String userID, String uname,String fname,String lastname, String uemail, String password, String ph)
	{
		User payload = new User();
		payload.setId(Integer.parseInt(userID));
		payload.setUserName(uname);
		payload.setFirstName(fname);
		payload.setLastName(lastname);
		payload.setEmail(uemail);
		payload.setPassword(password);
		payload.setPhone(ph);
		
		Response res = UserEndPoints.createUser(payload);
   //     res.then().log().all();

        Assert.assertEquals(res.getStatusCode(), 200, "User creation failed!");
	}
	
	
	@Test(priority = 2, dataProvider = "usernames",dataProviderClass = DataProviderclass.class)
	public void testdeleteusers(String username) {
		Response res =
				UserEndPoints.deleteUser(username);
		//res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}
	

}
