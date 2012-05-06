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
public interface Repository {
	/**
	 * The unique ID of the Repository.
	 * @return the ID
	 */
	public long getId();
	/**
	 * The source control type. Supported values are:
	 * SvnRepository
	 *		Subversion
	 *	CvsRepository
	 *		CVS
	 *	GitRepository
	 *		Git
	 *	HgRepository
	 *		Mercurial
	 *	BzrRepository
	 *		Bazaar
	 *	SvnSyncRepository
	 *		Subversion ‘svnsync
	 * @return the type
	 */
	public String getType();
	/**
	 * The public URL for the source control server.
	 * @return the URL
	 */
	public String getUrl();
	/**
	 * For CVS repositories only, this is the module name. <br>
	 * For all other repository types, this element is omitted.
	 * @return the moduleName
	 */
	public String getModuleName();
	/**
	 * An optional username, if one is required <br>
	 * to access the source control server.
	 * @return the userName
	 */
	public String getUserName();
	/**
	 * An optional password, if one is required <br>
	 * to access the source control server.
	 * @return the passWord
	 */
	public String getPassword();
	/**
	 * The last time the Ohloh servers successfully queried <br>
	 * the source control server for changes.
	 * @return the loggedAt
	 */
	public Date getLoggedAt();
	/**
	 * The number of commits which the Ohloh servers have successfully <br>
	 * downloaded from the source control server.
	 * @return count of coummits
	 */
	public int getCommits();
	/**
	 * If the most recent attempt by Ohloh to query the source <br>
	 * control server for changes failed or was interrupted, <br>
	 * this value will be `failed`. In all other cases it is `success`.
	 * @return the ohlohJobStatus
	 */
	public String getOhlohJobStatus();
}
