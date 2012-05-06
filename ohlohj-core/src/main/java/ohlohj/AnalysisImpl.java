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

import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 */
public class AnalysisImpl implements Analysis, java.io.Serializable{
	private static final long serialVersionUID = -7743407357901112548L;
	private long id;
	private long projectId;
	private Date updateAt;
	private Date loggedAt;
	private Date minMonth;
	private Date maxMonth;
	private int tweleveMonsContributor;
	private int totalCodeLines;
	private String mainLanguageId;
	private String mainLanguageName;
	
	public AnalysisImpl(XMLParser parser, boolean isList) {
		if(isList) {
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("response/result/analysis/id/text()");
		projectId = parser.getLong("response/result/analysis/project_id/text()");
		updateAt = parser.getDate("response/result/analysis/updated_at/text()");
		loggedAt = parser.getDate("response/result/analysis/logged_at/text()");
		minMonth = parser.getDate("response/result/analysis/min_month/text()");
		maxMonth = parser.getDate("response/result/analysis/max_month/text()");
		tweleveMonsContributor = parser.getInt("response/result/analysis/twelve_month_contributor_count/text()");
		totalCodeLines = parser.getInt("response/result/analysis/total_code_lines/text()");
		mainLanguageId = parser.getString("response/result/analysis/main_language_id/text()");
		mainLanguageName = parser.getString("response/result/analysis/main_language_name/text()");
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getLong("analysis/id/text()");
		projectId = parser.getLong("analysis/project_id/text()");
		updateAt = parser.getDate("analysis/updated_at/text()");
		loggedAt = parser.getDate("analysis/logged_at/text()");
		minMonth = parser.getDate("analysis/min_month/text()");
		maxMonth = parser.getDate("analysis/max_month/text()");
		tweleveMonsContributor = parser.getInt("analysis/twelve_month_contributor_count/text()");
		totalCodeLines = parser.getInt("analysis/total_code_lines/text()");
		mainLanguageId = parser.getString("analysis/main_language_id/text()");
		mainLanguageName = parser.getString("analysis/main_language_name/text()");
	}
	/**
     * {@inheritDoc}
     */
	public long getId() {
		return id;
	}
	/**
     * {@inheritDoc}
     */
	public long getProjectId() {
		return projectId;
	}
	/**
     * {@inheritDoc}
     */
	public Date getUpdateAt() {
		return updateAt;
	}
	/**
     * {@inheritDoc}
     */
	public Date getLoggedAt() {
		return loggedAt;
	}
	/**
     * {@inheritDoc}
     */
	public Date getMinMonth() {
		return minMonth;
	}
	/**
     * {@inheritDoc}
     */
	public Date getMaxMonth() {
		return maxMonth;
	}
	/**
     * {@inheritDoc}
     */
	public int getTweleveMonthContributor() {
		return tweleveMonsContributor;
	}
	/**
     * {@inheritDoc}
     */
	public int getTotalCodeLines() {
		return totalCodeLines;
	}
	/**
     * {@inheritDoc}
     */
	public String getMainLanguageId() {
		return mainLanguageId;
	}
	/**
     * {@inheritDoc}
     */
	public String getMainLanguageName() {
		return mainLanguageName;
	}
	
	public static Analysis createAnalysis(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		Analysis analysis = new AnalysisImpl(parser, false);
		return analysis;
	}
	
	public static Analysis createAnalysis(XMLParser parser) {
		return new AnalysisImpl(parser, true);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisImpl that = (AnalysisImpl) o;

        if (id != that.id) return false;
        if (projectId != that.projectId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AnalysisImpl{" +
                "id=" + id +
                ", projectId='" + projectId +
                '}';
    }
}
