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

import ohlohj.internal.http.HttpParameter;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HttpParameterTest {

    @Test
    public void testParameter() {
        HttpParameter[] params = 
                new HttpParameter[]{new HttpParameter("name", "name"),
                    new HttpParameter("query", "query")};
        assertThat(params, is(notNullValue()));
        assertEquals("name=name&query=query", HttpParameter.encodeParameters(params));
    }
}
