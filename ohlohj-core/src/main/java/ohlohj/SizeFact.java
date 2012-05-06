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
public interface SizeFact {
	/**
	 * Indicates the month covered by this SizeFact. <br>
	 * Only the year and month fields are significant. <br>
	 * This SizeFact record includes all development activity <br>
	 * that occured during this month.
	 * @return the month
	 */
	public Date getMonth();
	/**
	 * The total net lines of code, excluding comments and blanks, <br>
	 * as of the end of this month.
	 * @return the code
	 */
	public long getCode();
	/**
	 * The total net lines of comments as of the end of this month.
	 * @return the comments
	 */
	public long getComments();
	/**
	 * The total net blank lines as of the end of this month.
	 * @return the blanks
	 */
	public long getBlanks();
	/**
	 * The fraction of net lines which are comments as <br>
	 * of the end of this month.
	 * @return the commentRatio
	 */
	public float getCommentRatio();
	/**
	 * The cumulative total number commits to the project source control, <br>
	 * including this month.
	 * @return the commits
	 */
	public int getCommits();
	/**
	 * The cumulative total months of effort expended by all contributors <br>
	 * on this project, including this month. For instance, <br>
	 * if 1 contributor has worked for 3 months and 2 developers have each <br>
	 * worked for 5 months, man_months will be 13. <br>
	 * This is the running total of the ActivityFact contributors property.
	 * @return the manMonths
	 */
	public int getManMonths();
}
