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

import ohlohj.Analysis;
import ohlohj.OhlohException;

/**
 * 
 * @author cloudysunny14
 *
 */
public interface AnalysisMethods {
	/**
	 * Analysis returns from projectId and analysisId.
	 * Get a particular Analysis for a single Project
	 * @see <a href="http://meta.ohloh.net/referenceanalysis/">Analysis</a>
	 * @param projectId
	 * 		the project id
	 * @param analysisId
	 * 		the analysis id
	 * @return
	 * 		the analysis object
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public Analysis createAnalysis(long projectId, long analysisId) throws OhlohException;
	
	/**
	 * Returns Analysis from analysisId.
	 * A shortcut to the current best Analysis for a single Project
	 * @see <a href="http://meta.ohloh.net/referenceanalysis/">Analysis</a>
	 * @param projectId
	 * 		the project id
	 * @return 
	 * 		the analysis object
	 * @throws OhlohException when network(Service) is unavailable
	 */
	public Analysis createAnalysis(long projectId) throws OhlohException;
}
