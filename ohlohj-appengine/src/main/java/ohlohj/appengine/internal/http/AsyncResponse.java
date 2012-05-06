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
package ohlohj.appengine.internal.http;

/**
 * @author cloudysunny14
 *
 */
public interface AsyncResponse<V> {
	/**
	 * Waits if necessary for the computation to complete,<br>
	 *  and then retrieves its result.
	 * @see <a href="http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/Future.html">Interface {@code Future<V>}</a>
	 * @return V
	 * 		the computed result
	 */
	public V get();
}
