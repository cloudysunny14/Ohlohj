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
public class StackImpl implements Stack, java.io.Serializable{
	private static final long serialVersionUID = 6238066023310283277L;
	private long id;
	private String title;
	private String description;
	private Date updatedAt;
	private int projectCount;
	private List<StackEntry> stackEntries;
	private long accountId;
	private Account account;
	
	private StackImpl(XMLParser parser, boolean isList) {
		if(isList) {
			listInit(parser);
		}
		else {
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("response/result/stack/id/text()");
		title = parser.getString("response/result/stack/title/text()");
		description = parser.getString("response/result/stack/description/text()");
		updatedAt = parser.getDate("response/result/stack/updated_at/text()");
		projectCount = parser.getInt("response/result/stack/project_count/text()");
		stackEntries = createStackEntries(parser.getNodeList("response/result/stack/stack_entries"));
		accountId = parser.getInt("response/result/stack/account_id/text()");
		account = createAccount(parser.getNode("response/result/stack/account"));
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getLong("stack/id/text()");
		title = parser.getString("stack/title/text()");
		description = parser.getString("stack/description/text()");
		updatedAt = parser.getDate("stack/updated_at/text()");
		projectCount = parser.getInt("stack/project_count/text()");
		stackEntries = createStackEntries(parser.getNodeList("stack/stack_entries"));
		accountId = parser.getInt("stack/account_id/text()");
		account = createAccount(parser.getNode("stack/account"));
	}
	
	private Account createAccount(Node node) {
		Account account = null;
		if(node!=null){
			try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				account = AccountImpl.createAccount(childParser);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return account;
	}
	
	private List<StackEntry> createStackEntries(NodeList nodeList) {
		List<StackEntry> list = 
				new ArrayList<StackEntry>();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				StackEntry stackEntry = StackEntryImpl.createStackEntry(childParser);
				list.add(stackEntry);
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
	public long getId() {
		return id;
	}
	/**
     * {@inheritDoc}
     */
	public String getTitle() {
		return title;
	}
	/**
     * {@inheritDoc}
     */
	public String getDescription() {
		return description;
	}
	/**
     * {@inheritDoc}
     */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
     * {@inheritDoc}
     */
	public int getProjectCount() {
		return projectCount;
	}
	/**
     * {@inheritDoc}
     */
	public List<StackEntry> getStackEntries() {
		return stackEntries;
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
	public Account getAccount() {
		return account;
	}
	
	public static Stack createStack(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
        Stack stack = new StackImpl(parser, false);
        return stack;
	}
	
	public static ResponseList<Stack> createStackList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/stack");
		ResponseList<Stack> list = new ResponseListImpl<Stack> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Stack stack = new StackImpl(childParser, true);
				list.add(stack);
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
        StackImpl that = (StackImpl) o;

        if (id != that.id) return false;
        if (accountId != that.accountId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "id=" + id +
                ", accountId='" + accountId +
                '}';
    }
}
