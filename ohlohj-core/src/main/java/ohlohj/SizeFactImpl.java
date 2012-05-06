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
public class SizeFactImpl implements SizeFact, java.io.Serializable {
	private static final long serialVersionUID = 2333351348452811715L;
	private Date month;
	private long code;
	private long comments;
	private long blanks;
	private float commentRatio;
	private int commits;
	private int manMonths;
	
	private SizeFactImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		month = parser.getDate("size_fact/month/text()");
		code = parser.getLong("size_fact/code/text()");
		comments = parser.getLong("size_fact/comments/text()");
		blanks = parser.getLong("size_fact/blanks/text()");
		commentRatio = parser.getFloat("size_fact/comment_ratio/text()");
		commits = parser.getInt("size_fact/commits/text()");
		manMonths = parser.getInt("size_fact/man_months/text()");
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
	public int getCommits() {
		return commits;
	}
	/**
     * {@inheritDoc}
     */
	public int getManMonths() {
		return manMonths;
	}
	
	public static ResponseList<SizeFact> createSizeFactList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/size_fact");
		ResponseList<SizeFact> list = new ResponseListImpl<SizeFact> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				SizeFact sizeFact = new SizeFactImpl(childParser);
				list.add(sizeFact);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

    @Override
    public String toString() {
        return "SizeFactImpl{" +
                "month=" + month +
                ", code='" + code +
                ", commits='" + commits +
                '}';
    }
}
