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
package ohlohj.auth;

import javax.crypto.spec.SecretKeySpec;

import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpResponse;
import ohlohj.utils.StringUtils;

/**
 * 
 * @author cloudysunny14
 *
 */
public abstract class OAuthToken implements java.io.Serializable {
	private static final long serialVersionUID = -427220543186568510L;
	private SecretKeySpec spec;
	protected String tokenSecret;
	protected String token;
	protected String[] responseString;
	protected Configuration conf;
	
	public OAuthToken(HttpResponse res, Configuration conf) {
		if(res!=null) {
			responseString = StringUtils.split(res.asString(), "&");
			tokenSecret = getParameter("oauth_token_secret");
	        token = getParameter("oauth_token");
		}
		this.conf = conf;
	}
	
	public OAuthToken(String token, String tokenSecret) {
		this.token = token;
		this.tokenSecret = tokenSecret;
	}
	
	public String getTokenSecret() {
		return tokenSecret;
	}

	public String getToken() {
		return token;
	}

	public SecretKeySpec getSecretKeySpec() {
		return spec;
	}

	public void setSecretKeySpec(SecretKeySpec spec) {
		this.spec = spec;
	}
	
	public String getOAuthAuthorizeUrl() {
		return conf.getOAuthAuthorizeUrl();
	}
	
	public String getParameter(String parameter) {
        String value = null;
        for (String str : responseString) {
            if (str.startsWith(parameter + '=')) {
                value = StringUtils.split(str, "=")[1].trim();
                break;
            }
        }
        return value;
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthToken)) return false;

        OAuthToken that = (OAuthToken) o;

        if (!token.equals(that.token)) return false;
        if (!tokenSecret.equals(that.tokenSecret)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + tokenSecret.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OAuthToken{" +
                "token='" + token +
                ", tokenSecret='" + tokenSecret + 
                '}';
    }
}
