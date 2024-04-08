package com.agilysys.qa.gridIron.utils.httpHelpers;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;

public class HttpClient {

	private String url;
	private String body;
	private Map<String, String> headers;
	private String responseBody;
	private Headers responseHeaders;
	private String responseStatusText;
	private int responseStatus;
	private HttpMethod httpMethod;
		
    public HttpClient() {
    	
    }

    public HttpClientResponse execute() {
    	

        if (StringUtils.isEmpty(this.url)) {
            throw new IllegalArgumentException("Url can't be empty");
        }
        try {        	
        	HttpResponse<String> response = createHttpRequest()                    
                    .asString();
        	
        	responseBody = response.getBody();
        	responseHeaders = response.getHeaders();        	
        	responseStatusText = response.getStatusText();
        	responseStatus = response.getStatus();

            return HttpClientResponse.success(response.getStatus(), response.getBody());
        } catch (Exception e) {
            return HttpClientResponse.error(e);
        }
    }
    
    public HttpRequest createHttpRequest() {
        String url = this.getUrl();
                
        String body = this.getBody();
        if(this.httpMethod == null){
        	return null;
        }
        HttpRequest request = null;

        switch (httpMethod) {
            case GET:
                request = Unirest.get(url);
                break;
            case POST:
                request = Unirest.post(url)
                        .body(body)
                        .getHttpRequest();
                break;
            case PUT:
                request = Unirest.put(url)
                        .body(body)
                        .getHttpRequest();
                break;
            case PATCH:
                request = Unirest.patch(url)
                        .body(body)
                        .getHttpRequest();
                break;
            case DELETE:
                request = Unirest.delete(url)
                        .body(body)
                        .getHttpRequest();
                break;
            case HEAD:
                request = Unirest.head(url);
                break;
            case OPTIONS:
                request = Unirest.options(url);
                break;
        }
        if(headers != null){
        	request.headers(this.headers);
        }        
        return request;
    }
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public Headers getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Headers responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getResponseStatusText() {
		return responseStatusText;
	}

	public void setResponseStatusText(String responseStatusText) {
		this.responseStatusText = responseStatusText;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	
}
