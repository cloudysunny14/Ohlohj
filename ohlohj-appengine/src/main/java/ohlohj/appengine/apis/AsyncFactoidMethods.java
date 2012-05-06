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
package ohlohj.appengine.apis;

import ohlohj.Factoid;
import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.appengine.internal.http.AsyncResponse;

/**
 * @author cloudysunny14
 *
 */
public interface AsyncFactoidMethods {
	/**
	 * Factoid returns from projectId and factoid.
	 * Get a single Factoid
	 * @see <a href="http://meta.ohloh.net/referencefactoid/">Factoid</a>
	 * @param projectId
	 * 		the projectId.
	 * @param factoid
	 * 		the factoid.
	 * @return Factoid
	 * 		the factoid object.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<Factoid> createFactoid(long projectId, long factoid) throws OhlohException;
	/**
	 * Factoid list returns from projectId.
	 * Get a list of all current Factoids 
	 * for a particular Project:
	 * @see <a href="http://meta.ohloh.net/referencefactoid/">Factoid</a>
	 * @param projectId
	 * 		the projectId.
	 * @return ResponseList
	 * 		the Factoid list.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<ResponseList<Factoid>> createFactoid(long projectId) throws OhlohException;
}
