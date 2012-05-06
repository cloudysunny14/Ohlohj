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
package ohlohj.conf;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import ohlohj.conf.Configuration;
import ohlohj.conf.ConfigurationBuilder;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testDefaultConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Configuration conf = cb.build();
		assertThat(conf.isGAE(), is(false));
		assertThat(conf.getHttpProxyHost(), is(nullValue()));
		assertThat(conf.getHttpProxyUser(), is(nullValue()));
		assertThat(conf.getHttpProxyPassword(), is(nullValue()));
		assertThat(conf.getHttpProxyPort(), is(8080));
		assertThat(conf.getHttpConnectionTimeout(), is(20000));
		assertThat(conf.getHttpReadTimeout(), is(10000));
		assertThat(conf.getRetryCount(), is(3));
		assertThat(conf.getRetryIntervalSeconds(), is(5));
	}
	
	@Test
	public void testCustomConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setHttpProxyHost("host").setHttpProxyUser("user")
		.setHttpProxyPassword("pass").setHttpProxyPort(1234)
		.setHttpConnectionTimeout(30000).setHttpReadTimeout(30000)
		.setRetryCount(10).setIntervalSeconds(10);
		Configuration conf = cb.build();
		assertThat(conf.isGAE(), is(false));
		assertThat(conf.getHttpProxyHost(), is("host"));
		assertThat(conf.getHttpProxyUser(), is("user"));
		assertThat(conf.getHttpProxyPassword(), is("pass"));
		assertThat(conf.getHttpProxyPort(), is(1234));
		assertThat(conf.getHttpConnectionTimeout(), is(30000));
		assertThat(conf.getHttpReadTimeout(), is(30000));
		assertThat(conf.getRetryCount(), is(10));
		assertThat(conf.getRetryIntervalSeconds(), is(10));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDuplicateBuild() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.build();
		cb.setHttpConnectionTimeout(10000);
		cb.build();
	}
}
