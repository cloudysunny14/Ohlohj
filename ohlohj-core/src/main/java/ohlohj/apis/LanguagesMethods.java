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

import ohlohj.Languages;
import ohlohj.OhlohException;
import ohlohj.ResponseList;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface LanguagesMethods {
	/**
	 * Languages returns from languageId.
	 * Get a single Language.
	 * @see <a href="http://meta.ohloh.net/referencelanguage/">Language</a>
	 * @param id
	 * 		the language id
	 * @return Languages
	 * 		the languages
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public Languages createLanguages(int id) throws OhlohException;
	/**
	 * Returns the sorted languages list.
	 * The Language collection request supports 
	 * the standard collection request parameters,
	 * following sort options:
	 * total, code, projects, comment_ratio,
	 * contributors, commits, name (default)
	 * @see <a href="http://meta.ohloh.net/referencelanguage/">Language</a>
	 * @param parameter
	 * 		the collection request parameter
	 * @return RepsonseList
	 * 		the languages list
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public ResponseList<Languages> createLanguages(CollectionParameter parameter) throws OhlohException;
}
