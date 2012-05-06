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
 * 
 * @author cloudysunny14
 *
 */
public interface Kudo {
	/**
	 * The time at which this Kudo was sent.
	 * @return the createdAt
	 */
	public Date getCreatedAt();
	/**
	 * The unique ID of the Account sending this Kudo.
	 * @return the senderAccountId
	 */
	public long getSenderAccountId();
	/**
	 * The name of the Accountsending this Kudo. 
	 * @return the senderAccountName
	 */
	public String getSenderAccountName();
	/**
	 * If this Kudo was sent to a Project contributor instead of an Account, <br>
	 * this is the unique ID of the Project. Otherwise, this element is omitted.
	 * @return the projectID
	 */
	public long getProjectId();
	/**
	 * If this Kudo was sent to a Project contributor instead of an Account, <br>
	 * this is the Project name. Otherwise, this element is omitted.
	 * @return the projectName
	 */
	public String getProjectName();
	/**
	 * If this Kudo was sent to a Project contributor instead of an Account, <Br>
	 * this is the unique ID of the contributor. Otherwise, this element is omitted.
	 * @return the contributorId
	 */
	public long getContoributorId();
	/**
	 * If this Kudo was sent to a Project contributor instead of an account, <br>
	 * this is the name which appears in the source control logs. <br>
	 * Otherwise, this element is omitted.
	 * @return the contributorName
	 */
	public String getContoributorName();
	/**
	 * If this Kudo can be linked to an Account, this is <br>
	 * the unique ID of the Account receiving this Kudo. <br>
	 * Otherwise, this element is omitted.
	 * @return the accountID
	 */
	public long getReceiverAccountId();
	/**
	 * If this kudo can be linked to an Account, <br>
	 * this is the name of the Account receiving this Kudo. <br>
	 * Otherwise, this element is omitted.
	 * @return the receiver account name
	 */
	public String getReceiverAccountName();
}
