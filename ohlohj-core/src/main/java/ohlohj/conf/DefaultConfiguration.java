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
package ohlohj.conf;

import ohlohj.internal.http.HttpParameter;

/**
 * 
 * @author cloudysunny14
 *
 */
public class DefaultConfiguration implements Configuration, java.io.Serializable{
	private static final long serialVersionUID = -831976714649050398L;
	private boolean isGAE;
	private String httpProxyHost;
	private String httpProxyUser;
	private String httpProxyPassword;
	private int httpProxyPort;
	private int httpConnectionTimeout;
	private int httpReadTimeout;
	private int retryCount;
	private int intervalSeconds;
	private String userAgent;
	private boolean gzipEnabled;
	private String oAuthRequestTokenUrl;
	private String oAuthAccessTokenUrl;
	private String oAuthAuthorizeUrl;
	private String oAuthCallbackUrl;
	private String oAuthConsumerKey;
	private String oAuthConsumerSecret;
	private String apiKey;
	
	static String gaeDetected;
	
	private static final HttpParameter[] NULL_PARAMETERS = new HttpParameter[0]; 
	
	static {
		// detecting Google App Engine
        try {
            Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
            gaeDetected = "true";
        } catch (ClassNotFoundException cnfe) {
            gaeDetected = "false";
        }
	}
	
	public DefaultConfiguration() {
		setGAE(Boolean.valueOf(gaeDetected));
		setHttpProxyHost(null);
		setHttpProxyUser(null);
		setHttpProxyPassword(null);
		setHttpProxyPort(8080);
		setHttpConnectionTimeout(20000);
		setHttpReadTimeout(10000);
		setRetryCount(3);
		setIntervalSeconds(5);
		setOAuthRequestTokenUrl("http://www.ohloh.net/oauth/request_token");
		setOAuthAccessTokenUrl("https://www.ohloh.net/oauth/access_token");
		setOAuthAuthorizeUrl("http://www.ohloh.net/oauth/authorize");
	}
	
	public boolean isGAE() {
		return isGAE;
	}

	public String getHttpProxyHost() {
		return httpProxyHost;
	}

	public String getHttpProxyUser() {
		return httpProxyUser;
	}

	public String getHttpProxyPassword() {
		return httpProxyPassword;
	}

	public int getHttpProxyPort() {
		return httpProxyPort;
	}

	public int getHttpConnectionTimeout() {
		return httpConnectionTimeout;
	}

	public int getHttpReadTimeout() {
		return httpReadTimeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public int getRetryIntervalSeconds() {
		return intervalSeconds;
	}
	
	public String getUserAgent() {
		return userAgent;
	}
	
	public boolean getGzipEnabled() {
		return gzipEnabled;
	}
	
	public String getOAuthRequestTokenUrl() {
		return oAuthRequestTokenUrl;
	}
	
	public String getOAuthAccessTokenUrl() {
		return oAuthAccessTokenUrl;
	}
	
	public String getOAuthAuthorizeUrl() {
		return oAuthAuthorizeUrl;
	}
	
	public String getOAuthCallbackUrl() {
		return oAuthCallbackUrl;
	}
	
	public String getOAuthConsumerKey() {
		return oAuthConsumerKey;
	}

	public String getOAuthConsumerSecret() {
		return oAuthConsumerSecret;
	}
	
	public HttpParameter[] getKeyParam() {
		if(apiKey!=null){
			return new HttpParameter[]{new HttpParameter("api_key", apiKey)};
		}
		return NULL_PARAMETERS;
	}

	protected void setGAE(boolean isGAE) {
		this.isGAE = isGAE;
	}

	protected void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}

	protected void setHttpProxyUser(String httpProxyUser) {
		this.httpProxyUser = httpProxyUser;
	}

