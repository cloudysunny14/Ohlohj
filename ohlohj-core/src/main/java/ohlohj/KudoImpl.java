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
public class KudoImpl implements Kudo, java.io.Serializable{
	private static final long serialVersionUID = 5030064145446429439L;
	private long senderAccountId;
	private String senderAccountName;
	private long projectId;
	private String projectName;
	private long contoributorId;
	private String contoributorName;
	private long receiverAccountId;
	private String receiverAccountName;
	private Date createdAt;
	
	private KudoImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		senderAccountId = parser.getLong("kudo/sender_account_id/text()");
		senderAccountName = parser.getString("kudo/sender_account_name/text()");
		projectId = parser.getLong("kudo/project_id/text()");
		projectName = parser.getString("kudo/project_name/text()");
		contoributorId = parser.getLong("kudo/contributor_id/text()");
		contoributorName = parser.getString("kudo/contributor_name/text()");
		receiverAccountId = parser.getLong("kudo/receiver_account_id/text()");
		receiverAccountName = parser.getString("kudo/receiver_account_name/text()");
		createdAt = parser.getDate("kudo/created_at/text()");
	}
	/**
     * {@inheritDoc}
     */
	public long getSenderAccountId() {
		return senderAccountId;
	}
	/**
     * {@inheritDoc}
     */
	public String getSenderAccountName() {
		return senderAccountName;
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
	public String getProjectName() {
		return projectName;
	}
	/**
     * {@inheritDoc}
     */
	public long getContoributorId() {
		return contoributorId;
	}
	/**
     * {@inheritDoc}
     */
	public String getContoributorName() {
		return contoributorName;
	}
	/**
     * {@inheritDoc}
     */
	public long getReceiverAccountId() {
		return receiverAccountId;
	}
	/**
     * {@inheritDoc}
     */
	public String getReceiverAccountName() {
		return receiverAccountName;
	}
	/**
     * {@inheritDoc}
     */
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public static ResponseList<Kudo> createKudoList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/kudo");
		ResponseList<Kudo> list = 
				new ResponseListImpl<Kudo> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Kudo kudo = new KudoImpl(childParser);
				list.add(kudo);
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
        KudoImpl that = (KudoImpl) o;

        if (senderAccountId != that.senderAccountId) return false;
        if (projectId != that.projectId) return false;
        if (contoributorId != that.contoributorId)return false;
        if (receiverAccountId != that.receiverAccountId)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (senderAccountId ^ (senderAccountId >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (contoributorId ^ (contoributorId >>> 32));
        result = 31 * result + (int) (receiverAccountId ^ (receiverAccountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "KudoImpl{" +
                "senderAccountId=" + senderAccountId +
                ", projectId='" + projectId +
                ", contoributorId='" + contoributorId +
                ", receiverAccountId='" + receiverAccountId +
                '}';
    }
}
