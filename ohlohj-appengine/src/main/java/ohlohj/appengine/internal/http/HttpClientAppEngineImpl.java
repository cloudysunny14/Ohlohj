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

import static ohlohj.internal.http.RequestMethod.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.appengine.api.urlfetch.FetchOptions.Builder;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import ohlohj.OhlohException;
import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpClient;
import ohlohj.internal.http.HttpParameter;
import ohlohj.internal.http.HttpRequest;
import ohlohj.internal.http.HttpResponse;

/**
 * @author cloudysunny14
 *
 */
public class HttpClientAppEngineImpl implements HttpClient{
	
	private Configuration conf;
    
	/**
	 * The constructor if HttpClient for AppEngine.
	 * @param conf
	 */
    public HttpClientAppEngineImpl(Configuration conf) {
        this.conf = conf;
    }
    
    /**
     * HTTP Request 
     * @param req
     * @return HttpResponse
     * 
     */
    public HttpResponse request(HttpRequest req) throws OhlohException {
    	HTTPRequest request;
        try {
            request = new HTTPRequest(new URL(req.getUrl())
                    , HTTPMethod.valueOf(req.getMethod().name())
                    , Builder.disallowTruncate().setDeadline(conf.getHttpReadTimeout() / 1000D)
            );
        } catch (MalformedURLException e) {
            throw new OhlohException(e);
        }
        
        HttpResponse res = null;
        ByteArrayOutputStream os;
        try {
        	setHeaders(req, request);
        	if (req.getMethod() == POST) {
        		request.setHeader(new HTTPHeader(
                        "Content-Type",
                        "application/x-www-form-urlencoded"
                ));
                String postParam = HttpParameter.encodeParameters(req.getParams());
                byte[] bytes = postParam.getBytes("UTF-8");
                request.setHeader(new HTTPHeader("Content-Length",
                        Integer.toString(bytes.length)));
                os = new ByteArrayOutputStream();
                os.write(bytes);
                request.setPayload(os.toByteArray());
        	}
        	URLFetchService service = URLFetchServiceFactory.getURLFetchService();
            return new HttpResponseAppEngineImpl(service.fetchAsync(request));
        } catch (IOException ioe) {
        	throw new OhlohException(ioe.getMessage(), res);
        }
    }
    
    private void setHeaders(HttpRequest req, HTTPRequest request) {
    	 String authorizationHeader;
         if (req.getOAuth() != null && (authorizationHeader = req.getOAuth().getAuthorizationHeader(req)) != null) {
             request.setHeader(new HTTPHeader("Authorization", authorizationHeader));
         }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpClientAppEngineImpl that = (HttpClientAppEngineImpl) o;
        if (!conf.equals(that.conf)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return conf.hashCode();
    }

    @Override
    public String toString() {
        return "HttpClientAppEngineImpl{" +
                "conf=" + conf +
                '}';
    }
}
