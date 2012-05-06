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
 */
public class AccountImpl implements Account, java.io.Serializable{
	private static final long serialVersionUID = 2778395770090745365L;
	private long id;
	private String name;
	private Date createAt;
	private Date updateAt;
	private String homePageUrl;
	private String avatarUrl;
	private String emailSha1;
	private int postsCount;
	private String location;
	private String countryCode;
	private double latitude;
	private double longitude;
	private KudoScore kudoScore;
	
	public AccountImpl(XMLParser parser, boolean isList) {
		if(isList){
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
        id = parser.getLong("response/result/account/id/text()");
        name = parser.getString("response/result/account/name/text()");
        createAt = parser.getDate("response/result/account/created_at/text()");
        updateAt = parser.getDate("response/result/account/updated_at/text()");
        homePageUrl = parser.getString("response/result/account/homepage_url/text()");
        avatarUrl = parser.getString("response/result/account/avatar_url/text()");
        emailSha1 = parser.getString("response/result/account/email_sha1/text()");
        postsCount = parser.getInt("response/result/account/posts_count/text()");
        location = parser.getString("response/result/account/location/text()");
        countryCode = parser.getString("response/result/account/country_code/text()");
        latitude = parser.getDouble("response/result/account/latitude/text()");
        longitude = parser.getDouble("response/result/account/longitude/text()");
        kudoScore = createKudoScore(parser.getNode("response/result/account/kudo_score"));
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getLong("account/id/text()");
        name = parser.getString("account/name/text()");
        createAt = parser.getDate("account/created_at/text()");
        updateAt = parser.getDate("account/updated_at/text()");
        homePageUrl = parser.getString("account/homepage_url/text()");
        avatarUrl = parser.getString("account/avatar_url/text()");
        emailSha1 = parser.getString("account/email_sha1/text()");
        postsCount = parser.getInt("account/posts_count/text()");
        location = parser.getString("account/location/text()");
        countryCode = parser.getString("account/country_code/text()");
        latitude = parser.getDouble("account/latitude/text()");
        longitude = parser.getDouble("account/longitude/text()");
	}
	
	private KudoScore createKudoScore(Node node) {
		KudoScore kudoScore = null;
		try {
			XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
			kudoScore = KudoScoreImpl.createKudoScore(childParser);
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return kudoScore;
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
	public String getName() {
		return name;
	}
	/**
     * {@inheritDoc}
     */
	public Date getCreateAt() {
		return createAt;
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
	public String getHomePageUrl() {
		return homePageUrl;
	}
	/**
     * {@inheritDoc}
     */
	public String getAvatarUrl() {
		return avatarUrl;
	}
	/**
     * {@inheritDoc}
     */
	public String getEmailSha1() {
		return emailSha1;
	}
	/**
     * {@inheritDoc}
     */
	public int getPostsCount() {
		return postsCount;
	}
	/**
     * {@inheritDoc}
     */
	public String getLocation() {
		return location;
	}
	/**
     * {@inheritDoc}
     */
	public String getCountryCode() {
		return countryCode;
	}
	/**
     * {@inheritDoc}
     */
	public double getLatitude() {
		return latitude;
	}
	/**
     * {@inheritDoc}
     */
	public double getLongitude() {
		return longitude;
	}
	/**
     * {@inheritDoc}
     */
	public KudoScore getKudoScore() {
		return kudoScore;
	}
	
	public static Account createAccount(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		Account account = new AccountImpl(parser, false);
		return account;
	}
	
	public static Account createAccount(XMLParser parser) {
		return new AccountImpl(parser, true);
	}
	
	public static ResponseList<Account> createAccountList(HttpResponse res) {
		if (null == res) {
            return null;
        }
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/account");
		ResponseList<Account> list = new ResponseListImpl<Account> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Account account = new AccountImpl(childParser, true);
				list.add(account);
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
        AccountImpl that = (AccountImpl) o;

        if (id != that.id) return false;
        if (name != that.name) return false;
        if (emailSha1 != that.emailSha1)return false;
        if (!name.equals(that.name))return false;
        if (!emailSha1.equals(that.emailSha1))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (emailSha1 != null ? emailSha1.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
