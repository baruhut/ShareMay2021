package com.jakartalabs.assignment;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import com.jakartalabs.assignment.utils.DataUtilsAssignment;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPITestAssignment {

	Faker faker = new Faker();

// 	protected RequestSpecification commonSpec = new RequestSpecBuilder()
// 			.setBaseUri(DataUtilsAssignment.getDataFromExcel("Config", "BaseAPIUrl")).setAccept("application/json")
// 			.setProxy("proxy.dnroot.net", 8000).build().log().all();
	
		protected RequestSpecification commonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtilsAssignment.getDataFromExcel("Config", "BaseAPIUrl")).setAccept("application/json").build().log().all();

	protected void verifyAPIStatusTimeAndHeader(Response response) {
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getTimeIn(TimeUnit.SECONDS) < 10, true);

	}

	protected <T> T getDataFromResponseUsingJsonPath(Response response, String jsonPath) {

		JsonPath jpath = response.jsonPath();
		return jpath.get(jsonPath);

	}

	/*
	 * protected LinkedTreeMap<String, Object> convertJsonToMap(String jsonString) {
	 * 
	 * Gson gson = new Gson(); LinkedTreeMap<String, Object> jsonMap = new
	 * LinkedTreeMap<String, Object>();
	 * 
	 * jsonMap = gson.fromJson(jsonString, jsonMap.getClass()); return jsonMap; }
	 */
}
