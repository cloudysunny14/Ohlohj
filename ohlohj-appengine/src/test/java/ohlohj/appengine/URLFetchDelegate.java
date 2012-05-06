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
package ohlohj.appengine;

import java.util.List;
import java.util.concurrent.Future;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.*;

/**
 * @author cloudysunny14
 *
 */
public class URLFetchDelegate implements Delegate<Environment>{
	
	protected static final String URLFETCH_SERVICE = "urlfetch";
	protected static final String FETCH_METHOD = "Fetch";
	
	public ApiProxy.Delegate<Environment> apiProxyLocal;
	
	public URLFetchDelegate(ApiProxy.Delegate<Environment> apiProxyLocal) {
		this.apiProxyLocal = apiProxyLocal;
	}
	
	public byte[] makeSyncCall(Environment env, String service, String method,
			byte[] request) throws ApiProxyException {
		return apiProxyLocal.makeSyncCall(env, service, method, request);
	}

	public void flushLogs(Environment env) {
		apiProxyLocal.flushLogs(env);
	}

	public List<Thread> getRequestThreads(Environment env) {
		return apiProxyLocal.getRequestThreads(env);
	}

	public void log(Environment env, LogRecord logRecord) {
		apiProxyLocal.log(env, logRecord);
	}

	public Future<byte[]> makeAsyncCall(Environment env, String service,
			String method, byte[] request, ApiConfig config) {
		return apiProxyLocal.makeAsyncCall(
	                    env,
	                    service,
	                    method,
	                    request,
	                    config);
	}
}
