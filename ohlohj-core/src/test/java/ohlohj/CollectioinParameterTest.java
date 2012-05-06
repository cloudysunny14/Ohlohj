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
import static org.junit.Assert.*;

import ohlohj.apis.CollectionParameter;
import ohlohj.internal.http.HttpParameter;

import org.junit.Test;

public class CollectioinParameterTest {

	@Test
	public void testQuery() {
		CollectionParameter cp = new CollectionParameter();
		cp.setPage(1);
		cp.setQuery("query");
		cp.setSort("sort");
		assertThat(cp.getParameter(), is(notNullValue()));
		assertThat(HttpParameter.encodeParameters(cp.getParameter()), is("query=query&sort=sort&page=1"));
	}
}
