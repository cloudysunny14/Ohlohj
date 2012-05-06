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
public interface Analysis {
	/**
	 * The unique ID for the Analysis.
	 * @return the id
	 */
	public long getId();
	/**
	 * The unique ID of the Project measured by this Analysis.
	 * @return project id
	 */
	public long getProjectId();
	/**
	 * The time at which this Analysis record was last modified.
	 * @return the update_at
	 */
	public Date getUpdateAt();
	/**
	 * The time at which Ohloh queried the source control<br>
	 * system for the source code measured by this Analysis.<br>
	 * All commits which occured at or before this time are included.<br>
	 * Note that the Analysis might also include some activity after<br>
	 * this time if the project includes many Repositories,<br>
	 * because not all Repositories are updated at the same time.<br>
	 * The logged_at time is the most pessimistic time among all <br>
	 * the Repositories included in the Project.
	 * @return the logged_at
	 */
	public Date getLoggedAt();
	/**
	 * Ohloh groups most historical statistics (ActivityFacts and SizeFacts) <br>
	 * into monthly totals. min_month indicates the first month for <br>
	 * which Ohloh has monthly historical statistics available for <br>
	 * this project. This is typically the date of the first project commit, <br>
	 * truncated to the beginning of its calendar month. <br>
	 * Only the year and month fields are significant.
	 * @return the minMonth
	 */
	public Date getMinMonth();
	/**
	 * The last month for which monthly historical statistics <br>
	 * are available for this project. Depending on when this <br>
	 * analysis was prepared, max_month usually refers to the current month, <br>
	 * but it may be slightly older. Only the year and month <br>
	 * fields are significant. Ohloh’s monthly statistics for <br>
	 * max_month are usually smaller than other months because <br>
	 * the complete month has not yet elapsed, and it reflects ongoing development work.
	 * @return the maxMonth
	 */
	public Date getMaxMonth();
	/**
	 * The number of contributors who made at least one commit to the project <br>
	 * source code in the twelve months leading up to and including max_month.
	 * @return the tweleveMonthContributor
	 */
	public int getTweleveMonthContributor();
	/**
	 * The most recent total count of all source code lines. <br>
	 * Blank lines and comment lines are excluded.
	 * @return the totalcodelines
	 */
	public int getTotalCodeLines();
	/**
	 * The unique ID of the most common programming language used in this project. <br>
	 * XML and HTML are ignored when making this determination.
	 * @return the mainlanguageID
	 */
	public String getMainLanguageId();
	/**
	 * The name of the most common programming language used in this project. <br>
	 * XML and HTML are ignored when making this determination.
	 * @return the mainlanguageName
	 */
	public String getMainLanguageName();
}
