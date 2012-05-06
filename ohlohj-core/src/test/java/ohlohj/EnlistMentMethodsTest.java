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

import ohlohj.EnlistMent;
import ohlohj.OhlohAPIImpl;
import ohlohj.Repository;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class EnlistMentMethodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateContributorFact() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        EnlistMent em = apis.createEnlistMent(2323, 5002);
        assertThat(em, is(notNullValue()));
        assertThat(em.getId(), is(greaterThan(0l)));
        assertThat(em.getProjectId(), is(greaterThan(0l)));
        assertThat(em.getRepositoryId(), is(greaterThan(0l)));
        assertThat(em.getRepository(), is(notNullValue()));
        List<Repository> repositories = em.getRepository();
        assertThat(repositories.size(), is(greaterThan(0)));
        Repository repo = repositories.get(0);
        assertThat(repo, is(notNullValue()));
        assertThat(repo.getId(), is(greaterThan(0l)));
        assertThat(repo.getType(), is(notNullValue()));
        assertThat(repo.getUrl(), is(notNullValue()));
        assertThat(repo.getModuleName(), is(notNullValue()));
        assertThat(repo.getUserName(), is(notNullValue()));
        assertThat(repo.getPassword(), is(notNullValue()));
        assertThat(repo.getLoggedAt(), is(notNullValue()));
        assertThat(repo.getCommits(), is(greaterThan(0)));
        assertThat(repo.getOhlohJobStatus(), is(notNullValue()));
	}
	
	@Test
	public void testCreateContributorFacts() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<EnlistMent> list = apis.createEnlistMent(2323, CollectionParameter.NULL_PARAMETER);
        assertThat(list.size(), is(list.getItemsReturned()));
        EnlistMent em = list.get(0);
        assertThat(em, is(notNullValue()));
        assertThat(em.getId(), is(greaterThan(0l)));
        assertThat(em.getProjectId(), is(greaterThan(0l)));
        assertThat(em.getRepositoryId(), is(greaterThan(0l)));
        assertThat(em.getRepository(), is(notNullValue()));
        List<Repository> repositories = em.getRepository();
        assertThat(repositories.size(), is(greaterThan(0)));
        Repository repo = repositories.get(0);
        assertThat(repo, is(notNullValue()));
        assertThat(repo.getId(), is(greaterThan(0l)));
        assertThat(repo.getType(), is(notNullValue()));
        assertThat(repo.getUrl(), is(notNullValue()));
        assertThat(repo.getModuleName(), is(notNullValue()));
        assertThat(repo.getUserName(), is(notNullValue()));
        assertThat(repo.getPassword(), is(notNullValue()));
        assertThat(repo.getLoggedAt(), is(notNullValue()));
        assertThat(repo.getCommits(), is(greaterThan(0)));
        assertThat(repo.getOhlohJobStatus(), is(notNullValue()));
	}
	
	
	
}
