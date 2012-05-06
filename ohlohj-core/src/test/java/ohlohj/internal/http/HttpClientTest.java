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

import ohlohj.conf.ConfigurationBuilder;
import ohlohj.internal.http.HttpClient;
import ohlohj.internal.http.HttpClientImpl;
import ohlohj.internal.http.HttpParameter;
import ohlohj.internal.http.HttpRequest;
import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.http.HttpResponseCode;

import org.junit.Test;

import static ohlohj.internal.http.RequestMethod.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class HttpClientTest implements HttpResponseCode{

    @Test
    public void testHttpClient() throws Exception{
        HttpParameter[] params = new HttpParameter []{ new HttpParameter("api_key", "W7CRKVisSHVrraozB5koQ")};
        String url = "http://www.ohloh.net/projects/1.xml";
        HttpRequest httpRequest = new HttpRequest(GET, url, params, null);
        ConfigurationBuilder conf = new ConfigurationBuilder();
        conf.setUserAgent("Mozilla/5.0 (Macintosh; U; PPC Mac OS X; ja-jp) AppleWebKit/85.7 (KHTML, like Gecko) Safari/85.6");
        HttpClient http = new HttpClientImpl(conf.build());
        HttpResponse response = http.request(httpRequest);
        assertThat(response.getStatusCode(), is(OK));
        assertThat(response, is(notNullValue()));
        assertThat(response.asString(), is(notNullValue()));
    }
}
