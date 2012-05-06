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
public class LanguagesImpl implements Languages, java.io.Serializable{
	private static final long serialVersionUID = -2286983766985537310L;
	private int id;
	private String name;
	private String niceName;
	private String category;
	private long code;
	private long comments;
	private long blanks;
	private float commentRatio;
	private int projects;
	private int contributors;
	private long commits;
	
	private LanguagesImpl(XMLParser parser, boolean isList) {
		if(isList){
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		id = parser.getInt("response/result/language/id/text()");
		name = parser.getString("response/result/language/name/text()");
		niceName = parser.getString("response/result/language/nice_name/text()");
		category = parser.getString("response/result/language/category/text()");
		code = parser.getLong("response/result/language/code/text()");
		comments = parser.getLong("response/result/language/comments/text()");
		blanks = parser.getLong("response/result/language/blanks/text()");
		commentRatio = parser.getFloat("response/result/language/comment_ratio/text()");
		projects = parser.getInt("response/result/language/projects/text()");
		contributors = parser.getInt("response/result/language/contributors/text()");
		commits = parser.getLong("response/result/language/commits/text()");
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getInt("language/id/text()");
		name = parser.getString("language/name/text()");
		niceName = parser.getString("language/nice_name/text()");
		category = parser.getString("language/category/text()");
		code = parser.getLong("language/code/text()");
		comments = parser.getLong("language/comments/text()");
		blanks = parser.getLong("language/blanks/text()");
		commentRatio = parser.getFloat("language/comment_ratio/text()");
		projects = parser.getInt("language/projects/text()");
		contributors = parser.getInt("language/contributors/text()");
		commits = parser.getLong("language/commits/text()");
	}
	/**
     * {@inheritDoc}
     */
	public int getId() {
		return id;
	}
	/**
     * {@inheritDoc}
     */
	public String getName() {
		return name;
	}
	/**
     * {@inheritDoc}
     */
	public String getNiceName() {
		return niceName;
	}
	/**
     * {@inheritDoc}
     */
	public String getCategory() {
		return category;
	}
	/**
     * {@inheritDoc}
     */
	public long getCode() {
		return code;
	}
	/**
     * {@inheritDoc}
     */
	public long getComments() {
		return comments;
	}
	/**
     * {@inheritDoc}
     */
	public long getBlanks() {
		return blanks;
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
	public int getProjects() {
		return projects;
	}
	/**
     * {@inheritDoc}
     */
	public int getContributors() {
		return contributors;
	}
	/**
     * {@inheritDoc}
     */
	public long getCommits() {
		return commits;
	}
	
	public static Languages createLanguages(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		Languages languages = new LanguagesImpl(parser, false);
		return languages;
	}
	
	public static ResponseList<Languages> createLanguagesList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/language");
		ResponseList<Languages> list = 
				new ResponseListImpl<Languages> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Languages languages = new LanguagesImpl(childParser, true);
				list.add(languages);
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
        LanguagesImpl that = (LanguagesImpl) o;

        if (id != that.id) return false;
        if (name != that.name) return false;
        if (!name.equals(that.name))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LanguagesImpl{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
