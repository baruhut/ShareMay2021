package com.jakartalabs.assigment;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.google.gson.internal.LinkedTreeMap;
import com.jakartalabs.assignment.utils.DataUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPITestAssignment {

	protected RequestSpecification commonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtils.getDataFromExcel("Config", "BaseAPIUrl")).setAccept("application/json").build().log()
			.all();

	protected void verifyAPIStatusTimeAndHeader(Response response) {
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getTimeIn(TimeUnit.SECONDS) < 10, true);

	}

	protected <T> T getDataFromResponseUsingJsonPath(Response response, String jsonPath) {

		JsonPath jpath = response.jsonPath();
		return jpath.get(jsonPath);

	}
	
	protected LinkedTreeMap<String, Object>
}
