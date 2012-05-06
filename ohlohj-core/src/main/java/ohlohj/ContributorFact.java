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
public interface ContributorFact {
	/**
	 * The unique ID for the Analysis which provided the source data <br>
	 * for this ContributorFact.
	 * @return the contributorID
	 */
	public long getContributorId();
	/**
	 * The ID for the person who contributed the code measured in this <br>
	 * ContributorFact. The contributor_id is not globally unique. <br>
	 * It is derived from the author name found in the source control <br>
	 * server log, and is unique within the scope of an individual project only.
	 * @return the analysisID
	 */
	public long getAnalysisId();
	/**
	 * The name used by the author of this code when committing <br>
	 * to the source control server.
	 * @return the contributorName
	 */
	public String getContributorName();
	/**
	 * If this contribution has been claimed by an Ohloh member, <br>
	 * the element contains the unique ID of the Ohloh Account. <br>
	 * Otherwise, this element is omitted
	 * @return the accountID.
	 */
	public long getAccountId();
	/**
	 * The unique ID of the Language most often used by this contributor, <br>
	 * measured by the number of code lines added. If this contributor <br>
	 * has not committed any code in a language which Ohloh can recognize, <br>
	 * this value will be null.
	 * @return the primaryLanguageID
	 */
	public int getPrimaryLanguageId();
	/**
	 * The nice_name of the Language specified by primary_language_id.
	 * @return the primaryLanguageNiceName
	 */
	public String getPrimaryLanguageNiceName();
	/**
	 * The fraction of new lines added by this contributor <br>
	 * which are comments. Note that Ohloh does not track <br>
	 * the net lines of current code attributable to an specific individual. <br>
	 * This statistic merely sums over all new lines added, <br>
	 * and does not consider whether the added lines were later removed <br>
	 * by this contributor or any other.
	 * @return the commentRatio
	 */
	public float getCommentRatio();
	/**
	 * The time of the first commit by this contributor.
	 * @return the firstCommitTime
	 */
	public Date getFirstCommitTime();
	/**
	 * The time of the last commit by this contributor.
	 * @return the lastCommitTime
	 */
	public Date getLastCommitTime();
	/**
	 * The total number of calendar months in which this contributor <br>
	 * made at least one commit. Note that this is not simply the number <br>
	 * of months between first_commit_time and last_commit_time: months <br>
	 * in which there was no activity for this contributor are not counted.
	 * @return the manMonths
	 */
	public int getManMonths();
	/**
	 * The total number of commits made by this contributor.
	 * @return the commits
	 */
	public int getCommits();
	/**
	 * Considering only months in which this contributor made at least one commit, <br>
	 * this is the median number of commits in a single month by this contributor.
	 * @return the medianCommits
	 */
	public int getMedianCommits();
	/**
	 * A collection of ContributorLanguageFacts may be included here, <br>
	 * covering statistics in individual Languages. <br>
	 * This collection is only present when ContributorFacts are queried individually.
	 * @return the list of contributorLanguageFact
	 */
	public List<ContributorLanguageFact> getContributorLanguageFact();
}
