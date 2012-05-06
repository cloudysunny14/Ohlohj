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

import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 */
public class KudoScoreImpl implements KudoScore, java.io.Serializable{
	private static final long serialVersionUID = -1667760457410082457L;
	private Date createdAt;
	private int kudoRank;
	private long position;
	private long maxPosition;
	private float positionDelta;
	
	private KudoScoreImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		createdAt = parser.getDate("kudo_score/created_at/text()");
		kudoRank = parser.getInt("kudo_score/kudo_rank/text()");
		position = parser.getLong("kudo_score/position/text()");
		maxPosition = parser.getLong("kudo_score/max_position/text()");
		positionDelta = parser.getFloat("kudo_score/position_delta/text()");
	}
	/**
     * {@inheritDoc}
     */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
     * {@inheritDoc}
     */
	public int getKudoRank() {
		return kudoRank;
	}
	/**
     * {@inheritDoc}
     */
	public long getPosition() {
		return position;
	}
	/**
     * {@inheritDoc}
     */
	public long getMaxPosition() {
		return maxPosition;
	}
	/**
     * {@inheritDoc}
     */
	public float getPositionDelta() {
		return positionDelta;
	}
	
	public static KudoScore createKudoScore(XMLParser parser) {
		return new KudoScoreImpl(parser);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KudoScoreImpl that = (KudoScoreImpl) o;

        if (createdAt != that.createdAt) return false;
        if (kudoRank != that.kudoRank) return false;
        if (position != that.position)return false;
        if (maxPosition != that.maxPosition)return false;
        if (Float.compare(that.getPositionDelta(), positionDelta) != 0) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + kudoRank;
        result = 31 * result + (int) (position ^ (position >>> 32));
        result = 31 * result + (int) (maxPosition ^ (maxPosition >>> 32));
        int temp = positionDelta != +0.0f ? Float.floatToIntBits(positionDelta) : 0;
        result = 31 * result + temp;
        return result;
    }

    @Override
    public String toString() {
        return "KudoScoreImpl{" +
                "createdAt=" + createdAt +
                ", kudoRank='" + kudoRank +
                ", position='" + position +
                ", maxPosition='" + maxPosition +
                ", positionDelta='" + positionDelta +
                '}';
    }
}
