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

import ohlohj.ContributorFact;
import ohlohj.ContributorLanguageFact;
import ohlohj.OhlohAPIImpl;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class ContributorFactMethodsTest extends MethodsTestBase{
	
	@Test
	public void testCreateContributorFact() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ContributorFact cf = apis.createContributorFact(2323, 9979356525106l);
        assertThat(cf, is(notNullValue()));
        assertThat(cf.getContributorId(), is(greaterThan(0l)));
        assertThat(cf.getAnalysisId(), is(greaterThan(0l)));
        assertThat(cf.getAccountId(), is(greaterThanOrEqualTo(0l)));
        assertThat(cf.getContributorName(), is(notNullValue()));
        assertThat(cf.getPrimaryLanguageId(), is(greaterThan(0)));
        assertThat(cf.getPrimaryLanguageNiceName(), is(notNullValue()));
        assertThat(cf.getCommentRatio(), is(greaterThan(0f)));
        //assertThat(cf.getFirstCommitTime(), is(sdf.parse("2004-09-23T17:53:18Z")));
        assertThat(cf.getFirstCommitTime(), is(notNullValue()));
        //assertThat(cf.getLastCommitTime(), is(is(sdf.parse("2011-02-24T13:02:50Z"))));
        assertThat(cf.getLastCommitTime(), is(notNullValue()));
        assertThat(cf.getManMonths(), is(greaterThan(0)));
        assertThat(cf.getCommits(), is(greaterThan(0)));
        assertThat(cf.getMedianCommits(), is(greaterThan(0)));
        assertThat(cf.getContributorLanguageFact(), is(notNullValue()));
        assertThat(cf.getContributorLanguageFact().size(), is(greaterThan(0)));
        ContributorLanguageFact clf = cf.getContributorLanguageFact().get(0);
        assertThat(clf.getContributorId(), is(greaterThan(0l)));
        assertThat(clf.getAnalysisId(), is(greaterThan(0l)));
        assertThat(clf.getContributorName(), is(notNullValue()));
        assertThat(clf.getLanguageId(), is(greaterThan(0)));
        assertThat(clf.getLanguageNiceName(), is(notNullValue()));
        assertThat(clf.getCommentRatio(), is(greaterThan(0f)));
        assertThat(cf.getManMonths(), is(greaterThan(0)));
        assertThat(cf.getCommits(), is(greaterThan(0)));
        assertThat(cf.getMedianCommits(), is(greaterThan(0)));
	}

	@Test
	public void testCreateContributorFactList() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        ResponseList<ContributorFact> cfList = apis.createContributorFact(2323, CollectionParameter.NULL_PARAMETER);
        assertThat(cfList.size(), is(cfList.getItemsReturned()));
        ContributorFact cf = cfList.get(0);
        assertThat(cf, is(notNullValue()));
        assertThat(cf.getContributorId(), is(greaterThan(0l)));
        assertThat(cf.getAnalysisId(), is(greaterThan(0l)));
        assertThat(cf.getAccountId(), is(greaterThanOrEqualTo(0l)));
        assertThat(cf.getContributorName(), is(notNullValue()));
        assertThat(cf.getPrimaryLanguageId(), is(greaterThan(0)));
        assertThat(cf.getPrimaryLanguageNiceName(), is(notNullValue()));
        assertThat(cf.getCommentRatio(), is(greaterThan(0f)));
        //assertThat(cf.getFirstCommitTime(), is(sdf.parse("2004-09-23T17:53:18Z")));
        assertThat(cf.getFirstCommitTime(), is(notNullValue()));
        //assertThat(cf.getLastCommitTime(), is(is(sdf.parse("2011-02-24T13:02:50Z"))));
        assertThat(cf.getLastCommitTime(), is(notNullValue()));
        assertThat(cf.getManMonths(), is(greaterThan(0)));
        assertThat(cf.getCommits(), is(greaterThan(0)));
        assertThat(cf.getMedianCommits(), is(greaterThan(0)));   
	}
}
