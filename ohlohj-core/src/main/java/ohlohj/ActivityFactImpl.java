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
public class ActivityFactImpl implements ActivityFact, java.io.Serializable{
	private static final long serialVersionUID = 6963757346419217520L;
	private Date month;
	private int codeAdded;
	private int codeRemoved;
	private int commentsAdded;
	private int commentsRemoved;
	private int blanksAdded;
	private int blanksRemoved;
	private int commits;
	private int contributors;
	
	public ActivityFactImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		month = parser.getDate("activity_fact/month/text()");
		codeAdded = parser.getInt("activity_fact/code_added/text()");
		codeRemoved = parser.getInt("activity_fact/code_removed/text()");
		commentsAdded = parser.getInt("activity_fact/comments_added/text()");
		commentsRemoved = parser.getInt("activity_fact/comments_removed/text()");
		blanksAdded = parser.getInt("activity_fact/blanks_added/text()");
		blanksRemoved = parser.getInt("activity_fact/blanks_removed/text()");
		commits = parser.getInt("activity_fact/commits/text()");
		contributors = parser.getInt("activity_fact/contributors/text()");
	}

	/**
     * {@inheritDoc}
     */
	public Date getMonth() {
		return month;
	}
	/**
     * {@inheritDoc}
     */
	public int getCodeAdded() {
		return codeAdded;
	}
	/**
     * {@inheritDoc}
     */
	public int getCodeRemoved() {
		return codeRemoved;
	}
	/**
     * {@inheritDoc}
     */
	public int getCommentsAdded() {
		return commentsAdded;
	}
	/**
     * {@inheritDoc}
     */
	public int getCommentsRemoved() {
		return commentsRemoved;
	}
	/**
     * {@inheritDoc}
     */
	public int getBlanksAdded() {
		return blanksAdded;
	}
	/**
     * {@inheritDoc}
     */
	public int getBlanksRemoved() {
		return blanksRemoved;
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
	public int getContributors() {
		return contributors;
	}
	
	public static ResponseList<ActivityFact> createActivityFactList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/activity_fact");
		ResponseList<ActivityFact> list = new ResponseListImpl<ActivityFact> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				ActivityFact activityFact = new ActivityFactImpl(childParser);
				list.add(activityFact);
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
        ActivityFactImpl that = (ActivityFactImpl) o;
        if (month != that.month) return false;
        if (codeAdded != that.codeAdded) return false;
        if (codeRemoved != that.codeRemoved)return false;
        if (commentsAdded != that.commentsAdded)return false;
        if (commentsRemoved != that.commentsRemoved)return false;
        if (blanksAdded != that.blanksAdded)return false;
        if (blanksRemoved != that.codeRemoved)return false;
        if (commits != that.commits)return false;
        if (contributors != that.contributors)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = month != null ? month.hashCode() : 0;
        result = 31 * result + codeAdded;
        result = 31 * result + codeRemoved;
        result = 31 * result + commentsAdded;
        result = 31 * result + commentsRemoved;
        result = 31 * result + blanksAdded;
        result = 31 * result + blanksRemoved;
        result = 31 * result + commits;
        result = 31 * result + contributors;
        return result;
    }

    @Override
    public String toString() {
        return "ActivityFactImpl{" +
                "month=" + month +
                ", codeAdded='" + codeAdded + '\'' +
                ", commentsAdded='" + commentsAdded + '\'' +
                ", blanksAdded='" + blanksAdded + '\'' +
                ", commits='" + commits +
                '}';
    }
}
