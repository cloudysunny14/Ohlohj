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
import java.util.List;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface Project {
	/**
	 * The unique ID for the Project.
	 * @return the ID
	 */
    public long getId();
    /**
     * The project name. Currently limited to 40 characters, <br>
     * and must be unique on Ohloh.
     * @return the Name
     */
    public String getName();
    /**
     * The time at which this Project was initially <br>
     * added to the Ohloh database.
     * @return the createdAt
     */
    public Date getCreatedAt();
    /**
     * The time of the most recent modification <br>
     * of this Project record.
     * @return the updatedAt
     */
    public Date getUpdatedAt();
    /**
     * An optional description. Currently limited to 800 characters.
     * @return the description
     */
    public String getDescription();
    /**
     * An optional URL to the project home page.
     * @return the homePageURL
     */
    public String getHomePageUrl();
    /**
     * An optional URL to a website hosting project downloads.
     * @return the downloadURL
     */
    public String getDownloadUrl();
    /**
     * A short, unique name for this project. This name is used in Ohloh URLs.
     * @return the URLName
     */
    public String getUrlName();
    /**
     * An url to the project’s 64×64 pixels logo image.
     * @return the MediumLogoURL
     */
    public String getMediumLogoUrl();
    /**
     * An url to the project’s 32×32 pixels logo image.
     * @return the smallLogoURL
     */
    public String getSmallLogoUrl();
    /**
     * The number of Stacks currently containing this project. <br>
     * Higher stack counts indicate more popular projects.
     * @return the UserCount
     */
    public int getUserCount();
    /**
     * A floating point value from 1.0 to 5.0, representing <br>
     * the average value of all user ratings. <br>
     * 1.0 is the worst possible rating and 5.0 is the highest possible rating. <br>
     * Will be null if no users have rated this project.
     * @return the avgRating
     */
    public float getAvgRating();
    /**
     * The number of users who have rated this project.
     * @return the RatingCount
     */
    public int getRatingCount();
    /**
     * The unique ID of the current best Analysis for this project. <br>
     * If the project has never been analyzed, this element will be empty.
     * @return the analysisID
     */
    public long getAnalysisId();
    /**
     * For convenience, the current best Analysis for the project is <br>
     * included in this element. <br>
     * This object is present only when you have requested a single project <br>
     * — if the project was returned as part of a collection, <br>
     * the analysis object will not be present.
     * @return the analysis
     */
    public Analysis getAnalysis();
    /**
     * The Licenses. of the project.
     * @return the Licenses
     */
    public List<License> getLicenses();
}
