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

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;

public class MethodsTestBase {

	private static ThreadLocal<Map<String, SimpleDateFormat>> formatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };
    
    SimpleDateFormat sdf = null;
    
    protected Properties p = new Properties();
    protected String apiKey;
    protected String oAuthConsumerKey;
    protected String oAuthConsumerSec;
    protected String oAuthAccessToken;
    protected String oAuthAccessTokenSec;
    
    @Before
    public void setUp() throws IOException {
    	InputStream is = MethodsTestBase.class.getResourceAsStream("/test_configuration.properties");
		p.load(is);
		is.close();
		apiKey = p.getProperty("api_key");
		oAuthConsumerKey = p.getProperty("consumerkey");
		oAuthConsumerSec = p.getProperty("consumersec");
		oAuthAccessToken = p.getProperty("accesstoken");
		oAuthAccessTokenSec = p.getProperty("accesstokensec");
		
    	sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatMap.get().put("yyyy-MM-dd'T'HH:mm:ss'Z'", sdf);
    }
    
    @After
	public void tearDown() {
    }
}
