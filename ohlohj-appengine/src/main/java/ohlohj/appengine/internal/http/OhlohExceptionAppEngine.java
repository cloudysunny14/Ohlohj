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
package ohlohj.appengine.internal.http;

import java.util.HashMap;
import java.util.Map;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPResponse;

import ohlohj.OhlohException;

/**
 * @author cloudysunny14
 *
 */
public class OhlohExceptionAppEngine extends OhlohException{
	private static final long serialVersionUID = -3957426575476860581L;
	
	private int statusCode = -1;
	private HTTPResponse response;
	/**
	 * the constructor of OhlohException for AppEngine.
	 * @param cause
	 */
	public OhlohExceptionAppEngine(Exception cause) {
		super(cause);
	}
	
	/**
	 * the constructor of OhlohException for AppEngine
	 * with the message and HTTPResponse.
	 * @param message
	 * @param response
	 */
	public OhlohExceptionAppEngine(String message, HTTPResponse response) {
		super(message);
		this.response = response;
		this.statusCode = response.getResponseCode();
	}
	
	public Map<String, String> getResponseHeader() {
        Map<String, String> headers = new HashMap<String, String>();
        for (HTTPHeader header : response.getHeaders()) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }
	
	@Override
	public String getResponseHeader(String name) {
        return getResponseHeader().get(name);
    }
	
	@Override
	public int getStatusCode() {
        return this.statusCode;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		result = prime * result + statusCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OhlohExceptionAppEngine other = (OhlohExceptionAppEngine) obj;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (statusCode != other.statusCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OhlohExceptionAppEngine [statusCode=" + statusCode
				+ ", response=" + response + "]";
	}
}
