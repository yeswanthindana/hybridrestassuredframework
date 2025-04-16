package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.userendpointsfromprop;
import api.payloads.User;
import io.restassured.response.Response;

public class Usertestsfromprop {

     Faker faker;
     User payload;
     Logger logger;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        payload = new User();

        payload.setId(faker.idNumber().hashCode());
        payload.setUserName(faker.name().username());
        payload.setFirstName(faker.name().firstName());
        payload.setLastName(faker.name().lastName());
        payload.setEmail(faker.internet().safeEmailAddress());
        payload.setPassword(faker.internet().password(5, 10));
        payload.setPhone(faker.phoneNumber().cellPhone());
        
        logger = LogManager.getLogger(this.getClass());
        
        System.out.println("Generated Test Payload:");
        System.out.println("Username: " + payload.getUserName());
        System.out.println("ID: " + payload.getId());
        System.out.println("First Name: " + payload.getFirstName());
        System.out.println("Last Name: " + payload.getLastName());
        System.out.println("Email: " + payload.getEmail());
        System.out.println("Password: " + payload.getPassword());
        System.out.println("Phone: " + payload.getPhone());
        
       
    }

    @Test(priority = 0)
    public void testPostUser() {
    	logger.info("*****************CREATING USER**********************");
        Response res = userendpointsfromprop.createUser(payload);
        res.then().log().all();
        
        Assert.assertEquals(res.getStatusCode(), 200, "User creation failed!");
        logger.info("*****************USER IS CREATED/FAILED**********************");
    }

    @Test(priority = 1)
    public void testGetUserByName()  {
    	logger.info("***************** USER INFO READING**********************");
    	   Response res = userendpointsfromprop.readUser(payload.getUserName());
    	        res.then().log().all();
    	        Assert.assertEquals(res.getStatusCode(), 200, "User retrieval failed!");
    	        logger.info("***************** USER INFO IS DISPLAYED**********************");
}
    
    
    @Test(priority = 2)
    public void testupdateUserbyName() {
    	//updating data using same payload 
    	logger.info("***************** USER INFO UPDATING**********************");
    	 payload.setFirstName(faker.name().firstName());
         payload.setLastName(faker.name().lastName());
         payload.setEmail(faker.internet().safeEmailAddress());
         
         Response res =
        		 userendpointsfromprop.updateUser(this.payload.getUserName(), payload);
         		res.then().log().body().statusCode(200);
         		Assert.assertEquals(res.getStatusCode(), 200);
         		
         	// checking data after update
         	
         		Response res1 = userendpointsfromprop.readUser(this.payload.getUserName());
         		res1.then().log().all();
         		Assert.assertEquals(res1.getStatusCode(), 200);
         		logger.info("***************** USER INFO UPDATES**********************");
    }
    
    @Test(priority = 3)
    public void testDeleteByName() {
    	logger.info("***************** USER DELETING**********************");
    		Response res =
    				userendpointsfromprop.deleteUser(this.payload.getUserName());
    		res.then().log().all();
    		Assert.assertEquals(res.getStatusCode(), 200);
    		logger.info("***************** USER  DELETED**********************");
    	
    }
}
