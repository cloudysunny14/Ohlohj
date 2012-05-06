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

import java.util.ArrayList;
import java.util.List;

import ohlohj.internal.http.HttpParameter;

/**
 * 
 * @author cloudysunny14
 *
 */
public class CollectionParameter {

	/**
	 * If you do not set any parameters.
	 */
	public static final CollectionParameter NULL_PARAMETER = new CollectionParameter();
	private String query;
	private String sort;
	private int page;
	
	public CollectionParameter() {
		//This parameter is one-based
		setPage(1);
	}
	
	/**
	 * Results will be filtered by the provided string. <br>
	 * Only items that contain the query string in <br>
	 * their names or descriptions will be returned. <br>
	 * Filtering is case insenstive. <br>
	 * Only alphanumeric characters are accepted. <br>
	 * All non-alphanumeric characters will be replaced by spaces. <br>
	 * Filtering is not available on all API methods, <br>
	 * and the searched text depends on the type of object <br>
	 * requested. Check the reference documentation for specifics.
	 * @param query 
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	/**
	 * Controls the sort order of the returned results. <br>
	 * Typical supported values are name, created_at, <br>
	 * and updated_at. Most sort options are also available in a reversed order, <br>
	 * such as name_reverse. The specific sort options available <br>
	 * depend on the type of object requested, <br>
	 * so check to the reference documentation for specifics.
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	/**
	 * In most cases, the Ohloh API returns at most 25 items per request. <br>
	 * Pass this parameter to request subsequent items beyond the first page. <br>
	 * This parameter is one-based, with a default value of 1. <br>
	 * If you pass a value outside the range of available pages, <br>
	 * you will receive the first page.
	 * @param page
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	public HttpParameter[] getParameter() {
		ArrayList<HttpParameter> params = new ArrayList<HttpParameter>();
        appendParameter("query", query, params);
        appendParameter("sort", sort, params);
        appendParameter("page", String.valueOf(page), params);
        HttpParameter[] paramArray = new HttpParameter[params.size()];
        return params.toArray(paramArray);
	}
	
	private void appendParameter(String name, String value, List<HttpParameter> params) {
        if (value != null) {
            params.add(new HttpParameter(name, value));
        }
    }
}
