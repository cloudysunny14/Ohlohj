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
package ohlohj.internal.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

import ohlohj.OhlohException;
import ohlohj.conf.Configuration;

import static ohlohj.internal.http.RequestMethod.*;

/**
 * 
 * @author cloudysunny14
 *
 */
public class HttpClientImpl implements HttpClient, HttpResponseCode, java.io.Serializable {
	private static final long serialVersionUID = 5242014531735070837L;
	private Configuration conf;
    
    public HttpClientImpl(Configuration conf) {
        this.conf = conf;
    }
    
    public HttpResponse request(HttpRequest req) throws OhlohException {
        int retriedCount;
        HttpResponse res = null;
        for (retriedCount = 0; retriedCount < conf.getRetryCount(); retriedCount++) {
            int responseCode = -1;
            try {
                HttpURLConnection con;
                OutputStream os = null;
                con = getConnection(req.getUrl());
                setHeaders(req, con);
                con.setDoInput(true);
                con.setRequestMethod(req.getMethod().name());
                if(req.getMethod()==POST){
                	con.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    String postParam = HttpParameter.encodeParameters(req.getParams());
                    byte[] bytes = postParam.getBytes("UTF-8");
                    con.setRequestProperty("Content-Length",
                            Integer.toString(bytes.length));
                    con.setDoOutput(true);
                    os = con.getOutputStream();
                    os.write(bytes);
                    os.flush();
                    os.close();
                }
                res = new HttpResponseImpl(con);
                responseCode = con.getResponseCode();
                if (responseCode < OK || 
                		(responseCode != MOVED_PERMANENTLY && responseCode != FOUND && MULTIPLE_CHOICES <= responseCode)) {
                	throw new OhlohException(res.asString(), res);
                } else if(responseCode == MOVED_PERMANENTLY) {
                	req.setUrl(getRedirectUrl(res));
                }
                else {
                    break;
                }
            } catch (IOException ioe) {
            	if (retriedCount == conf.getRetryCount()) {
                    throw new OhlohException(ioe.getMessage(), res);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
                //ignore
            }
        }
        return res;
    }
    
    private void setHeaders(HttpRequest req, HttpURLConnection connection) {
        if (req.getOAuth() != null) {
            connection.addRequestProperty("Authorization",
            		req.getOAuth().getAuthorizationHeader(req));
        }
        if(conf.getGzipEnabled()) {
        	connection.addRequestProperty("User-Agent",
            		conf.getUserAgent());
        	connection.addRequestProperty("Accept-Encoding",
        			"gzip");
        }
    }
    
    private HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection con;
        if (conf.getHttpProxyUser() != null && !conf.getHttpProxyUser().equals("")) {
        	Authenticator.setDefault(new Authenticator() {
        		@Override
                protected PasswordAuthentication getPasswordAuthentication() {
        		if (getRequestorType().equals(RequestorType.PROXY)) {
        				return new PasswordAuthentication(conf.getHttpProxyUser(),
        						conf.getHttpProxyPassword().toCharArray());
        			} else {
        				return null;
        			}
        		}
            });
        	final Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress
                    .createUnresolved(conf.getHttpProxyHost(), conf.getHttpProxyPort()));
            con = (HttpURLConnection) new URL(url).openConnection(proxy);
        }
        else{
        	con = (HttpURLConnection) new URL(url).openConnection();
        }
        con.setInstanceFollowRedirects(false);
        return con;
    }
    
    private String getRedirectUrl(HttpResponse res) {
    	return res.getResponseHeader("Location");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpClientImpl that = (HttpClientImpl) o;
        if (!conf.equals(that.conf)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return conf.hashCode();
    }

    @Override
    public String toString() {
        return "HttpClientImpl{" +
                "conf=" + conf +
                '}';
    }
}
