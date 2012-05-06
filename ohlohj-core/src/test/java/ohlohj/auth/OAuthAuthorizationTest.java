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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import ohlohj.Account;
import ohlohj.MethodsTestBase;
import ohlohj.OhlohAPIImpl;
import ohlohj.OhlohException;
import ohlohj.apis.OhlohAPI;
import ohlohj.auth.OAuthAccessToken;
import ohlohj.auth.OAuthAuthorization;
import ohlohj.auth.OAuthRequestToken;
import ohlohj.auth.OAuthToken;
import ohlohj.conf.Configuration;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class OAuthAuthorizationTest extends MethodsTestBase{

	@Test
	public void testOAuth() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(oAuthConsumerKey)
		.setOAuthConsumerSecret(oAuthConsumerSec)
		.setOAuthCallbackUrl("http://callback");
		Configuration conf = cb.build();
		OAuthAuthorization auth = new OAuthAuthorization(conf);
		OAuthRequestToken reqToken = null;
		try {
			reqToken = auth.getOAuthRequestToken();
		} catch (OhlohException ignore) {
		}
		assertThat(reqToken.getToken(), is(notNullValue()));
		assertThat(reqToken.getTokenSecret(), is(notNullValue()));
		String url = reqToken.getAuthorizeUrl();
		assertThat(url, is( conf.getOAuthAuthorizeUrl() 
				+ "?oauth_token="+ reqToken.getToken()
				+ "&"
				+ "oauth_callback=http%3A%2F%2Fcallback"));
		auth.setOAuthToken(reqToken);
		OAuthAccessToken accToken = null;
		try {
			accToken = auth.getOAuthAccessToken();
		} catch (OhlohException ignore) {
			//Because that was not Authorized.
		}
		assertThat(accToken, is(nullValue()));
		//If user authorizes requestToken.
		//assertThat(reqToken.getToken(), is(notNullValue()));
		//assertThat(reqToken.getTokenSecret(), is(notNullValue()));
	}
	
	@Test
	public void testOAuthSearch() throws Exception{
		OAuthToken oauth = new OAuthAccessToken(oAuthAccessToken,oAuthAccessTokenSec);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(oAuthConsumerKey)
		.setOAuthConsumerSecret(oAuthConsumerSec);
		OAuthAuthorization oAuth = new OAuthAuthorization(cb.build());
		oAuth.setOAuthToken(oauth);
		OhlohAPI apis = new OhlohAPIImpl(oAuth);
        Account account = apis.createAccount(153760);
        assertThat(account, is(notNullValue()));
	}
}
