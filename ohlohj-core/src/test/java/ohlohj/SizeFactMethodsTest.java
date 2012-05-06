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

import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.SizeFact;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class SizeFactMethodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateSizeFact() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<SizeFact> list = apis.createSizeFact(2323);
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(list.getItemsReturned()));
        SizeFact sf = list.get(0);
        assertThat(sf, is(notNullValue()));
        assertThat(sf.getMonth(), is(notNullValue()));
        assertThat(sf.getCode(), is(greaterThan(0l)));
        assertThat(sf.getComments(), is(greaterThan(0l)));
        assertThat(sf.getBlanks(), is(greaterThan(0l)));
        assertThat(sf.getCommentRatio(), is(greaterThan(0f)));
        assertThat(sf.getCommits(), is(greaterThan(0)));
        assertThat(sf.getManMonths(), is(greaterThan(0)));
	}
	
	@Test
	public void testCreateSizeFactWithAnalysisId() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<SizeFact> list = apis.createSizeFact(2323, 1762228);
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(list.getItemsReturned()));
        SizeFact sf = list.get(0);
        assertThat(sf, is(notNullValue()));
        assertThat(sf.getMonth(), is(notNullValue()));
        assertThat(sf.getCode(), is(greaterThan(0l)));
        assertThat(sf.getComments(), is(greaterThan(0l)));
        assertThat(sf.getBlanks(), is(greaterThan(0l)));
        assertThat(sf.getCommentRatio(), is(greaterThan(0f)));
        assertThat(sf.getCommits(), is(greaterThan(0)));
        assertThat(sf.getManMonths(), is(greaterThan(0)));
	}
}
