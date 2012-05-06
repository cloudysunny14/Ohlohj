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

import ohlohj.Account;
import ohlohj.KudoScore;
import ohlohj.OhlohAPIImpl;
import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.apis.OhlohAPI;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.After;
import org.junit.Test;

public class AccountMethodsTest extends MethodsTestBase{

	@Test
	public void testAccountSearch() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
        OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Account account = apis.createAccount(153760);
		assertThat(account, is(notNullValue()));
        assertThat(account.getId(), is(153760l));
        assertThat(account.getName(), is("cloudysunny14"));
        assertThat(account.getCreateAt(), is(notNullValue()));
        assertThat(account.getUpdateAt(), is(notNullValue()));
        assertThat(account.getHomePageUrl(), is(notNullValue()));
        assertThat(account.getAvatarUrl(), is("http://www.gravatar.com/avatar.php?gravatar_id=48717c01876c8a6de716d912a383a427"));
        assertThat(account.getEmailSha1(), is("7ad32523a59871af486b1b604c8838f9f2709d0d"));
        assertThat(account.getPostsCount(), is(greaterThan(0)));
        assertThat(account.getLocation(), is(notNullValue()));
        assertThat(account.getCountryCode(), is(notNullValue()));
        assertThat(account.getLatitude(), is(greaterThanOrEqualTo(0d)));
        assertThat(account.getLongitude(), is(greaterThanOrEqualTo(0d)));
        assertThat(account.getKudoScore(), is(notNullValue()));
        KudoScore kudoScore = account.getKudoScore();
        assertThat(kudoScore.getCreatedAt(), is(nullValue()));
        assertThat(kudoScore.getKudoRank(), is(greaterThan(0)));
        assertThat(kudoScore.getPosition(), is(greaterThan(0l)));
        assertThat(kudoScore.getMaxPosition(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudoScore.getPositionDelta(), is(greaterThanOrEqualTo(0f)));
	}
	
	@Test
	public void testAccountSearchWithMD5() throws Exception{
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
        Account account = apis.createAccountWithEmail("cloudysunny14@gmail.com");
		assertThat(account, is(notNullValue()));
        assertThat(account.getId(), is(153760l));
        assertThat(account.getName(), is("cloudysunny14"));
        assertThat(account.getCreateAt(), is(notNullValue()));
        assertThat(account.getUpdateAt(), is(notNullValue()));
        assertThat(account.getHomePageUrl(), is(notNullValue()));
        assertThat(account.getAvatarUrl(), is("http://www.gravatar.com/avatar.php?gravatar_id=48717c01876c8a6de716d912a383a427"));
        assertThat(account.getEmailSha1(), is("7ad32523a59871af486b1b604c8838f9f2709d0d"));
        assertThat(account.getPostsCount(), is(2));
        assertThat(account.getLocation(), is(notNullValue()));
        assertThat(account.getCountryCode(), is(notNullValue()));
        assertThat(account.getLatitude(), is(greaterThanOrEqualTo(0d)));
        assertThat(account.getLongitude(), is(greaterThanOrEqualTo(0d)));
        KudoScore kudoScore = account.getKudoScore();
        assertThat(kudoScore.getCreatedAt(), is(nullValue()));
        assertThat(kudoScore.getKudoRank(), is(greaterThan(0)));
        assertThat(kudoScore.getPosition(), is(greaterThan(0l)));
        assertThat(kudoScore.getMaxPosition(), is(greaterThanOrEqualTo(0l)));
        assertThat(kudoScore.getPositionDelta(), is(greaterThanOrEqualTo(0f)));
	}
	
	@Test
	public void testAccountSearchFromQuery(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setApiKey(apiKey);
    	OhlohAPI apis = new OhlohAPIImpl(cb.build());
    	CollectionParameter query = new CollectionParameter();
    	query.setPage(1);
    	query.setQuery("Java");
    	query.setSort("created_at_reverse");
    	ResponseList<Account> list = null;
		try {
			list = apis.createAccountWithQuery(query);
		} catch (OhlohException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	assertThat(list, is(notNullValue()));
    	assertThat(list.size(), is(list.getItemsReturned()));
	}
	
	@After
    public void doAfter() {
		sdf = null;
    }
}
