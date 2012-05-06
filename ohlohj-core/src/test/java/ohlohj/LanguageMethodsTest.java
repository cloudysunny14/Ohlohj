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

import ohlohj.Languages;
import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class LanguageMethodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateLanguagesSingle() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Languages lang = apis.createLanguages(53);
        assertThat(lang, is(notNullValue()));
        assertThat(lang.getId(), is(greaterThan(0)));
        assertThat(lang.getName(), is(notNullValue()));
        assertThat(lang.getNiceName(), is(notNullValue()));
        assertThat(lang.getCode(), is(greaterThan(0l)));
        assertThat(lang.getComments(), is(greaterThan(0l)));
        assertThat(lang.getBlanks(), is(greaterThan(0l)));
        assertThat(lang.getCommentRatio(), is(greaterThan(0f)));
        assertThat(lang.getProjects(), is(greaterThan(0)));
        assertThat(lang.getContributors(), is(greaterThan(0)));
        assertThat(lang.getCommits(), is(greaterThan(0l)));
	}
	
	@Test
	public void testCreateLanguagesWithCollectionParam() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	CollectionParameter cp = new CollectionParameter();
    	cp.setPage(2);
    	cp.setSort("code");
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<Languages> list = apis.createLanguages(cp);
        assertThat(list.size(), is(list.getItemsReturned()));
        assertThat(list.getFirstItemPosition(), is(10));
        Languages lang = list.get(0);
        assertThat(lang, is(notNullValue()));
        assertThat(lang.getId(), is(greaterThan(0)));
        assertThat(lang.getName(), is(notNullValue()));
        assertThat(lang.getNiceName(), is(notNullValue()));
        assertThat(lang.getCode(), is(greaterThan(0l)));
        assertThat(lang.getComments(), is(greaterThan(0l)));
        assertThat(lang.getBlanks(), is(greaterThan(0l)));
        assertThat(lang.getCommentRatio(), is(greaterThan(0f)));
        assertThat(lang.getProjects(), is(greaterThan(0)));
        assertThat(lang.getContributors(), is(greaterThan(0)));
        assertThat(lang.getCommits(), is(greaterThan(0l)));
        Languages lang1 = list.get(1);
        assertThat(lang.getCode(), is(greaterThan(lang1.getCode())));
	}
}
