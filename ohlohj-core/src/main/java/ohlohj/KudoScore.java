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
public interface KudoScore {
	/**
	 * The time at which this KudoScore was calculated.
	 * @return the createdAt
	 */
	public Date getCreatedAt();
	/**
	 * The KudoRank, which is an integer from 1 to 10. <br>
	 * Higher ranks are better. KudoRanks are assigned on a curve, <br>
	 * with a certain fraction of people receiving each KudoRank.
	 * @return the kudoRank
	 */
	public int getKudoRank();
	/**
	 * An integer which orders all participants. <br>
	 * The person with `position` equals 1 is the highest-ranked person on Ohloh.
	 * @return the position
	 */
	public long getPosition();
	/**
	 * The total number of partcipants in the most recent KudoScore calculations. <br>
	 * The person whose `position` equals `max_position` is the lowest-ranked <br>
	 * person on Ohloh.
	 * @return the maxPosition
	 */
	public long getMaxPosition();
	/**
	 * The change in this person’s position since the previous kudo score calculations. <br>
	 * Here, a negative number represents an improvement, <br>
	 * since lower positions are better.
	 * @return the positionDelta
	 */
	public float getPositionDelta();
}
