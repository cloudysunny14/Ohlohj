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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.parser.XMLParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author cloudysunny14
 *
 */
public class ContributorFactImpl implements ContributorFact, java.io.Serializable{
	private static final long serialVersionUID = 327664368187569158L;
	private long contributorId;
	private long analysisId;
	private long accountId;
	private String contributorName;
	private int primaryLanguageId;
	private String primaryLanguageNiceName;
	private float commentRatio;
	private Date firstCommitTime;
	private Date lastCommitTime;
	private int manMonths;
	private int commits;
	private int medianCommits;
	private List<ContributorLanguageFact> contributorLanguageFact;
	private final List<ContributorLanguageFact> NULLLANGFACT = 
			new ArrayList<ContributorLanguageFact>();
	
	public ContributorFactImpl(XMLParser parser, boolean isList) {
		if(isList){
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		contributorId = parser.getLong("response/result/contributor_fact/contributor_id/text()");
		analysisId = parser.getLong("response/result/contributor_fact/analysis_id/text()");
		accountId = parser.getLong("response/result/contributor_fact/account_id/text()");
		contributorName = parser.getString("response/result/contributor_fact/contributor_name/text()");
		primaryLanguageId = parser.getInt("response/result/contributor_fact/primary_language_id/text()");
		primaryLanguageNiceName = parser.getString("response/result/contributor_fact/primary_language_nice_name/text()");
		commentRatio = parser.getFloat("response/result/contributor_fact/comment_ratio/text()");
		firstCommitTime = parser.getDate("response/result/contributor_fact/first_commit_time/text()");
		lastCommitTime = parser.getDate("response/result/contributor_fact/last_commit_time/text()");
		manMonths = parser.getInt("response/result/contributor_fact/man_months/text()");
		commits = parser.getInt("response/result/contributor_fact/commits/text()");
		medianCommits = parser.getInt("response/result/contributor_fact/median_commits/text()");
		contributorLanguageFact = createContLangFact(parser.getNodeList("response/result/contributor_fact/contributor_language_facts/contributor_language_fact"));
	}
	
	private void listInit(XMLParser parser) {
		contributorId = parser.getLong("contributor_fact/contributor_id/text()");
		analysisId = parser.getLong("contributor_fact/analysis_id/text()");
		accountId = parser.getLong("contributor_fact/account_id/text()");
		contributorName = parser.getString("contributor_fact/contributor_name/text()");
		primaryLanguageId = parser.getInt("contributor_fact/primary_language_id/text()");
		primaryLanguageNiceName = parser.getString("contributor_fact/primary_language_nice_name/text()");
		commentRatio = parser.getFloat("contributor_fact/comment_ratio/text()");
		firstCommitTime = parser.getDate("contributor_fact/first_commit_time/text()");
		lastCommitTime = parser.getDate("contributor_fact/last_commit_time/text()");
		manMonths = parser.getInt("contributor_fact/man_months/text()");
		commits = parser.getInt("contributor_fact/commits/text()");
		medianCommits = parser.getInt("contributor_fact/median_commits/text()");
		contributorLanguageFact = NULLLANGFACT;
	}
	
	private List<ContributorLanguageFact> createContLangFact(NodeList nodeList){
		List<ContributorLanguageFact> list = 
				new ArrayList<ContributorLanguageFact>();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				ContributorLanguageFact contributorLangFact = ContributorLanguageFactImpl.createContributorLanguageFact(childParser);
				list.add(contributorLangFact);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
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
	public long getAnalysisId() {
		return analysisId;
	}
	/**
     * {@inheritDoc}
     */
	public long getAccountId() {
		return accountId;
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
	public int getPrimaryLanguageId() {
		return primaryLanguageId;
	}
	/**
     * {@inheritDoc}
     */
	public String getPrimaryLanguageNiceName() {
		return primaryLanguageNiceName;
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
	public Date getFirstCommitTime() {
		return firstCommitTime;
	}
	/**
     * {@inheritDoc}
     */
	public Date getLastCommitTime() {
		return lastCommitTime;
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
	/**
     * {@inheritDoc}
     */
	public List<ContributorLanguageFact> getContributorLanguageFact() {
		return contributorLanguageFact;
	}
	
	public static ContributorFact createContributorFact(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		ContributorFact contributorFact = new ContributorFactImpl(parser, false);
		return contributorFact;
	}
	
	public static ResponseList<ContributorFact> createContributorFactList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/contributor_fact");
		ResponseList<ContributorFact> list = 
				new ResponseListImpl<ContributorFact> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				ContributorFact contributorFact = new ContributorFactImpl(childParser, true);
				list.add(contributorFact);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContributorFactImpl that = (ContributorFactImpl) o;

        if (contributorId != that.contributorId) return false;
        if (analysisId != that.analysisId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (contributorId ^ (contributorId >>> 32));
        result = 31 * result + (int) (analysisId ^ (analysisId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ContributorFactImpl{" +
                "contributorId=" + contributorId +
                ", analysisId='" + analysisId +
                '}';
    }
}
