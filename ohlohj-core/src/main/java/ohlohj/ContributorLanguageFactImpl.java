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

import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 */
public class ContributorLanguageFactImpl implements ContributorLanguageFact
	, java.io.Serializable {
	private static final long serialVersionUID = -6790357591019553788L;
	private long analysisId;
	private long contributorId;
	private String contributorName;
	private int languageId;
	private String languageNiceName;
	private float commentRatio;
	private int manMonths;
	private int commits;
	private int medianCommits;
	
	private ContributorLanguageFactImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		contributorId = parser.getLong("contributor_language_fact/contributor_id/text()");
		analysisId = parser.getLong("contributor_language_fact/analysis_id/text()");
		contributorName = parser.getString("contributor_language_fact/contributor_name/text()");
		languageId = parser.getInt("contributor_language_fact/language_id/text()");
		languageNiceName = parser.getString("contributor_language_fact/language_nice_name/text()");
		commentRatio = parser.getFloat("contributor_language_fact/comment_ratio/text()");
		manMonths = parser.getInt("contributor_language_fact/man_months/text()");
		commits = parser.getInt("contributor_language_fact/commits/text()");
		medianCommits = parser.getInt("contributor_language_fact/median_commits/text()");
	}
	/**
     * {@inheritDoc}
     */
	public long getAnalysisId() {
		return analysisId;
	}
	/**
     * {@inheritDoc}
     */
	public long getContributorId() {
		return contributorId;
	}
	/**
     * {@inheritDoc}
     */
	public String getContributorName() {
		return contributorName;
	}
	/**
     * {@inheritDoc}
     */
	public int getLanguageId() {
		return languageId;
	}
	/**
     * {@inheritDoc}
     */
	public String getLanguageNiceName() {
		return languageNiceName;
	}
	/**
     * {@inheritDoc}
     */
	public float getCommentRatio() {
		return commentRatio;
	}
	/**
     * {@inheritDoc}
     */
	public int getManMonths() {
		return manMonths;
	}
	/**
     * {@inheritDoc}
     */
	public int getCommits() {
		return commits;
	}
	/**
     * {@inheritDoc}
     */
	public int getMedianCommits() {
		return medianCommits;
	}
	
	public static ContributorLanguageFact createContributorLanguageFact(XMLParser parser) {
		return new ContributorLanguageFactImpl(parser);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContributorLanguageFactImpl that = (ContributorLanguageFactImpl) o;

        if (analysisId != that.analysisId) return false;
        if (contributorId != that.contributorId) return false;
        if (languageId != that.languageId)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (analysisId ^ (analysisId >>> 32));
        result = 31 * result + (int) (contributorId ^ (contributorId >>> 32));
        result = 31 * result + languageId;
        return result;
    }

    @Override
    public String toString() {
        return "ContributorLanguageFactImpl{" +
                "analysisId=" + analysisId +
                ", contributorId='" + contributorId +
                ", languageId='" + languageId +
                '}';
    }
}
