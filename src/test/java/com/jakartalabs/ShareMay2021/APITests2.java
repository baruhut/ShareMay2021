package com.jakartalabs.ShareMay2021;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;
import com.jakartalabs.ShareMay2021.utils.DataUtils;
import com.jakartalabs.ShareMay2021.utils.TestUtils;

import io.restassured.response.Response;

public class APITests2 extends BaseAPITest {

	String authToken = "token not found";

	@Test(priority = 1)
	public void testLoginAPIWithInvalidCredentials() {

		LinkedTreeMap<String, Object> loginMap = TestUtils
				.convertJsonToMap(DataUtils.getDataFromExcel("Payload", "LoginAPI"));

		loginMap.put("email", "chandan@gmail.com");
		loginMap.put("password", "chandan");

		Response response = given().spec(commonSpec).body(loginMap).when().post(APIEndpoints.loginAPI);
		verifyAPIStatusTimeAndHeader(response, 422);
	}

	@Test(priority = 2)
	public void testLoginAPI() {

		Response response = given().spec(commonSpec).body(DataUtils.getDataFromExcel("Payload", "LoginAPI")).when()
				.post(APIEndpoints.loginAPI);

		verifyAPIStatusTimeAndHeader(response, 200);

		authToken = getDataFromResponseUsingJsonPath(response, JsonPaths.authToken);

		assertEquals(authToken.contains("token not found"), false);
		assertEquals(getDataFromResponseUsingJsonPath(response, JsonPaths.email),
				DataUtils.getDataFromExcel("Config", "EmailAPI"));

	}

	@Test(priority = 3)
	public void testDashboardAPI() {
		Response dashboardResponse = given().spec(commonSpec).header("authtoken", authToken)
				.param("status", "completed").when().get(APIEndpoints.dashboardAPI);

		verifyAPIStatusTimeAndHeader(dashboardResponse, 200);

		int cardCount = getDataFromResponseUsingJsonPath(dashboardResponse, JsonPaths.completedCardCount);
		assertEquals(cardCount, 292);

	}

}
