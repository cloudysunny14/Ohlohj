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

import ohlohj.OhlohException;
import ohlohj.ResponseList;
import ohlohj.Stack;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface StackMethods {
	/**
	 * Stack returns from accountID and stackID.
	 * Get a single Stack belonging to an Account
	 * @see <a href="http://meta.ohloh.net/referencestack/">Stack</a>
	 * @param accountId
	 * 		the accountId
	 * @param stackId
	 * 		the stackId
	 * @return Stack
	 * 		the Stack
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public Stack createStack(long accountId, long stackId) throws OhlohException;
	
	/**
	 * Stack returns from accountID.
	 * A handy shortcut for getting 
	 * the default Stack for an Account 
	 * when you don’t know the stack_id
	 * @see <a href="http://meta.ohloh.net/referencestack/">Stack</a>
	 * @param accountId
	 * 		the accountId
	 * @return Stack
	 * 		the Stack
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public Stack createStack(long accountId) throws OhlohException;
	
	/**
	 * Stack list returns from projectId.
	 * The Stack collection is paginated,
	 * and supports the standard 
	 * collection request parameters.
	 * To get a list of all Stacks for a particular Project
	 * @see <a href="http://meta.ohloh.net/referencestack/">Stack</a>
	 * @param projectId
	 * 		the project Id
	 * @param parameter
	 * 		the collectionParameter
	 * @return ResponseList
	 *  	the Stack list
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public ResponseList<Stack> createStackWithCollectionParmeter(long projectId, CollectionParameter parameter) throws OhlohException;
}
