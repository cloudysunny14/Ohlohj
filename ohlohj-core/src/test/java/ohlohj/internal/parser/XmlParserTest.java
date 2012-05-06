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
package ohlohj.internal.parser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import ohlohj.internal.parser.XMLParser;

import org.junit.Test;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XmlParserTest {

    private static ThreadLocal<Map<String, SimpleDateFormat>> formatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };
    
    private static String fileToString(File file) throws IOException {
    	BufferedReader br = null;
    	try {
    		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    		StringBuffer sb = new StringBuffer();
    		int c;
    		while ((c = br.read()) != -1) {
    			sb.append((char) c);
    		}
    		return sb.toString();
    	} finally {
    		br.close();
    	}
    }

    @Test
    public void testParse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        formatMap.get().put("yyyy-MM-dd'T'HH:mm:ss'Z'", sdf);
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        		"<response>" +
        		"<status>success</status>" +
        		"<result>" +
        		"<project>" +
        		"<id>503</id>" +
        		"<name>SimpleTest</name>" +
        		"<created_at>2006-10-10T15:51:31Z</created_at>" +
        		"<updated_at>2012-02-22T19:01:34Z</updated_at>" +
        		"<description>SimpleTest is a unit tester, Web tester, and dynamic mock objects framework for PHP. The test structure is similar to JUnit/PHPUnit. Used as a Web tester, you can navigate sites and submit forms as if you were using a Web browser.</description>" +
        		"<homepage_url>http://simpletest.org/</homepage_url>" +
        		"<download_url>http://simpletest.org/en/download.html</download_url>" +
        		"<url_name>simpletest</url_name>" +
        		"<medium_logo_url>https://s3.amazonaws.com/cloud.ohloh.net/attachments/19951/simpletest-logo_med.png</medium_logo_url>" +
        		"<small_logo_url>https://s3.amazonaws.com/cloud.ohloh.net/attachments/19951/simpletest-logo_small.png</small_logo_url>" +
        		"<user_count>65</user_count>" +
        		"<average_rating>4.19048</average_rating>" +
        		"<rating_count>21</rating_count>" +
        		"<analysis_id>1759638</analysis_id>" +
        		"<analysis>" +
        		"<id>1759638</id>" +
        		"<project_id>503</project_id>" +
        		"<updated_at>2011-03-09T15:35:42Z</updated_at>" +
        		"<logged_at>2011-01-17T08:54:32Z</logged_at>" +
        		"<min_month>2003-03-01T00:00:00Z</min_month>" +
        		"<max_month>2010-12-01T00:00:00Z</max_month>" +
        		"<twelve_month_contributor_count>3</twelve_month_contributor_count>" +
        		"<total_code_lines>119187</total_code_lines>" +
        		"<main_language_id>2</main_language_id>" +
        		"<main_language_name>PHP</main_language_name>" +
        		"</analysis>" +
        		"<licenses>" +
        		"<license>" +
        		"<name>lgpl</name>" +
        		"<nice_name>GNU Library or &quot;Lesser&quot; GPL (LGPL)</nice_name>" +
        		"</license>" +
        		"</licenses>" +
        		"</project>" +
        		"</result>" +
        		"</response>";
        XMLParser parser = new XMLParser(xmlString);
        String status = parser.getString("response/status/text()");
        assertTrue(status.equals("success"));
        String id = parser.getString("response/result/project/id/text()");
        assertTrue(id.equals("503"));
        String name = parser.getString("response/result/project/name/text()");
        assertTrue(name.equals("SimpleTest"));
        Date createAt = parser.getDate("response/result/project/created_at/text()");
        assertTrue(createAt.equals(sdf.parse("2006-10-10T15:51:31Z")));
        int userCount = parser.getInt("response/result/project/user_count/text()");
        assertTrue(userCount == 65);
        long lines = parser.getLong("response/result/project/analysis/total_code_lines/text()");
        assertTrue(lines == 119187);
        double rating = parser.getDouble("response/result/project/average_rating/text()");
        assertTrue(rating == 4.19048);
    }
    
    @Test
    public void testParseList() 
    		throws IOException, TransformerFactoryConfigurationError, TransformerException, ParseException {
    	SimpleDateFormat sdf = formatMap.get().get("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	String xmlString = fileToString(new File("./src/test/java/accounts.xml"));
    	XMLParser parser = new XMLParser(xmlString);
    	NodeList list = parser.getNodeList("response/result/account");
    	int length = list.getLength();
    	assertTrue(length == 10);
    	Node node = list.item(0);
    	XMLParser childParser = new XMLParser(XMLParser.getXmlStringFromNode(node));
    	long id = childParser.getLong("account/id/text()");
        assertTrue(id == 154666l);
        String name = childParser.getString("account/name/text()");
        assertTrue(name.equals("geminids"));
        Date createAt = childParser.getDate("account/created_at/text()");
        assertTrue(createAt.equals(sdf.parse("2012-03-31T03:57:07Z")));
        int postsCount = childParser.getInt("account/posts_count/text()");
        assertTrue(postsCount == 0);
    }
}
