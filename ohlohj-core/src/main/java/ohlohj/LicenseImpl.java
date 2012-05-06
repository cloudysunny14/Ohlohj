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

import ohlohj.internal.parser.XMLParser;

/**
 * 
 * @author cloudysunny14
 *
 */
public class LicenseImpl implements License, java.io.Serializable{
	private static final long serialVersionUID = -8573810244717992546L;
	private String name;
	private String niceName;
	
	private LicenseImpl(XMLParser parser) {
		init(parser);
	}
	
	private void init(XMLParser parser) {
		name = parser.getString("licenses/license/name/text()");
		niceName = parser.getString("licenses/license/nice_name/text()");
	}
	/**
     * {@inheritDoc}
     */
	public String getName() {
		return name;
	}
	/**
     * {@inheritDoc}
     */
	public String getNiceName() {
		return niceName;
	}
	
	public static License createLicense(XMLParser parser) {
		return new LicenseImpl(parser);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseImpl that = (LicenseImpl) o;

        if (name != that.name) return false;
        if (niceName != that.niceName)return false;
        if (!name.equals(that.name))return false;
        if (!niceName.equals(that.niceName))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (niceName != null ? niceName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LicenseImpl{" +
                "name=" + name +
                ", niceName='" + niceName +
                '}';
    }
}
