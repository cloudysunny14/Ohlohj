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
public interface Stack {
	/**
	 * The unique ID for the Stack.
	 * @return the ID
	 */
	public long getId();
	/**
	 * The title of the Stack.
	 * @return the title
	 */
	public String getTitle();
	/**
	 * The description of the Stack.
	 * @return the description
	 */
	public String getDescription();
	/**
	 * The most recent time at which any Projects were added <br>
	 * to or removed from this Stack.
	 * @return the title
	 */
	public Date getUpdatedAt();
	/**
	 * The total number of Projects currently contained in this Stack.
	 * @return the description
	 */
	public int getProjectCount();
	/**
	 * A collection of zero or more StackEntries. <br>
	 * If the Stack object was returned in response <br>
	 * to a collection request (/projects/x/stacks.xml), <br>
	 * you will receive only a single StackEntry corresponding <br>
	 * to the Project in question.
	 * @return the updatedAt
	 */
	public List<StackEntry> getStackEntries();
	/**
	 * The unique ID of the Account which owns this Stack.
	 * @return the projectCount
	 */
	public long getAccountId();
	/**
	 * For convenience, a full Account object may be included here. <br>
	 * If the stack object was returned in response to a collection request <br>
	 * (/projects/x/stacks.xml), the Account objects will be present.
	 * @return the stackEntries
	 */
	public Account getAccount();
}
