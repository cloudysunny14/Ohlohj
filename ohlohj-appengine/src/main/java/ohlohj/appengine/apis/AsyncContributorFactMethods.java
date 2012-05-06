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

import ohlohj.ContributorFact;
import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.appengine.internal.http.AsyncResponse;

/**
 * @author cloudysunny14
 *
 */
public interface AsyncContributorFactMethods {
	/**
	 * ContributorFacts list returns from projectId.
	 * Get a list of all ContributorFacts based on 
	 * the latest Analysis for a Project:
	 * The project collection request supports 
	 * the standard collection request parameters.
	 * @see <a href="http://meta.ohloh.net/referencecontributor_fact/">ContributorFact</a>
	 * @param projectId
	 * 		the projectId
	 * @param parameter
	 * 		the CollectionParameter.
	 * @return ResponseList
	 * 		the ResponseList object.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<ResponseList<ContributorFact>> createContributorFact(long projectId
			, CollectionParameter parameter) throws OhlohException;
	/**
	 * Returns ContributorFact from projectId and contributorId.
	 * Get a single ContributorFact based on the
	 *  latest Analysis for a Project
	 * @see <a href="http://meta.ohloh.net/referencecontributor_fact/">ContributorFact</a>
	 * @param projectId
	 * 		the projectId.
	 * @param contributorId
	 * 		the contributorId.
	 * @return
	 * 		the ContributorFact object
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<ContributorFact> createContributorFact(long projectId, long contributorId) throws OhlohException;
}
