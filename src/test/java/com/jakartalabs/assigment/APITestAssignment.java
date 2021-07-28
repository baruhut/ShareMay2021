package com.jakartalabs.assigment;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;
import com.jakartalabs.assignment.APIEndpointsAssigment;
import com.jakartalabs.assignment.JsonPathsAssignment;
import com.jakartalabs.assignment.utils.DataUtilsAssignment;
import com.jakartalabs.assignment.utils.TestUtilsAssignment;

import io.restassured.response.Response;

public class APITestAssignment extends BaseAPITestAssignment {

	String authToken = "token not found";
	String fakerName = faker.name().firstName();
	String fakerEmail = faker.name().username() + "@gmail.com";

	@Test(priority = 1)
	public void testSignUpAPI() {

		LinkedTreeMap<String, Object> signUpMap = TestUtilsAssignment
				.convertJsonToMap(DataUtilsAssignment.getDataFromExcel("Payload", "SignUpAPI")
						.replace("uniqueFirstName", fakerName).replace("uniqueEmail", fakerEmail));

		Response response = given().spec(commonSpec).contentType("application/json").body(signUpMap).when()
				.post(APIEndpointsAssigment.SignUpAPI);

		for (String idPayload : signUpMap.keySet()) {

			System.out.println("ID is: " + idPayload + " ----Value is: " + signUpMap.get(idPayload));
		}

		verifyAPIStatusTimeAndHeader(response);

		authToken = getDataFromResponseUsingJsonPath(response, JsonPathsAssignment.authToken);

	}

	@Test(priority = 2)
	public void verifyProfileAPI() {
		Response response = given().spec(commonSpec).header("authToken", authToken).when()
				.get(APIEndpointsAssigment.ProfileAPI);

		verifyAPIStatusTimeAndHeader(response);

		String emailUserProfile = getDataFromResponseUsingJsonPath(response, JsonPathsAssignment.userEmailProfile);

		assertEquals(emailUserProfile, fakerEmail);
	}

	@Test(priority = 3)
	public void logoutAPI() {
		Response response = given().spec(commonSpec).param("authtoken", authToken).when()
				.delete(APIEndpointsAssigment.LogoutAPI);
		verifyAPIStatusTimeAndHeader(response);
	}

}
