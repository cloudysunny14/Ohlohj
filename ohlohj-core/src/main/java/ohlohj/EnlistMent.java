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

import java.util.List;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface EnlistMent {
	/**
	 * The unique ID of the Enlistment.
	 * @return the ID
	 */
	public long getId();
	/**
	 * The unique ID of the Project.
	 * @return the projectID
	 */
	public long getProjectId();
	/**
	 * The unique ID of the Repository.
	 * @return the repositoryID
	 */
	public long getRepositoryId();
	/**
	 * The Repository record will be included in full here.
	 * @return the Repository
	 */
	public List<Repository> getRepository();
}
