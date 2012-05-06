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

/**
 * @author cloudysunny14
 */
public interface Account {
	/**
	 * The unique ID for the Account.
	 * @return the account id
	 */
	public long getId();
	/**
	 * The public name for this Account.
	 * @return the account name
	 */
	public String getName();
	/**
	 * The time at which this Account was originally created on Ohloh.
	 * @return the created_at
	 */
	public Date getCreateAt();
	/**
	 * The time at which this Account record was last modified.
	 * @return the updated_at
	 */
	public Date getUpdateAt();
	/**
	 * An optional URL to a member’s home page, such as a blog.
	 * @return the HomePage URL
	 */
	public String getHomePageUrl();
	/**
	 * A URL to the profile image displayed on Ohloh pages.<br>
	 * Currently, this is an URL to the Gravatar free image hosting service,<br>
	 * which will resolves to a default image if the account holder is not<br>
	 * a Gravatar member. Images are 80×80 by default.
	 * @return the avatar URL
	 */
	public String getAvatarUrl();
	/**
	 * The SHA1 hex digest of the account email address.
	 * @return the email SHA1
	 */
	public String getEmailSha1();
	/**
	 * The number of posts made to the Ohloh forums by this account.
	 * @return the posts count
	 */
	public int getPostsCount();
	/**
	 * An optional text description of this account holder’s claimed location.<br>
	 * This text has been validated by either the Google or Yahoo geocoder<br>
	 *  web service, but the precision is variable.
	 * @return the location
	 */
	public String getLocation();
	/**
	 * A string representing the account holder’s country.<br>
	 * This field is derived from location using either the Google or<br>
	 *  Yahoo geocoder web service. If you use this data,<br>
	 *   read the important note below.
	 * @return the countrycode
	 */
	public String getCountryCode();
	/**
	 * Floating-point values representing the account’s<br>
	 *  latitude and longitude, suitable for use with the Google Maps API.<br>
	 *  They are available only when the account has specified a valid location.
	 * @return the latitude
	 */
	public double getLatitude();
	/**
	 * Floating-point values representing the account’s<br>
	 *  latitude and longitude, suitable for use with the Google Maps API.<br>
	 *  They are available only when the account has specified a valid location.
	 * @return the longitude
	 */
	public double getLongitude();
	/**
	 * If this account has a KudoScore, <br>
	 * it will appear here. New accounts may not have a KudoScore.<br>
	 *  The Ohloh seb site displays these accounts with a default KudoRank of 1.
	 * @return the KudoScore
	 */
	public KudoScore getKudoScore();
}
