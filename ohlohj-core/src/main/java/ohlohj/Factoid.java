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

/**
 * 
 * @author cloudysunny14
 *
 */
public interface Factoid {
	/**
	 * The unique ID for the Factoid. Factoid IDs <br>
	 * change every time the Project is reanalyzed, <br>
	 * so it’s unwise to store these IDs for long periods.
	 * @return the ID
	 */
	public long getId();
	/**
	 * The unique ID of the analysis used to calculate this factoid.
	 * @return the analysisID
	 */
	public long getAnalysisId();
	/**
	 * The Factoid type. The possible types are defined below.
	 * @return the type
	 */
	public String getType();
	/**
	 * A short, human-readable description. <br>
	 * This is the bullet point text which appears <br>
	 * on the Ohloh project page.
	 * @return the description
	 */
	public String getDescription();
	/**
	 * An integer from -3 to +3 which rates the relative severity <br>
	 * of the factoid. Negative numbers generally indicate bad news, <br>
	 * positive numbers generally indicate good news. <br>
	 * Ohloh uses these numbers to select icons to display <br>
	 * beside the factoids. All factoids of the same type have the save severity.
	 * @return the serverity
	 */
	public int getSeverity();
	
}
