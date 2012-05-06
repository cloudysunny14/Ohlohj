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

import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.SizeFact;
import ohlohj.appengine.internal.http.AsyncResponse;

/**
 * @author cloudysunny14
 *
 */
public interface AsyncSizeFactMethods {
	/**
	 * SizeFact list returns from projectID and analysisID.
	 * get all SizeFacts for a particular Analysis
	 * @see <a href="http://meta.ohloh.net/referencesize_fact/">SizeFact</a>
	 * @param projectId
	 * 		the projectId
	 * @param analysisId
	 * 		the analysisId
	 * @return ResponseList
	 * 		the SizeFact list
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<ResponseList<SizeFact>> createSizeFact(long projectId, long analysisId) throws OhlohException;
	/**
	 * SizeFact list returns from projectId.
	 * The call returns one SizeFact 
	 * for each month, starting at the first month 
	 * in which any code exists, and ending at 
	 * the month in which Ohloh last created 
	 * a new Analysis for this Project. 
	 * @see <a href="http://meta.ohloh.net/referencesize_fact/">SizeFact</a>
	 * @param projectId
	 * 		the projectId
	 * @return ResponseList
	 * 		the SizeFact List
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public AsyncResponse<ResponseList<SizeFact>> createSizeFact(long projectId) throws OhlohException;
}
