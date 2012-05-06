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

import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 * @param <T>
 */
public class ResponseListImpl<T> extends ArrayList<T> implements ResponseList<T>{

	private static final long serialVersionUID = -4643789311650667230L;
	private int itemsAvailable;
	private int itemsReturned;
	private int firstItemPosition;
	
	public ResponseListImpl(int size, HttpResponse res) {
		super(size);
        init(res);
	}
	
	private void init(HttpResponse res) {
		XMLParser parser = new XMLParser(res.asString());
		itemsAvailable = parser.getInt("response/items_available/text()");
		itemsReturned = parser.getInt("response/items_returned/text()");
		firstItemPosition = parser.getInt("response/first_item_position/text()");
	}
	/**
     * {@inheritDoc}
     */
	public int getItemsAvailable() {
		return itemsAvailable;
	}
	/**
     * {@inheritDoc}
     */
	public int getItemsReturned() {
		return itemsReturned;
	}
	/**
     * {@inheritDoc}
     */
	public int getFirstItemPosition() {
		return firstItemPosition;
	}
}
