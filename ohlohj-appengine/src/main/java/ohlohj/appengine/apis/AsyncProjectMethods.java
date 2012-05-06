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
import ohlohj.Project;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.appengine.internal.http.AsyncResponse;

/**
 * @author harigaekiyonari
 *
 */
public interface AsyncProjectMethods {
	/**
     * Project returns from projectID.
     * To get a single Project, 
     * including its current best Analysis:
     * @see <a href="http://meta.ohloh.net/referenceproject/">Project </a>
     * @param projectId
     *      the projectId
     * @return Project
     *      the Project
     * @throws OhlohException when network(Service) is unavailable
     */
    public AsyncResponse<Project> createProject(long projectId) throws OhlohException;
    
    /**
     * Project list returns from CollectionParameter.
     * To get a list of all Projects, 
     * not including their Analysis
     * The Project collection request supports 
     * the standard collection request parameters.
     * @see <a href="http://meta.ohloh.net/referenceproject/">Project </a>
     * @param parameter
     * 		the collection parameter
     * @return ResponseList
     * 		the Project list
     * @throws OhlohException when network(Service) is unavailable
     */
    public AsyncResponse<ResponseList<Project>> createProject(CollectionParameter parameter) throws OhlohException;
}