	protected void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}

	protected void setHttpProxyPort(int httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}

	protected void setHttpConnectionTimeout(int httpConnectionTimeout) {
		this.httpConnectionTimeout = httpConnectionTimeout;
	}

	protected void setHttpReadTimeout(int httpReadTimeout) {
		this.httpReadTimeout = httpReadTimeout;
	}

	protected void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	protected void setIntervalSeconds(int intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}
	
	protected void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
		setGzipEnabled(true);
	}
	
	private void setGzipEnabled(boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}
	
	protected void setOAuthRequestTokenUrl(String oAuthRequestTokenUrl) {
		this.oAuthRequestTokenUrl = oAuthRequestTokenUrl;
	}
	
	protected void setOAuthAccessTokenUrl(String oAuthAccessTokenUrl) {
		this.oAuthAccessTokenUrl = oAuthAccessTokenUrl;
	}
	
	protected void setOAuthAuthorizeUrl(String oAuthAuthorizeUrl) {
		this.oAuthAuthorizeUrl = oAuthAuthorizeUrl;
	}
	
	protected void setOAuthCallbackUrl(String oAuthCallbackUrl) {
		this.oAuthCallbackUrl = oAuthCallbackUrl;
	}
	
	protected void setOAuthConsumerKey(String oAuthConsumerKey) {
		this.oAuthConsumerKey = oAuthConsumerKey;
	}
	
	protected void setOAuthConsumerSecret(String oAuthConsumerSecret ) {
		this.oAuthConsumerSecret = oAuthConsumerSecret;
	}
	
	protected void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultConfiguration that = (DefaultConfiguration) o;

        if (isGAE != that.isGAE) return false;
        if (httpProxyHost != that.httpProxyHost) return false;
        if (httpProxyUser != that.httpProxyUser) return false;
        if (httpProxyPassword != that.httpProxyPassword) return false;
        if (httpProxyPort != that.httpProxyPort) return false;
        if (httpConnectionTimeout != that.httpConnectionTimeout) return false;
        if (httpReadTimeout != that.httpReadTimeout) return false;
        if (retryCount != that.retryCount) return false;
        if (intervalSeconds != that.intervalSeconds) return false;
        if (userAgent != that.userAgent) return false;
        if (gzipEnabled != that.gzipEnabled) return false;
        if (oAuthRequestTokenUrl != that.oAuthRequestTokenUrl) return false;
        if (oAuthAccessTokenUrl != that.oAuthAccessTokenUrl) return false;
        if (oAuthAuthorizeUrl != that.oAuthAuthorizeUrl) return false;
        if (oAuthCallbackUrl != that.oAuthCallbackUrl) return false;
        if (oAuthConsumerKey != that.oAuthConsumerKey) return false;
        if (oAuthConsumerSecret != that.oAuthConsumerSecret) return false;
        if (apiKey != that.apiKey) return false;
        if (httpProxyHost != null ? !httpProxyHost.equals(that.httpProxyHost) : that.httpProxyHost != null)
            return false;
        if (httpProxyUser != null ? !httpProxyUser.equals(that.httpProxyUser) : that.httpProxyUser != null)
            return false;
        if (httpProxyPassword != null ? !httpProxyPassword.equals(that.httpProxyPassword) : that.httpProxyPassword != null)
            return false;
        if (userAgent != null ? !userAgent.equals(that.userAgent) : that.userAgent != null)
            return false;
        if (oAuthRequestTokenUrl != null ? !oAuthRequestTokenUrl.equals(that.oAuthRequestTokenUrl) : that.oAuthRequestTokenUrl != null)
            return false;
        if (oAuthAccessTokenUrl != null ? !oAuthAccessTokenUrl.equals(that.oAuthAccessTokenUrl) : that.oAuthAccessTokenUrl != null)
            return false;
        if (oAuthAuthorizeUrl != null ? !oAuthAuthorizeUrl.equals(that.oAuthAuthorizeUrl) : that.oAuthAuthorizeUrl != null)
            return false;
        if (oAuthCallbackUrl != null ? !oAuthCallbackUrl.equals(that.oAuthCallbackUrl) : that.oAuthCallbackUrl != null)
            return false;
        if (oAuthConsumerKey != null ? !oAuthConsumerKey.equals(that.oAuthConsumerKey) : that.oAuthConsumerKey != null)
            return false;
        if (oAuthConsumerSecret != null ? !oAuthConsumerSecret.equals(that.oAuthConsumerSecret) : that.oAuthConsumerSecret != null)
            return false;
        if (apiKey != null ? !apiKey.equals(that.apiKey) : that.apiKey != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (isGAE ? 1 : 0);
        result = 31 * result + (httpProxyHost != null ? httpProxyHost.hashCode() : 0);
        result = 31 * result + (httpProxyUser != null ? httpProxyUser.hashCode() : 0);
        result = 31 * result + httpProxyPort;
        result = 31 * result + httpConnectionTimeout;
        result = 31 * result + httpReadTimeout;
        result = 31 * result + retryCount;
        result = 31 * result + intervalSeconds;
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (gzipEnabled ? 1 : 0);
        result = 31 * result + (oAuthRequestTokenUrl != null ? oAuthRequestTokenUrl.hashCode() : 0);
        result = 31 * result + (oAuthAccessTokenUrl != null ? oAuthAccessTokenUrl.hashCode() : 0);
        result = 31 * result + (oAuthAuthorizeUrl != null ? oAuthAuthorizeUrl.hashCode() : 0);
        result = 31 * result + (oAuthCallbackUrl != null ? oAuthCallbackUrl.hashCode() : 0);
        result = 31 * result + (oAuthConsumerKey != null ? oAuthConsumerKey.hashCode() : 0);
        result = 31 * result + (oAuthConsumerSecret != null ? oAuthConsumerSecret.hashCode() : 0);
        result = 31 * result + (apiKey != null ? apiKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DefaultConfiguration{" +
                "isGAE=" + isGAE +
                ", httpProxyHost='" + httpProxyHost +
                ", httpProxyUser='" + httpProxyUser +
                ", httpProxyPort='" + httpProxyPort +
                ", httpConnectionTimeout='" + httpConnectionTimeout +
                ", httpReadTimeout='" + httpReadTimeout +
                ", retryCount='" + retryCount +
                ", intervalSeconds='" + intervalSeconds +
                ", userAgent='" + userAgent +
                ", gzipEnabled='" + gzipEnabled +
                ", oAuthRequestTokenUrl='" + oAuthRequestTokenUrl +
                ", oAuthAccessTokenUrl='" + oAuthAccessTokenUrl +
                ", oAuthAuthorizeUrl='" + oAuthAuthorizeUrl +
                ", oAuthCallbackUrl='" + oAuthCallbackUrl +
                ", oAuthConsumerKey='" + oAuthConsumerKey +
                ", oAuthConsumerSecret='" + oAuthConsumerSecret +
                ", apiKey='" + apiKey +
                '}';
    }
}
