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
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

import ohlohj.Kudo;
import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class KudoMethodsTest extends MethodsTestBase{

	@Test
	public void testCreateKudoSentbyId() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<Kudo> list = apis.createKudoSent(153760);
        assertThat(list.size(), is(list.getItemsReturned()));
        Kudo kudo = list.get(0);
        assertThat(kudo, is(notNullValue()));
        assertThat(kudo.getSenderAccountId(), is(greaterThan(0l)));
        assertThat(kudo.getSenderAccountName(), is(notNullValue()));
        assertThat(kudo.getProjectId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getProjectName(), is(notNullValue()));
        assertThat(kudo.getContoributorId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getContoributorName(), is(notNullValue()));
        assertThat(kudo.getReceiverAccountId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getReceiverAccountName(), is(notNullValue()));
        assertThat(kudo.getCreatedAt(), is(notNullValue()));
	}
	@Test
	public void testCreateKudoById() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<Kudo> list = apis.createKudo(42618);
        assertThat(list.size(), is(list.getItemsReturned()));
        Kudo kudo = list.get(0);
        assertThat(kudo, is(notNullValue()));
        assertThat(kudo.getSenderAccountId(), is(greaterThan(0l)));
        assertThat(kudo.getSenderAccountName(), is(notNullValue()));
        assertThat(kudo.getProjectId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getProjectName(), is(notNullValue()));
        assertThat(kudo.getContoributorId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getContoributorName(), is(notNullValue()));
        assertThat(kudo.getReceiverAccountId(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudo.getReceiverAccountName(), is(notNullValue()));
        assertThat(kudo.getCreatedAt(), is(notNullValue()));
	}
}
