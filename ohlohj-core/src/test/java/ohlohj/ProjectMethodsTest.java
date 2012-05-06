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

import ohlohj.License;
import ohlohj.OhlohAPIImpl;
import ohlohj.Project;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class ProjectMethodsTest extends MethodsTestBase{
	
    @Test
    public void testProjectDetail() throws Exception{
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Project projectDetail = apis.createProject(503);
        assertThat(projectDetail, is(notNullValue()));
        assertThat(projectDetail.getId(), is(503l));
        assertThat(projectDetail.getName(), is("SimpleTest"));
        //assertThat(projectDetail.getCreatedAt(), is(sdf.parse("2006-10-10T15:51:31Z")));
        assertThat(projectDetail.getCreatedAt(), is(notNullValue()));
        //assertThat(projectDetail.getUpdatedAt(), is(sdf.parse("2012-02-22T19:01:34Z")));
        assertThat(projectDetail.getUpdatedAt(), is(notNullValue()));
        assertThat(projectDetail.getDescription(), is("SimpleTest is a unit tester, Web tester, and dynamic mock objects framework for PHP. The test structure is similar to JUnit/PHPUnit. Used as a Web tester, you can navigate sites and submit forms as if you were using a Web browser."));
        assertThat(projectDetail.getHomePageUrl(), is("http://simpletest.org/"));
        assertThat(projectDetail.getDownloadUrl(), is("http://simpletest.org/en/download.html"));
        assertThat(projectDetail.getUrlName(), is("simpletest"));
        assertThat(projectDetail.getMediumLogoUrl(), is("http://cloud.ohloh.net/attachments/19951/simpletest-logo_med.png"));
        assertThat(projectDetail.getSmallLogoUrl(), is("http://cloud.ohloh.net/attachments/19951/simpletest-logo_small.png"));
        assertThat(projectDetail.getUserCount(), is(greaterThan(0)));
        assertThat(projectDetail.getAvgRating(), greaterThan(0f));
        assertThat(projectDetail.getRatingCount(), is(greaterThan(0)));
        assertThat(projectDetail.getAnalysisId(), is(greaterThan(0l)));
        assertThat(projectDetail.getAnalysis(), is(notNullValue()));
        assertThat(projectDetail.getLicenses(), is(notNullValue()));
        List<License> list = projectDetail.getLicenses();
        assertThat(list.size(), is(greaterThan(0)));
        License lisence = list.get(0);
        assertThat(lisence, is(notNullValue()));
        assertThat(lisence.getName(), is(notNullValue()));
        assertThat(lisence.getNiceName(), is(notNullValue()));
    }
    
    @Test
    public void testProjectListWithParameter() throws Exception {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        CollectionParameter cp = new CollectionParameter();
        cp.setQuery("SimpleTest");
        ResponseList<Project> list = apis.createProject(cp);
        assertThat(list, is(notNullValue()));
        assertThat(list.getItemsReturned(), is(list.size()));
        Project projectDetail = list.get(0);
        assertThat(projectDetail, is(notNullValue()));
        assertThat(projectDetail.getId(), is(503l));
        assertThat(projectDetail.getName(), is("SimpleTest"));
        //assertThat(projectDetail.getCreatedAt(), is(sdf.parse("2006-10-10T15:51:31Z")));
        assertThat(projectDetail.getCreatedAt(), is(notNullValue()));
        //assertThat(projectDetail.getUpdatedAt(), is(sdf.parse("2012-02-22T19:01:34Z")));
        assertThat(projectDetail.getUpdatedAt(), is(notNullValue()));
        assertThat(projectDetail.getDescription(), is("SimpleTest is a unit tester, Web tester, and dynamic mock objects framework for PHP. The test structure is similar to JUnit/PHPUnit. Used as a Web tester, you can navigate sites and submit forms as if you were using a Web browser."));
        assertThat(projectDetail.getHomePageUrl(), is("http://simpletest.org/"));
        assertThat(projectDetail.getDownloadUrl(), is("http://simpletest.org/en/download.html"));
        assertThat(projectDetail.getUrlName(), is("simpletest"));
        assertThat(projectDetail.getMediumLogoUrl(), is("http://cloud.ohloh.net/attachments/19951/simpletest-logo_med.png"));
        assertThat(projectDetail.getSmallLogoUrl(), is("http://cloud.ohloh.net/attachments/19951/simpletest-logo_small.png"));
        assertThat(projectDetail.getUserCount(), is(greaterThan(0)));
        assertThat(projectDetail.getAvgRating(), greaterThan(0f));
        assertThat(projectDetail.getRatingCount(), is(greaterThan(0)));
        assertThat(projectDetail.getAnalysisId(), is(greaterThan(0l)));
        //analysis id is not contain this request.
        assertThat(projectDetail.getAnalysis(), is(nullValue()));
        assertThat(projectDetail.getLicenses(), is(notNullValue()));
        List<License> licenselist = projectDetail.getLicenses();
        assertThat(licenselist.size(), is(greaterThan(0)));
        License lisence = licenselist.get(0);
        assertThat(lisence, is(notNullValue()));
        assertThat(lisence.getName(), is(notNullValue()));
        assertThat(lisence.getNiceName(), is(notNullValue()));
    }
}
