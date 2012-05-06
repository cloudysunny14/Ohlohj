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

import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 
 * @author cloudysunny14
 *
 */
public class XMLParser {
    
    private Document document;
    private static XPath xpath =
            new com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl().newXPath();
    
    private static ThreadLocal<Map<String, SimpleDateFormat>> formatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    
    public XMLParser(String xmlString) {
        document = stringToDocument(xmlString);
    }
    
    private Object get(String expression, QName resultType) {
        Object result = null;
        try {
            result =
                xpath.evaluate(expression, document, resultType);
        } catch (Throwable t) {
            throw new IllegalStateException("Xpath can not evaluate.");
        }
        return result;
    }

    private Object get(XPathExpression expr, QName resultType) {
        Object result = null;
        try {
            result = expr.evaluate(document, resultType);
        } catch (Throwable t) {
        	throw new IllegalStateException("Xpath can not evaluate.");
        }
        return result;
    }
    
    public Node getNode(String expression) {
        return (Node) get(expression, XPathConstants.NODE);
    }

	@SuppressWarnings("unused")
	private Node getNode(XPathExpression expr) {
        return (Node) get(expr, XPathConstants.NODE);
    }

    public NodeList getNodeList(String expression) {
        return (NodeList) get(expression, XPathConstants.NODESET);
    }

    public NodeList getNodeList(XPathExpression expr) {
        return (NodeList) get(expr, XPathConstants.NODESET);
    }
    
    private static Document stringToDocument(String xml) {
        Document result = null;
        try {
            DocumentBuilderFactory domFactory =
                DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            result = builder.parse(new InputSource(new StringReader(xml)));
        } catch (Throwable t) {
        	throw new IllegalStateException("Xpath can not evaluate.");
        }
        return result;
    }
    
    public String getString(String nodeString) {
        Node node = getNode(nodeString);
        if(node!=null) {
        	return node.getNodeValue();
        }
        else {
        	return "";
        }
    }
    
    public int getInt(String nodeString) {
        Node node = getNode(nodeString);
        if(node!=null){
        	return Integer.parseInt(node.getNodeValue());
        }
        else{
        	return 0;
        }
    }
    
    public long getLong(String nodeString) {
        Node node = getNode(nodeString);
        if(node!=null){
        	return Long.parseLong(node.getNodeValue());
        }
        else{
        	return 0;
        }
    }
    
    public float getFloat(String nodeString) {
    	Node node = getNode(nodeString);
        if(node!=null){
        	return Float.parseFloat(node.getNodeValue());
        }
        else{
        	return 0;
        }
    }
    
    public double getDouble(String nodeString) {
        Node node = getNode(nodeString);
        if(node!=null){
        	return Double.parseDouble(node.getNodeValue());
        }
        else{
        	return 0;
        }
    }
    
    public Date getDate(String nodeString) {
        String dateString = getString(nodeString);
        SimpleDateFormat sdf = formatMap.get().get("yyyy-MM-dd'T'HH:mm:ss'Z'");
        if (null == sdf) {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            formatMap.get().put("yyyy-MM-dd'T'HH:mm:ss'Z'", sdf);
        }
        try {
            return sdf.parse(dateString);
        } catch (ParseException pe) {
            //ignore
        }
        return null;
    }
    
    public static String getXmlStringFromNode(Node node) 
    		throws TransformerFactoryConfigurationError, TransformerException {
    	StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return writer.toString();
    }
}
