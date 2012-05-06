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
public class EnlistMentImpl implements EnlistMent, java.io.Serializable{
	private static final long serialVersionUID = 2110790398798805944L;
	private long id;
	private long projectId;
	private long repositoryId;
	private List<Repository> repository;
	
	private EnlistMentImpl(XMLParser parser, boolean isList) {
		if(isList) {
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("response/result/enlistment/id/text()");
		projectId = parser.getLong("response/result/enlistment/project_id/text()");
		repositoryId = parser.getLong("response/result/enlistment/repository_id/text()");
		repository = createRepository(parser.getNodeList("response/result/enlistment/repository"));
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getLong("enlistment/id/text()");
		projectId = parser.getLong("enlistment/project_id/text()");
		repositoryId = parser.getLong("enlistment/repository_id/text()");
		repository = createRepository(parser.getNodeList("enlistment/repository"));
	}
	
	private List<Repository> createRepository(NodeList nodeList) {
		List<Repository> list =
				new ArrayList<Repository>();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Repository repository = RepositoryImpl.createRepository(childParser);
				list.add(repository);
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
	public long getProjectId() {
		return projectId;
	}
	/**
     * {@inheritDoc}
     */
	public long getRepositoryId() {
		return repositoryId;
	}
	/**
     * {@inheritDoc}
     */
	public List<Repository> getRepository() {
		return repository;
	}
	
	public static EnlistMent createEnlistMent(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		EnlistMent enlistMent = new EnlistMentImpl(parser, false);
		return enlistMent;
	}
	
	public static ResponseList<EnlistMent> createEnlistMentList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/enlistment");
		ResponseList<EnlistMent> list = 
				new ResponseListImpl<EnlistMent> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				EnlistMent enlistMent = new EnlistMentImpl(childParser, true);
				list.add(enlistMent);
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
        EnlistMentImpl that = (EnlistMentImpl) o;

        if (id != that.id) return false;
        if (projectId != that.projectId) return false;
        if (repositoryId != that.repositoryId)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (repositoryId ^ (repositoryId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "EnlistMentImpl{" +
                "id=" + id +
                ", projectId='" + projectId +
                ", repositoryId='" + repositoryId +
                '}';
    }
}
