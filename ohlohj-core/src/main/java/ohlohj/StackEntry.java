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

import java.util.Date;
import java.util.List;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface StackEntry {
	/**
	 * The unique ID for the StackEntry.
	 * @return the ID
	 */
	public long getId();
	/**
	 * The unique ID of the Stack which contains this StackEntry.
	 * @return the statckID
	 */
	public long getStackId();
	/**
	 * The unique ID of the Project.
	 * @return the projectID
	 */
	public long getProjectId();
	/**
	 * The time at which this Project was added to this Stack.
	 * @return the createdAt
	 */
	public Date getCreatedAt();
	/**
	 * For convenience, a full Project object may be included here. <br>
	 * If the Stack object was returned in response to an Account Stack request <br>
	 * (/accounts/x/stacks/default.xml), the Project objects will be present.
	 * @return the projects
	 */
	public List<Project> getProject();
}
