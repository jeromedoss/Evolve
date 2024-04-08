package com.agilysys.qa.gridIron.utils;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import com.agilysys.qa.gridIron.utils.httpHelpers.HttpClient;
import com.agilysys.qa.gridIron.utils.httpHelpers.HttpClientResponse;
import com.agilysys.qa.gridIron.utils.httpHelpers.HttpMethod;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;


public class JiraZephyrClient {
	String url;
	String username;
	String password;
	String authorization;
	
	public JiraZephyrClient(String url, String username, String password){
		this(url,"Basic "+ Base64.getEncoder().encodeToString((username+":"+password).getBytes()));
	}
	
	public JiraZephyrClient(String url, String authorizationKey){		
		this.url = url;
		this.url = this.url.replaceAll("/$", "");
		this.authorization = authorizationKey;
	}
	
	private Map<String,String> getHeaders(){
		Map<String, String> headers = new LinkedHashMap<String, String>();		
		if(this.authorization.isEmpty() || this.authorization == null){
			this.authorization = "Basic "+ Base64.getEncoder().encodeToString((this.username+":"+this.password).getBytes());
		}
		headers.put("Authorization", this.authorization);
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public JSONObject getIssue(String issueKey) throws JSONException {
		String url = this.url+"/rest/api/2/issue/"+issueKey;
		HttpClient httpclient = getHttpClient();
		httpclient.setUrl(url);
		httpclient.setHttpMethod(HttpMethod.GET);
		HttpClientResponse response = httpclient.execute();		
		return getJsonFromString(response.getBody());
	}
	
	public JSONObject getTestCycleExecution(String cycleId) throws JSONException {
		String url = this.url+"/rest/zapi/latest/execution?cycleId="+cycleId+"&action=expand";
		HttpClient httpclient = getHttpClient();
		httpclient.setUrl(url);
		httpclient.setHttpMethod(HttpMethod.GET);
		HttpClientResponse response = httpclient.execute();
		return getJsonFromString(response.getBody());
	}
	
	public int getTestExecutionIdByNameFromCycleExecution(String name,JSONObject cycleExecutionList)
			throws JSONException {
		JSONArray executions = cycleExecutionList.getJSONArray("executions");
		
		if(null != executions){
			for(int i=0; i<executions.length(); i++){
				JSONObject execution = executions.getJSONObject(i);
				if(execution.getString("issueKey").contentEquals(name)){
					return execution.getInt("id");
				}
			}
		}
		return 0;
	}

	public JSONObject addTestToCycle(String testcaseId, String projectId, String versionId, String cycleId)
			throws JSONException {
		String url = this.url+"/rest/zapi/latest/execution";
		HttpClient httpclient = getHttpClient();
		httpclient.setUrl(url);
		httpclient.setHttpMethod(HttpMethod.POST);		
		JSONObject body = new JSONObject();
		body.put("issueId", testcaseId);
		body.put("versionId", versionId);
		body.put("cycleId", cycleId);
		body.put("projectId", projectId);		
		httpclient.setBody(body.toString());
		HttpClientResponse response = httpclient.execute();
		return getJsonFromString(response.getBody());
	}
	
	public JSONObject updateExecutionStatus(int executionId, String status) throws JSONException {
		String url = this.url+"/rest/zapi/latest/execution/"+executionId+"/execute";
		HttpClient httpclient = getHttpClient();
		httpclient.setUrl(url);
		httpclient.setHttpMethod(HttpMethod.PUT);		
		JSONObject body = new JSONObject();
		body.put("status", status);
		body.put("changeAssignee", false);				
		httpclient.setBody(body.toString());
		HttpClientResponse response = httpclient.execute();
		return getJsonFromString(response.getBody());
	}
	
	private HttpClient getHttpClient(){
		HttpClient httpclient = new HttpClient();		
		httpclient.setHeaders(getHeaders());		
		return httpclient;
	}
	
	private JSONObject getJsonFromString(String json) throws JSONException {
		if(!json.isEmpty() & json != null ){
			JSONObject object = new JSONObject(json);
			return object;
		}
		return null;
	}
}