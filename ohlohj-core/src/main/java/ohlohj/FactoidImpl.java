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

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.parser.XMLParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author cloudysunny14
 *
 */
public class FactoidImpl implements Factoid, java.io.Serializable{
	private static final long serialVersionUID = -3456398002425668959L;
	private long id;
	private long analysisId;
	private String type;
	private String description;
	private int severity;
	
	private FactoidImpl(XMLParser parser, boolean isList) {
		if(isList) {
			listInit(parser);
		}
		else{
			init(parser);
		}
	}
	
	private void init(XMLParser parser) {
		id = parser.getLong("response/result/factoid/id/text()");
		analysisId = parser.getLong("response/result/factoid/analysis_id/text()");
		type = parser.getString("response/result/factoid/type/text()");
		description = parser.getString("response/result/factoid/description/text()");
		severity = parser.getInt("response/result/factoid/severity/text()");
	}
	
	private void listInit(XMLParser parser) {
		id = parser.getLong("factoid/id/text()");
		analysisId = parser.getLong("factoid/analysis_id/text()");
		type = parser.getString("factoid/type/text()");
		description = parser.getString("factoid/description/text()");
		severity = parser.getInt("factoid/severity/text()");
	}
	/**
     * {@inheritDoc}
     */
	public long getId() {
		return id;
	}
	/**
     * {@inheritDoc}
     */
	public long getAnalysisId() {
		return analysisId;
	}
	/**
     * {@inheritDoc}
     */
	public String getType() {
		return type;
	}
	/**
     * {@inheritDoc}
     */
	public String getDescription() {
		return description;
	}
	/**
     * {@inheritDoc}
     */
	public int getSeverity() {
		return severity;
	}
	
	public static Factoid createFactoid(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		Factoid factoid = new FactoidImpl(parser, false);
		return factoid;
	}
	
	public static ResponseList<Factoid> createFactoidList(HttpResponse res) {
		if(null == res) {
			return null;
		}
		XMLParser parser = new XMLParser(res.asString());
		NodeList nodelist = parser.getNodeList("response/result/factoid");
		ResponseList<Factoid> list = 
				new ResponseListImpl<Factoid> (nodelist.getLength(), res);
		for(int i=0; i<nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
	    	try {
				XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
				Factoid factoid = new FactoidImpl(childParser, true);
				list.add(factoid);
			} catch (TransformerFactoryConfigurationError e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactoidImpl that = (FactoidImpl) o;

        if (id != that.id) return false;
        if (analysisId != that.analysisId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (analysisId ^ (analysisId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "FactoidImpl{" +
                "id=" + id +
                ", analysisId='" + analysisId +
                '}';
    }
}
