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
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import ohlohj.Analysis;
import ohlohj.OhlohAPIImpl;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class AnalysisMethodsTest extends MethodsTestBase{

	@Test
	public void testCreateAnalysisFromTwoKeys() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Analysis analysis = apis.createAnalysis(2323, 1762228);
        assertThat(analysis, is(notNullValue()));
        assertThat(analysis.getId(), is(1762228l));
        assertThat(analysis.getProjectId(), is(2323l));
        assertThat(analysis.getUpdateAt(), is(sdf.parse("2011-03-10T00:50:32Z")));
        assertThat(analysis.getLoggedAt(), is(sdf.parse("2011-03-02T00:19:10Z")));
        assertThat(analysis.getMinMonth(), is(sdf.parse("2004-09-01T00:00:00Z")));
        assertThat(analysis.getMaxMonth(), is(sdf.parse("2011-02-01T00:00:00Z")));
        assertThat(analysis.getTweleveMonthContributor(), is(greaterThanOrEqualTo(0)));
        assertThat(analysis.getTotalCodeLines(), is(is(greaterThan(0))));
        assertThat(analysis.getMainLanguageId(), is(notNullValue()));
        assertThat(analysis.getMainLanguageName(), is(notNullValue()));
	}
	
	@Test
	public void testCreateAnalysis() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Analysis analysis = apis.createAnalysis(2323);
        assertThat(analysis, is(notNullValue()));
        assertThat(analysis.getId(), is(1762228l));
        assertThat(analysis.getProjectId(), is(2323l));
        assertThat(analysis.getUpdateAt(), is(sdf.parse("2011-03-10T00:50:32Z")));
        assertThat(analysis.getLoggedAt(), is(sdf.parse("2011-03-02T00:19:10Z")));
        assertThat(analysis.getMinMonth(), is(sdf.parse("2004-09-01T00:00:00Z")));
        assertThat(analysis.getMaxMonth(), is(sdf.parse("2011-02-01T00:00:00Z")));
        assertThat(analysis.getTweleveMonthContributor(), is(greaterThanOrEqualTo(0)));
        assertThat(analysis.getTotalCodeLines(), is(is(greaterThan(0))));
        assertThat(analysis.getMainLanguageId(), is(notNullValue()));
        assertThat(analysis.getMainLanguageName(), is(notNullValue()));
	}
}
