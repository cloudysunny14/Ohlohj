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
package ohlohj.appengine;

import ohlohj.appengine.apis.AsyncAccountMethods;
import ohlohj.appengine.apis.AsyncActivityFactMethods;
import ohlohj.appengine.apis.AsyncAnalysisMethods;
import ohlohj.appengine.apis.AsyncContributorFactMethods;
import ohlohj.appengine.apis.AsyncEnlistMentMethods;
import ohlohj.appengine.apis.AsyncFactoidMethods;
import ohlohj.appengine.apis.AsyncKudoMethods;
import ohlohj.appengine.apis.AsyncLanguagesMethods;
import ohlohj.appengine.apis.AsyncProjectMethods;
import ohlohj.appengine.apis.AsyncSizeFactMethods;
import ohlohj.appengine.apis.AsyncStackMethods;
import ohlohj.auth.OAuthSupport;

/**
 * @author cloudysunny14
 *
 */
public interface FetchAsyncOhloh extends
AsyncAccountMethods,
AsyncProjectMethods,
AsyncActivityFactMethods,
AsyncAnalysisMethods,
AsyncContributorFactMethods,
AsyncEnlistMentMethods,
AsyncFactoidMethods,
AsyncKudoMethods,
AsyncLanguagesMethods,
AsyncSizeFactMethods,
AsyncStackMethods,
OAuthSupport
{
}
