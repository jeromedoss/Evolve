package com.agilysys.qa.gridIron.utils;

import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.agilysys.qa.clients.ClientFactory;
import com.agilysys.qa.helpers.ClientHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ClientPropertyDate {

	public LocalDate getPropertyDate(String tenantId, String propertyId) {

		RestAssured.baseURI = Endpoints.getBasePMSUrl();

//		String Path = "/auth-service/auth/tenants/" + tenantId + "/users/login";
//
//		Response response = given().contentType("application/json").header("Accept", ContentType.JSON)
//				.body("{\"username\":\"automation\",\"password\":\"TestingOnly1\"}").when().post(Path).then().extract()
//				.response();
//
//		String XToken = response.getBody().jsonPath().get("token");
//
////		System.out.println("XToken--" + XToken);
//
//		String path = "/property-service/tenants/" + tenantId + "/properties/" + propertyId + "/propertyDate";
//
//		response = given().contentType("application/json").header("Accept", ContentType.JSON).header("X-Token", XToken)
//				.when().get(path).then().extract().response();
//
//		String date = (response.asString().substring(1, (response.asString().length()) - 1));

		LocalDate localDate  = ClientHelper.call(() ->
			ClientFactory.getPropertyManagement().getPropertyDate(tenantId, propertyId));


		//		System.out.println(localDate);

		return localDate;

	}

}
