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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import ohlohj.Factoid;
import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class FactoidMehodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateFactoidTest() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Factoid factoid = apis.createFactoid(2323, 4387955);
        assertThat(factoid, is(notNullValue()));
        assertThat(factoid.getId(), is(greaterThan(0l)));
        assertThat(factoid.getAnalysisId(), is(greaterThan(0l)));
        assertThat(factoid.getType(), is(notNullValue()));
        assertThat(factoid.getDescription(), is(notNullValue()));
        assertThat(factoid.getSeverity(), is(greaterThan(0)));
	}
	
	@Test
	public void testCreateFactoidListTest() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<Factoid> list = apis.createFactoid(2323);
        assertThat(list.size(), is(list.getItemsReturned()));
        Factoid factoid = list.get(0);
        assertThat(factoid, is(notNullValue()));
        assertThat(factoid.getId(), is(greaterThan(0l)));
        assertThat(factoid.getAnalysisId(), is(greaterThan(0l)));
        assertThat(factoid.getType(), is(notNullValue()));
        assertThat(factoid.getDescription(), is(notNullValue()));
        assertThat(factoid.getSeverity(), is(greaterThan(0)));
	}
	
	
}
