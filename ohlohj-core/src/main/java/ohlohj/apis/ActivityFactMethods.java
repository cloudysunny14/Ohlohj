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

import ohlohj.ActivityFact;
import ohlohj.OhlohException;
import ohlohj.ResponseList;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface ActivityFactMethods {
	/**
     * ActivityFact list returns from projectID and analysisID.
     * To get all ActivityFacts for a particular Analysis:
     * @see <a href="http://meta.ohloh.net/referenceactivity_fact/">ActivityFact</a>
     * @param projectId
     *      The projectID
     * @param analysisId
     *      The project analysisID
     * @return ResponseList
     *      The ActivityFact list
	 * @throws OhlohException when network(Service) is unavailable
     */
    public ResponseList<ActivityFact> createActivityFact(long projectId, long analysisId) throws OhlohException;
    /**
     * ActivityFact list returns from projectID.
     * If you do not know the ID of the current best 
     * Analysis for a Project, you can use the following shortcut. 
     * The call returns one Activity for each month,
     * starting at the first month in which any code exists
     * @see <a href="http://meta.ohloh.net/referenceactivity_fact/">ActivityFact</a>
     * @param projectId
     *      The project ID
     * @return ResponseList
     *      The ActivityFact list
     * @throws OhlohException when network(Service) is unavailable
     */
    public ResponseList<ActivityFact> createActivityFact(long projectId) throws OhlohException;
}
