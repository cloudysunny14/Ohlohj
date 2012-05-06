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

/**
 * 
 * @author cloudysunny14
 *
 */
public interface ContributorLanguageFact {
	/**
	 * The unique ID for the Analysis which provided the source data <br>
	 * for this ContributorFact.
	 * @return the analysisID
	 */
	public long getAnalysisId();
	/**
	 * The ID for the person who contributed the code measured in <br>
	 * this ContributorLanguageFact. The contributor_id <br>
	 * is not globally unique. It is derived from the author name <br>
	 * found in the source control server log, and is unique within <br>
	 * the scope of an individual project only.
	 * @return the contributorID
	 */
	public long getContributorId();
	/**
	 * The name used by the author of this code when committing <br>
	 * to the source control server.
	 * @return the contributorName
	 */
	public String getContributorName();
	/**
	 * The unique ID of the Language measured.
	 * @return the languageID
	 */
	public int getLanguageId();
	/**
	 * The nice_name of the Language measured.
	 * @return the languageNiceName
	 */
	public String getLanguageNiceName();
	/**
	 * The fraction of new lines added by this contributor <br>
	 * in this language which are comments. <br>
	 * Note that Ohloh does not track the net lines of current <br>
	 * code attributable to an specific individual. <br>
	 * This statistic merely sums over all new lines added, <br>
	 * and does not consider whether the added lines were later removed <br>
	 * by this contributor or any other.
	 * @return the commentRatio
	 */
	public float getCommentRatio();
	/**
	 * The total number of calendar months in which this contributor <br>
	 * made at least one commit using this Language. <br>
	 * Months in which there was no activity in this Language for <br>
	 * this contributor are not counted.
	 * @return the manMonths
	 */
	public int getManMonths();
	/**
	 * The total number of commits made in this Language by this contributor.
	 * @return the commits
	 */
	public int getCommits();
	/**
	 * Considering only months in which this contributor made <br>
	 * at least one commit in this Language, <br>
	 * this is the median number of commits in a single month <br>
	 * in this Language by this contributor.
	 * @return the medianCommits
	 */
	public int getMedianCommits();
}
