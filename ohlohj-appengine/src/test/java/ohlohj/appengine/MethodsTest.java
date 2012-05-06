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
package ohlohj.appengine;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import ohlohj.Account;
import ohlohj.ActivityFact;
import ohlohj.Analysis;
import ohlohj.ContributorFact;
import ohlohj.EnlistMent;
import ohlohj.Factoid;
import ohlohj.Kudo;
import ohlohj.Languages;
import ohlohj.OhlohException;
import ohlohj.Project;
import ohlohj.ResponseList;
import ohlohj.SizeFact;
import ohlohj.Stack;
import ohlohj.apis.CollectionParameter;
import ohlohj.appengine.internal.http.AsyncResponse;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

/**
 * @author cloudysunny14
 *
 */
public class MethodsTest extends MethodsTestBaseAppengine{
	
	/**
	 * using low level API
	 * @throws OhlohException 
	 */
	@Test
	public void testMethodsAsync() throws OhlohException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setApiKey(apiKey);
        FetchAsyncOhloh apis = new FetchAsyncOhlohImpl(cb.build());
        //AccountMethods
        AsyncResponse<Account> accountAsync = apis.createAccount(22222);
        AsyncResponse<Account> accountEmail = apis.createAccountWithEmail("cloudysunny@gmail.com");
        AsyncResponse<ResponseList<Account>> aAccountList = apis.createAccountWithQuery(CollectionParameter.NULL_PARAMETER);
        Account account = accountAsync.get();
        assertThat(account, is(notNullValue()));
        assertThat(accountEmail.get(), is(notNullValue()));
        ResponseList<Account> accountList = aAccountList.get();
        assertThat(accountList, is(notNullValue()));
        assertThat(accountList.size(), is(accountList.getItemsReturned()));
        assertThat(accountList.get(0), is(notNullValue()));
        //ActivityFactMehotds
        AsyncResponse<ResponseList<ActivityFact>> aAFList = apis.createActivityFact(2323);
        AsyncResponse<ResponseList<ActivityFact>> aAFListTwo = apis.createActivityFact(2323, 1762228);
        ResponseList<ActivityFact> activityFactList = aAFList.get();
        assertThat(activityFactList, is(notNullValue()));
        assertThat(activityFactList.size(), is(activityFactList.getItemsReturned()));
        assertThat(activityFactList.get(0), is(notNullValue()));
        ResponseList<ActivityFact> activityFactListTwo = aAFListTwo.get();
        assertThat(activityFactListTwo, is(notNullValue()));
        assertThat(activityFactListTwo.size(), is(activityFactListTwo.getItemsReturned()));
        assertThat(activityFactListTwo.get(0), is(notNullValue()));
        //AnalysisMethods
        AsyncResponse<Analysis> analysisAsync = apis.createAnalysis(2323, 1762228);
        AsyncResponse<Analysis> analysisAsyncTwo = apis.createAnalysis(2323);
        assertThat(analysisAsync.get(), is(notNullValue()));
        assertThat(analysisAsyncTwo.get(), is(notNullValue()));
        //ContributorFactMethods
        AsyncResponse<ContributorFact> cfAsync = apis.createContributorFact(2323, 9979356525106l);
        AsyncResponse<ResponseList<ContributorFact>> cfListAsync = apis.createContributorFact(2323, CollectionParameter.NULL_PARAMETER);
        assertThat(cfAsync.get(), is(notNullValue()));
        ResponseList<ContributorFact> cfList = cfListAsync.get();
        assertThat(cfList, is(notNullValue()));
        assertThat(cfList.size(), is(cfList.getItemsReturned()));
        assertThat(cfList.get(0), is(notNullValue()));
        //EnlistMentMethods
        AsyncResponse<EnlistMent> emAsync = apis.createEnlistMent(2323, 5002);
        AsyncResponse<ResponseList<EnlistMent>> emListAsync = apis.createEnlistMent(2323, CollectionParameter.NULL_PARAMETER);
        assertThat(emAsync.get(), is(notNullValue()));
        ResponseList<EnlistMent> emList = emListAsync.get();
        assertThat(emList, is(notNullValue()));
        assertThat(emList.size(), is(emList.getItemsReturned()));
        assertThat(emList.get(0), is(notNullValue()));
        //FactoidMethods
        AsyncResponse<Factoid> factoid = apis.createFactoid(2323, 4387955);
        AsyncResponse<ResponseList<Factoid>> factoidListAsync = apis.createFactoid(2323);
        assertThat(factoid.get(), is(notNullValue()));
        ResponseList<Factoid> factoidList = factoidListAsync.get();
        assertThat(factoidList, is(notNullValue()));
        assertThat(factoidList.size(), is(factoidList.getItemsReturned()));
        assertThat(factoidList.get(0), is(notNullValue()));
        //KudoMethods
        AsyncResponse<ResponseList<Kudo>> kudoListAsync = apis.createKudoSent(153760);
        AsyncResponse<ResponseList<Kudo>> kudoListAsyncTwo = apis.createKudo(42618);
        ResponseList<Kudo> kudoList = kudoListAsync.get();
        assertThat(kudoList, is(notNullValue()));
        assertThat(kudoList.size(), is(kudoList.getItemsReturned()));
        assertThat(kudoList.get(0), is(notNullValue()));
        ResponseList<Kudo> kudoListTwo = kudoListAsyncTwo.get();
        assertThat(kudoListTwo, is(notNullValue()));
        assertThat(kudoListTwo.size(), is(kudoListTwo.getItemsReturned()));
        assertThat(kudoListTwo.get(0), is(notNullValue()));
        //LanguageMethods
        AsyncResponse<Languages> langAsync = apis.createLanguages(53);
        CollectionParameter cp = new CollectionParameter();
    	cp.setPage(2);
    	cp.setSort("code");
    	AsyncResponse<ResponseList<Languages>> langListAsync = apis.createLanguages(cp);
    	assertThat(langAsync.get(), is(notNullValue()));
    	ResponseList<Languages> langList = langListAsync.get();
        assertThat(langList, is(notNullValue()));
        assertThat(langList.size(), is(langList.getItemsReturned()));
        assertThat(langList.get(0), is(notNullValue()));
        //ProjectMethods
        AsyncResponse<Project> projectAsync = apis.createProject(503);
        CollectionParameter cpProject = new CollectionParameter();
        cp.setQuery("SimpleTest");
        AsyncResponse<ResponseList<Project>> projListAsync = apis.createProject(cpProject);
        assertThat(projectAsync.get(), is(notNullValue()));
        ResponseList<Project> projList = projListAsync.get();
        assertThat(projList, is(notNullValue()));
        assertThat(projList.size(), is(projList.getItemsReturned()));
        assertThat(projList.get(0), is(notNullValue()));
        //SizeFactMethods
        AsyncResponse<ResponseList<SizeFact>> sfListAsync = apis.createSizeFact(2323);
        AsyncResponse<ResponseList<SizeFact>> sfListAsyncTwo = apis.createSizeFact(2323, 1762228);
        ResponseList<SizeFact> sfList = sfListAsync.get();
        assertThat(sfList, is(notNullValue()));
        assertThat(sfList.size(), is(sfList.getItemsReturned()));
        assertThat(sfList.get(0), is(notNullValue()));
        ResponseList<SizeFact> sfListTwo = sfListAsyncTwo.get();
        assertThat(sfListTwo, is(notNullValue()));
        assertThat(sfListTwo.size(), is(sfListTwo.getItemsReturned()));
        assertThat(sfListTwo.get(0), is(notNullValue()));
        //StackMethods
        AsyncResponse<Stack> stackAsync = apis.createStack(153760, 97861);
        AsyncResponse<Stack> stackAsyncTwo = apis.createStack(153760);
        CollectionParameter cpStack = new CollectionParameter();
        cp.setPage(2);	
        cp.setSort("created_at");
        AsyncResponse<ResponseList<Stack>> stackListAsync = apis.createStackWithCollectionParmeter(503, cpStack);
        assertThat(stackAsync.get(), is(notNullValue()));
        assertThat(stackAsyncTwo.get(), is(notNullValue()));
        ResponseList<Stack> stackList = stackListAsync.get();
        assertThat(stackList, is(notNullValue()));
        assertThat(stackList.size(), is(stackList.getItemsReturned()));
        assertThat(stackList.get(0), is(notNullValue()));
	}
}
