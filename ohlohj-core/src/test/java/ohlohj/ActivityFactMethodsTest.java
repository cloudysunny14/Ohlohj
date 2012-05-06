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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

import ohlohj.ActivityFact;
import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class ActivityFactMethodsTest extends MethodsTestBase{
	
    @Test
    public void createActivityFactTest() throws Exception{
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<ActivityFact> list = apis.createActivityFact(2323, 1762228);
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(list.getItemsReturned()));
        ActivityFact af = list.get(0);
        assertThat(af.getMonth(), is(notNullValue()));
        //assertThat(af.getMonth(), is(sdf.parse("2004-09-01T00:00:00Z")));
        assertThat(af.getCodeAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCodeRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCommentsAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCommentsRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getBlanksAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getBlanksRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getContributors(), is(greaterThanOrEqualTo(0)));
    }
    
    @Test
    public void createActivityFactTestWithProjectId() throws Exception {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<ActivityFact> list = apis.createActivityFact(2323);
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(list.getItemsReturned()));
        ActivityFact af = list.get(0);
        assertThat(af.getMonth(), is(notNullValue()));
        //assertThat(af.getMonth(), is(sdf.parse("2004-09-01T00:00:00Z")));
        assertThat(af.getCodeAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCodeRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCommentsAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getCommentsRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getBlanksAdded(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getBlanksRemoved(), is(greaterThanOrEqualTo(0)));
        assertThat(af.getContributors(), is(greaterThanOrEqualTo(0)));
    }
}
