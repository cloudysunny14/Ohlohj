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

/**
 * 
 * @author cloudysunny14
 *
 */
public interface ActivityFact {
	/**
	 * Indicates the month covered by this ActivityFact.<br>
	 * Only the year and month fields are significant.<br>
	 * This ActivityFact record includes all development activity<br>
	 * that occured during this month.
	 * @return the month
	 */
	public Date getMonth();
	/**
	 * The total new lines of code added, <br>
	 * excluding comments and blanks, during this month.
	 * @return the code added
	 */
	public int getCodeAdded();
	/**
	 * The total lines of code removed,<br>
	 * excluding comments and blanks, during this month.
	 * @return the code removed
	 */
	public int getCodeRemoved();
	/**
	 * The total lines of new comments added during this month.
	 * @return the comments added
	 */
	public int getCommentsAdded();
	/**
	 * The total lines of comments removed during this month.
	 * @return the comments removed
	 */
	public int getCommentsRemoved();
	/**
	 * The total blank lines added during this month.
	 * @return the blanks added
	 */
	public int getBlanksAdded();
	/**
	 * The total blank lines removed during this month.
	 * @return the blanks removed
	 */
	public int getBlanksRemoved();
	/**
	 * The number of commits made during this month.
	 * @return the commits
	 */
	public int getCommits();
	/**
	 * The number of contributors who made<br>
	 *  at least one commit during this month.
	 * @return the contributors
	 */
	public int getContributors();
}
