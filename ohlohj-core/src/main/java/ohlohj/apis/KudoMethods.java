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
package ohlohj.apis;

import ohlohj.Kudo;
import ohlohj.OhlohException;
import ohlohj.ResponseList;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface KudoMethods {
	/**
	 * Kudo sent list returns from accountId.
	 * Get a list of all Kudos sent by an Account:
	 * @see <a href="http://meta.ohloh.net/referencekudo/">Kudo</a>
	 * @param accountId
	 * 		the accountId.
	 * @return ResponseList
	 * 		the Kudo list.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public ResponseList<Kudo> createKudoSent(long accountId) throws OhlohException;
	
	/**
	 * Kudo list returns from accountId.
	 * Get a list of all Kudos received by an Account:
	 * @see <a href="http://meta.ohloh.net/referencekudo/">Kudo</a>
	 * @param accountId
	 * 		the accountId.
	 * @return
	 * 		the Kudo list.
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public ResponseList<Kudo> createKudo(long accountId) throws OhlohException;
}