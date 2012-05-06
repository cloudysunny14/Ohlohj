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

import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 */
public class RepositoryImpl implements Repository, java.io.Serializable{
	private static final long serialVersionUID = -440339123433197021L;
	public long id;
	public String type;
	public String url;
	public String moduleName;
	public String userName;
	public String password;
	public Date loggedAt;
	public int commits;
	public String ohlohJobStatus;
	
	private RepositoryImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("repository/id/text()");
		type = parser.getString("repository/type/text()");
		url = parser.getString("repository/url/text()");
		moduleName = parser.getString("repository/module_name/text()");
		userName = parser.getString("repository/username/text()");
		password = parser.getString("repository/password/text()");
		loggedAt = parser.getDate("repository/logged_at/text()");
		commits = parser.getInt("repository/commits/text()");
		ohlohJobStatus = parser.getString("repository/ohloh_job_status/text()");
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
	public String getType() {
		return type;
	}
	/**
     * {@inheritDoc}
     */
	public String getUrl() {
		return url;
	}
	/**
     * {@inheritDoc}
     */
	public String getModuleName() {
		return moduleName;
	}
	/**
     * {@inheritDoc}
     */
	public String getUserName() {
		return userName;
	}
	/**
     * {@inheritDoc}
     */
	public String getPassword() {
		return password;
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
	public int getCommits() {
		return commits;
	}
	/**
     * {@inheritDoc}
     */
	public String getOhlohJobStatus() {
		return ohlohJobStatus;
	}
	
	public static Repository createRepository(XMLParser parser) {
		return new RepositoryImpl(parser);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryImpl that = (RepositoryImpl) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepositoryImpl{" +
                "id=" + id +
                ", type='" + type +
                '}';
    }
}
