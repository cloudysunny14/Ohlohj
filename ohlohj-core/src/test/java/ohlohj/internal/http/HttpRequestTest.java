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

import static ohlohj.internal.http.RequestMethod.*;
import static org.junit.Assert.*;
import ohlohj.internal.http.HttpParameter;
import ohlohj.internal.http.HttpRequest;

import org.junit.Test;


public class HttpRequestTest {
    @Test
    public void testHttpRequest() {
        String url = "url";
        HttpParameter[] parameters = new HttpParameter[] {new HttpParameter("name", "name")};
        HttpRequest httpRequest = new HttpRequest(GET, url, parameters, null);
        assertEquals(httpRequest.getMethod(), GET);
        assertEquals(httpRequest.getParams().length, 0);
        assertEquals(httpRequest.getUrl(), "url?name=name");
    }
}
