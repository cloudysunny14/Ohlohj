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
package ohlohj;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ohlohj.auth.OAuthAuthorization;
import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpClient;
import ohlohj.internal.http.HttpParameter;

/**
 * 
 * @author cloudysunny14
 *
 */
public abstract class OhlohAPIBase
{
	private static final String HTTPCLIENT = "ohlohj.internal.http.HttpClientImpl";
	private static final String HTTPCLIENT_APPENGINE = "ohlohj.appengine.internal.http.HttpClientAppEngineImpl";
	
	protected OAuthAuthorization oAuth;
	protected HttpParameter[] keyParameter;
	protected HttpClient http;
	protected OhlohAPIFactory factory;
	
	protected OhlohAPIBase(Configuration conf) {
		http = createHttpClient(conf);
		factory = createFactory(conf);
		keyParameter = conf.getKeyParam();
	}
	
	public OhlohAPIBase setOAuth(OAuthAuthorization oAuth) {
		this.oAuth = oAuth;
		return this;
	}
	
	private static HttpClient createHttpClient(Configuration conf) {
		Class<?> cl = null;
		HttpClient httpClient = null;
		if(conf.isGAE()) {
			try {
				cl = Class.forName(HTTPCLIENT_APPENGINE);
			} catch (ClassNotFoundException ignore){
			}
		}
		else {
			try {
				cl = Class.forName(HTTPCLIENT);
			} catch (ClassNotFoundException ignore){
			}
		}
		@SuppressWarnings("rawtypes")
		Constructor constructor = null;
		try {
			constructor = cl.getConstructor(Configuration.class);
        } catch (NoSuchMethodException nsme) {
            throw new AssertionError(nsme);
        }
		try {
			httpClient = (HttpClient) constructor.newInstance(conf);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        }
		return httpClient;
	}
	
	private static OhlohAPIFactory createFactory(Configuration conf) {
		return new OhlohAPIFactoryImpl();
	}
	
	protected HttpParameter[] mergeParameters(HttpParameter[] params1, HttpParameter[] params2) {
        if (params1 != null && params2 != null) {
            HttpParameter[] params = new HttpParameter[params1.length + params2.length];
            System.arraycopy(params1, 0, params, 0, params1.length);
            System.arraycopy(params2, 0, params, params1.length, params2.length);
            return params;
        }
        if (null == params1 && null == params2) {
            return new HttpParameter[0];
        }
        if (params1 != null) {
            return params1;
        } else {
            return params2;
        }
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OhlohAPIBase that = (OhlohAPIBase) o;

        if (http != that.http) return false;
        if (keyParameter != that.keyParameter) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (http != null ? http.hashCode() : 0);
        result = 31 * result + (keyParameter != null ? keyParameter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OhlohAPIBase{" +
                "http" + http +
                ", keyParameter='" + keyParameter +
                '}';
    }
}
