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

import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpParameter;
import ohlohj.internal.http.HttpResponse;

/**
 * representing unauthorized Request Token <br>
 * which is passed to the service provider when acquiring the authorized Access Token
 * @author cloudysunny14.
 */
public class OAuthRequestToken extends OAuthToken implements java.io.Serializable{
	private static final long serialVersionUID = -2310697711919107665L;

	public OAuthRequestToken(HttpResponse res, Configuration conf) {
		super(res, conf);
	}
	
	public OAuthRequestToken(String token, String tokenSecret) {
		super(token, tokenSecret);
	}
	
	/**
	 * Returns AuthorizeURL.
	 * @return the authorizeURL.
	 */
	public String getAuthorizeUrl() {
		HttpParameter httpParams[] = new HttpParameter[]{
			new HttpParameter("oauth_token", getToken()),
			new HttpParameter("oauth_callback", conf.getOAuthCallbackUrl())
		};
		return getOAuthAuthorizeUrl() +"?"+
			HttpParameter.encodeParameters(httpParams);
	}
}
