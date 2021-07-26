package com.jakartalabs.assigment;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.jakartalabs.assignment.APIEndpointsAssigment;
import com.jakartalabs.assignment.JsonPathsAssignment;
import com.jakartalabs.assignment.utils.DataUtils;

import io.restassured.response.Response;

public class APITestAssignment extends BaseAPITestAssignment {

	String authToken = "token not found";
	String userEmail = "email invalid";

	@Test(priority = 1)
	public void testSignUpAPI() {

		Response response = given().spec(commonSpec).contentType("application/json")
				.body(DataUtils.getDataFromExcel("Payload", "SignUpAPI")).when().post(APIEndpointsAssigment.SignUpAPI);

		verifyAPIStatusTimeAndHeader(response);

		authToken = getDataFromResponseUsingJsonPath(response, JsonPathsAssignment.authToken);
		userEmail = getDataFromResponseUsingJsonPath(response, JsonPathsAssignment.userEmail);

	}

	@Test(priority = 2)
	public void verifyProfileAPI() {
		Response response = given().spec(commonSpec).header("authToken", authToken).when()
				.get(APIEndpointsAssigment.ProfileAPI);

		verifyAPIStatusTimeAndHeader(response);

		String emailUserProfile = getDataFromResponseUsingJsonPath(response, JsonPathsAssignment.userEmailProfile);

		assertEquals(emailUserProfile, userEmail);
	}

	@Test(priority = 3)
	public void logoutAPI() {
		Response response = given().spec(commonSpec).param("authtoken", authToken).when()
				.delete(APIEndpointsAssigment.LogoutAPI);
		verifyAPIStatusTimeAndHeader(response);
	}

}
