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

import ohlohj.internal.parser.XMLParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author cloudysunny14
 *
 */
public class StackEntryImpl implements StackEntry, java.io.Serializable{
	private static final long serialVersionUID = -1547549835820516433L;
	private long id;
	private long stackId;
	private long projectId;
	private Date createdAt;
	private List<Project> project;
	
	private StackEntryImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("stack_entries/stack_entry/id/text()");
		stackId = parser.getLong("stack_entries/stack_entry/stack_id/text()");
		projectId = parser.getLong("stack_entries/stack_entry/project_id/text()");
		createdAt = parser.getDate("stack_entries/stack_entry/created_at/text()");
		project = createProject(parser.getNodeList("stack_entries/stack_entry/project"));
	}
	
	private List<Project> createProject(NodeList nodeList) {
		List<Project> list = 
				new ArrayList<Project>();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Project project = ProjectImpl.createProject(childParser);
				list.add(project);
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
	public long getStackId() {
		return stackId;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
     * {@inheritDoc}
     */
	public List<Project> getProject() {
		return project;
	}
	
	public static StackEntry createStackEntry(XMLParser parser) {
		return new StackEntryImpl(parser);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StackEntryImpl that = (StackEntryImpl) o;

        if (id != that.id) return false;
        if (stackId != that.stackId) return false;
        if (projectId != that.projectId)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (stackId ^ (stackId >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StackEntryImpl{" +
                "id=" + id +
                ", stackId='" + stackId +
                ", projectId='" + projectId +
                '}';
    }
}
