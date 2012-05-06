/*
 * Copyright 2012 cloudysunny14.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package ohlohj.internal.http;

import java.util.Arrays;

import ohlohj.auth.OAuthAuthorization;

/**
 * 
 * @author cloudysunny14
 *
 */
public class HttpRequest implements java.io.Serializable{
    
	private static final long serialVersionUID = 6862256023749552077L;
	private final RequestMethod method;
    private String url;
    private final HttpParameter[] parameters;
    private final OAuthAuthorization oAuth;
    private boolean isAuthorization;
    private static final HttpParameter[] NULL_PARAMETERS = new HttpParameter[0];
    
    public HttpRequest(RequestMethod method, String url, 
    		HttpParameter[] parameters, OAuthAuthorization oAuth) {
    	this.method = method;
        if (method != RequestMethod.POST && parameters != null && parameters.length != 0) {
            this.url = url + "?" + HttpParameter.encodeParameters(parameters);
            this.parameters = NULL_PARAMETERS;
        } else {
            this.url = url;
            this.parameters = parameters;
        }
        this.oAuth = oAuth;
    }
    
    
    public RequestMethod getMethod() {
        return method;
    }
    
    public synchronized String getUrl() {
        return url;
    }
    
    public synchronized void setUrl(String url) {
    	this.url = url;
    }
    
    public HttpParameter[] getParams() {
        return parameters;
    }
    
    public OAuthAuthorization getOAuth() {
    	return oAuth;
    }
    
    public boolean isAuthorization() {
    	return isAuthorization;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpRequest that = (HttpRequest) o;
        
        if (!Arrays.equals(parameters, that.parameters)) return false;
        if (method != null ? !method.equals(that.method) : that.method != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;
        if (oAuth != null ? !oAuth.equals(that.oAuth) : that.oAuth != null)
        	return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (parameters != null ? Arrays.hashCode(parameters) : 0);
        //To do:result = 31 * result + (oAuth != null ? Arrays.hashCode(oAuth) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestMethod=" + method +
                ", url='" + url + '\'' +
                ", postParams=" + (parameters == null ? null : Arrays.asList(parameters)) +
                '}';
    }
    
}
