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

import java.util.List;

import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.Stack;
import ohlohj.StackEntry;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class StackMethodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateSizeFact() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Stack stack = apis.createStack(153760, 97861);
        assertThat(stack, is(notNullValue()));
        assertThat(stack.getId(), is(greaterThan(0l)));
        assertThat(stack.getTitle(), is(notNullValue()));
        assertThat(stack.getDescription(), is(notNullValue()));
        assertThat(stack.getUpdatedAt(), is(notNullValue()));
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        assertThat(stack.getStackEntries(), is(notNullValue()));
        assertThat(stack.getAccountId(), is(greaterThan(0l)));
        assertThat(stack.getAccount(), is(nullValue()));
        List<StackEntry> entries = stack.getStackEntries();
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        StackEntry se = entries.get(0);
        assertThat(se.getId(), is(greaterThan(0l)));
        assertThat(se.getStackId(), is(greaterThan(0l)));
        assertThat(se.getProjectId(), is(greaterThan(0l)));
        assertThat(se.getCreatedAt(), is(notNullValue()));
        assertThat(se.getProject(), is(notNullValue()));
	}
	
	@Test
	public void testCreateSizeFactDefault() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Stack stack = apis.createStack(153760);
        assertThat(stack, is(notNullValue()));
        assertThat(stack.getId(), is(greaterThan(0l)));
        assertThat(stack.getTitle(), is(notNullValue()));
        assertThat(stack.getDescription(), is(notNullValue()));
        assertThat(stack.getUpdatedAt(), is(notNullValue()));
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        assertThat(stack.getStackEntries(), is(notNullValue()));
        assertThat(stack.getAccountId(), is(greaterThan(0l)));
        assertThat(stack.getAccount(), is(nullValue()));
        List<StackEntry> entries = stack.getStackEntries();
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        StackEntry se = entries.get(0);
        assertThat(se.getId(), is(greaterThan(0l)));
        assertThat(se.getStackId(), is(greaterThan(0l)));
        assertThat(se.getProjectId(), is(greaterThan(0l)));
        assertThat(se.getCreatedAt(), is(notNullValue()));
        assertThat(se.getProject(), is(notNullValue()));
	}
	
	@Test
	public void testCreateSizeFactWithParam() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey("W7CRKVisSHVrraozB5koQ");
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        CollectionParameter cp = new CollectionParameter();
        cp.setPage(2);	
        cp.setSort("created_at");
        ResponseList<Stack> list = apis.createStackWithCollectionParmeter(503, cp);
        assertThat(list, is(notNullValue()));
        assertThat(list.getFirstItemPosition(), is(10));
        assertThat(list.getItemsReturned(), is(list.size()));
        Stack stack = list.get(0);
        assertThat(stack, is(notNullValue()));
        assertThat(stack.getId(), is(greaterThan(0l)));
        assertThat(stack.getTitle(), is(notNullValue()));
        assertThat(stack.getDescription(), is(notNullValue()));
        assertThat(stack.getUpdatedAt(), is(notNullValue()));
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        assertThat(stack.getStackEntries(), is(notNullValue()));
        assertThat(stack.getAccountId(), is(greaterThan(0l)));
        assertThat(stack.getAccount(), is(notNullValue()));
        List<StackEntry> entries = stack.getStackEntries();
        assertThat(stack.getProjectCount(), is(greaterThan(0)));
        StackEntry se = entries.get(0);
        assertThat(se.getId(), is(greaterThan(0l)));
        assertThat(se.getStackId(), is(greaterThan(0l)));
        assertThat(se.getProjectId(), is(greaterThan(0l)));
        assertThat(se.getCreatedAt(), is(notNullValue()));
        assertThat(se.getProject(), is(notNullValue()));
	}
}
