package com.jakartalabs.ShareMay2021;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.jakartalabs.ShareMay2021.utils.DataUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPITest {

	protected RequestSpecification commonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtils.getDataFromExcel("Config", "BaseAPIUrl")).setAccept(ContentType.JSON)
			.setContentType(ContentType.JSON).build().log().all();

	protected void verifyAPIStatusTimeAndHeader(Response response, int expectedStatusCode) {
		assertEquals(response.getStatusCode(), expectedStatusCode);
		assertEquals(response.getTimeIn(TimeUnit.SECONDS) < 10, true);
		assertEquals(response.getHeader("X-Frame-Options"), "SAMEORIGIN");

	}

	protected <T> T getDataFromResponseUsingJsonPath(Response response, String jsonPath) {

		JsonPath jpath = response.jsonPath();

		return jpath.get(jsonPath);
	}

}
