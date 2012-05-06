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

import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.parser.XMLParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 * 
 * @author cloudysunny14
 *
 */
public class ProjectImpl implements Project, java.io.Serializable{
	private static final long serialVersionUID = 4988821048316811643L;
	private long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private String homePageUrl;
    private String downloadUrl;
    private String urlName;
    private String mediumLogoUrl;
    private String smallLogoUrl;
    private int userCount;
    private float avgRating;
    private int ratingCount;
    private long analysisId;
    private Analysis analysis;
    private List<License> licenses;
    
    private ProjectImpl(XMLParser parser, boolean isList) {
    	if(isList) {
    		listInit(parser);
    	}
    	else {
    		init(parser);
    	}
    }
    
    private void listInit(XMLParser parser) {
    	id = parser.getLong("project/id/text()");
        name = parser.getString("project/name/text()");
        createdAt = parser.getDate("project/created_at/text()");
        updatedAt = parser.getDate("project/updated_at/text()");
        description = parser.getString("project/description/text()");
        homePageUrl = parser.getString("project/homepage_url/text()");
        downloadUrl = parser.getString("project/download_url/text()");
        urlName = parser.getString("project/url_name/text()");
        mediumLogoUrl = parser.getString("project/medium_logo_url/text()");
        smallLogoUrl = parser.getString("project/small_logo_url/text()");
        userCount = parser.getInt("project/user_count/text()");
        avgRating = parser.getFloat("project/average_rating/text()");
        ratingCount = parser.getInt("project/rating_count/text()");
        analysisId = parser.getLong("project/analysis_id/text()");
        analysis = createAnalysis(parser.getNode("project/analysis"));
        licenses = createLicenses(parser.getNodeList("project/licenses"));
    }
    
    private Analysis createAnalysis(Node node) {
    	Analysis analysis = null;
    	if(node!=null){
    		try {
    			XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
    			analysis = AnalysisImpl.createAnalysis(childParser);
    		} catch (TransformerFactoryConfigurationError e) {
    			e.printStackTrace();
    		} catch (TransformerException e) {
    			e.printStackTrace();
    		}
    	}
		return analysis;
    }
    
    private List<License> createLicenses(NodeList nodeList) {
    	List<License> list = 
				new ArrayList<License>();
		for(int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				License license = LicenseImpl.createLicense(childParser);
				list.add(license);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
    }
    
    private void init(XMLParser parser) {
        id = parser.getLong("response/result/project/id/text()");
        name = parser.getString("response/result/project/name/text()");
        createdAt = parser.getDate("response/result/project/created_at/text()");
        updatedAt = parser.getDate("response/result/project/updated_at/text()");
        description = parser.getString("response/result/project/description/text()");
        homePageUrl = parser.getString("response/result/project/homepage_url/text()");
        downloadUrl = parser.getString("response/result/project/download_url/text()");
        urlName = parser.getString("response/result/project/url_name/text()");
        mediumLogoUrl = parser.getString("response/result/project/medium_logo_url/text()");
        smallLogoUrl = parser.getString("response/result/project/small_logo_url/text()");
        userCount = parser.getInt("response/result/project/user_count/text()");
        avgRating = parser.getFloat("response/result/project/average_rating/text()");
        ratingCount = parser.getInt("response/result/project/rating_count/text()");
        analysisId = parser.getLong("response/result/project/analysis_id/text()");
        analysis = createAnalysis(parser.getNode("response/result/project/analysis"));
        licenses = createLicenses(parser.getNodeList("response/result/project/licenses"));
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
    public Date getCreatedAt() {
        return createdAt;
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
    public String getDescription() {
        return description;
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
    public String getDownloadUrl() {
        return downloadUrl;
    }
    /**
     * {@inheritDoc}
     */
    public String getUrlName() {
        return urlName;
    }
    /**
     * {@inheritDoc}
     */
    public String getMediumLogoUrl() {
        return mediumLogoUrl;
    }
    /**
     * {@inheritDoc}
     */
    public String getSmallLogoUrl() {
        return smallLogoUrl;
    }
    /**
     * {@inheritDoc}
     */
    public int getUserCount() {
        return userCount;
    }
    /**
     * {@inheritDoc}
     */
    public float getAvgRating() {
        return avgRating;
    }
    /**
     * {@inheritDoc}
     */
    public int getRatingCount() {
        return ratingCount;
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
    public Analysis getAnalysis() {
    	return analysis;
    }
    /**
     * {@inheritDoc}
     */
    public List<License> getLicenses() {
    	return licenses;
    }

    public static Project createProject(HttpResponse res) {
    	if(null == res) {
    		return null;
    	}
        XMLParser parser = new XMLParser(res.asString());
        Project projectDetail = new ProjectImpl(parser, false);
        return projectDetail;
    }
    
    public static ResponseList<Project> createProjectList(HttpResponse res) {
    	if(null == res) {
    		return null;
    	}
    	XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/project");
		ResponseList<Project> list = new ResponseListImpl<Project> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Project project = new ProjectImpl(childParser, true);
				list.add(project);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
    }
    
    public static Project createProject(XMLParser parser) {
    	return new ProjectImpl(parser, true);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectImpl that = (ProjectImpl) o;

        if (id != that.id) return false;
        if (name != that.name) return false;
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
        return "ProjectImpl{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
