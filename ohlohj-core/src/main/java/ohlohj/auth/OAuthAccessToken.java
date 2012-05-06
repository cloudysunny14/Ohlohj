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
import ohlohj.internal.http.HttpResponse;

/**
 * Representing authorized Access Token which is passed to the service <br>
 * provider in order to access protected resources.<br>
 * the token and token secret can be stored into some persistent <br>
 * stores such as file system or RDBMS for the further accesses.
 *
 * @author cloudysuuny14
 */
public class OAuthAccessToken extends OAuthToken implements java.io.Serializable{
	private static final long serialVersionUID = 3362490909361816854L;

	public OAuthAccessToken(HttpResponse res, Configuration conf) {
		super(res, conf);
	}

	public OAuthAccessToken(String token, String tokenSecret) {
		super(token, tokenSecret);
	}
}
