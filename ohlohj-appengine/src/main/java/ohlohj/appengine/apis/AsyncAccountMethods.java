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
package ohlohj.appengine.apis;

import ohlohj.Account;
import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.apis.CollectionParameter;
import ohlohj.appengine.internal.http.AsyncResponse;

/**
 * @author cloudysunny14
 *
 */
public interface AsyncAccountMethods {
	/**
     * Account returns from AcocuntID.
     * To get a single Account:
     * @see <a href="http://meta.ohloh.net/referenceaccount/">Account</a>
     * @param accountId
     *      the userID
     * @return
     *      the Account object
	 * @throws OhlohException when network(Service) is unavailable
     */
	public AsyncResponse<Account> createAccount(long accountId) throws OhlohException;
	/**
     * Account returns from e-mail address.
     * using the MD5 hash of the account’s email address
     * You can also retrieve an Account using 
     * the MD5 hash of the email address, 
     * if you know it:
     * @see <a href="http://meta.ohloh.net/referenceaccount/">Account</a>
     * @param email
     *      the account's email address
     * @return
     *      the Account object
     * @throws OhlohException when network(Service) is unavailable
     */
	public AsyncResponse<Account> createAccountWithEmail(String email) throws OhlohException;
	
	/**
     * Account list returns from query objects.
     * To get a list of all Accounts
     * The account collection method supports 
     * the standard collection request parameters 
     * @see <a href="http://meta.ohloh.net/referenceaccount/">Account</a>
     * @param parameter
     *      the CollectionParameter
     * @return
     *      the ResponseList object
     * @throws OhlohException when network(Service) is unavailable
     */
	public AsyncResponse<ResponseList<Account>> createAccountWithQuery(CollectionParameter parameter) throws OhlohException;
}
