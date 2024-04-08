package com.agilysys.qa.gridIron.utils;

import com.agilysys.insertanator.objectStores.MasterObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 * *Author - Harish Baskaran - 2018
 */
public class RestUtilities {

	public void OnDuty(MasterObject masterObject) {

		RestAssured.baseURI = Endpoints.getBasePMSUrl();

		String Path = "auth-service/auth/tenants/" + masterObject.getProperty().getTenantId() + "/users/login";

		String body1 = "{\"username\": \"" + Endpoints.getUserName() + "\", \"password\":\"" + Endpoints.getPassword()
				+ "\"}";
		Response response = RestAssured.given().contentType("application/json").header("Accept", ContentType.JSON)
				.body(body1).when().post(Path).then().extract().response();

		String XToken = response.getBody().jsonPath().get("token");

		String Path1 = "servicerequest-service/tenants/" + masterObject.getProperty().getTenantId()
				+ "/staffmembers/housekeeping";

		Response response1 = RestAssured.given().contentType("application/json").header("Accept", ContentType.JSON)
				.header("X-Token", XToken).when().get(Path1).then().extract().response();

		int ids = response1.getBody().jsonPath().getList("array").size();

		for (int i = 0; i < ids; i++) {
			String StaffId = response1.getBody().jsonPath().getString("id[" + i + "]");

			String Path2 = "/servicerequest-service/tenants/" + masterObject.getProperty().getTenantId()
					+ "/staffmembers/housekeeping/" + StaffId + "/onDuty/properties/"
					+ masterObject.getProperty().getPropertyId();

			String body2 = "{\"id\":\"" + StaffId + "\",\"propertyId\":\"" + masterObject.getProperty().getPropertyId()
					+ "\"}";

			Response response2 = RestAssured.given().contentType("application/json").header("Accept", ContentType.JSON)
					.header("X-Token", XToken).body(body2).when().put(Path2).then().statusCode(200).extract()
					.response();

			System.out.println("Rest - " + response2.getBody().jsonPath().get("lastName") + ", "
					+ response2.getBody().jsonPath().get("firstName"));

		}
	}
}
