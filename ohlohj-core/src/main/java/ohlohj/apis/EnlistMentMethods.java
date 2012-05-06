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
package ohlohj.apis;

import ohlohj.EnlistMent;
import ohlohj.OhlohException;
import ohlohj.ResponseList;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface EnlistMentMethods {
	/**
	 * Enlistment returns from projectId and enlistMentId.
	 * Get a single Enlistment:
	 * @see <a href="http://meta.ohloh.net/referenceenlistment/">Enlistment</a>
	 * @param projectId
	 * 		the projectId
	 * @param enlistMentId
	 * 		the enlistMentId
	 * @return
	 * 		the EnlistMent object
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public EnlistMent createEnlistMent(long projectId, long enlistMentId) throws OhlohException;
	/**
	 * EnlistMent returns list from projectId
	 * Get a list of all Enlistments for a particular Project
	 * The Enlistment collection method supports 
	 * the standard collection request parameters.
	 * @see <a href="http://meta.ohloh.net/referenceenlistment/">Enlistment</a>
	 * @param projectId
	 * 		the projectId
	 * @param parameter
	 * 		the collection parameter
	 * @return ResponseList
	 * 		the EnlistMent list.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public ResponseList<EnlistMent> createEnlistMent(long projectId
			, CollectionParameter parameter) throws OhlohException;
}
