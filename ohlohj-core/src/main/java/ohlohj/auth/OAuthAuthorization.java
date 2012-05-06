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
package ohlohj.auth;

import static ohlohj.internal.http.RequestMethod.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import ohlohj.OhlohException;
import ohlohj.conf.Configuration;
import ohlohj.internal.http.HttpClient;
import ohlohj.internal.http.HttpClientImpl;
import ohlohj.internal.http.HttpParameter;
import ohlohj.internal.http.HttpRequest;
import ohlohj.utils.StringUtils;

/**
 * 
 * @author cloudysunny14
 *
 */
public class OAuthAuthorization implements java.io.Serializable{
	private static final long serialVersionUID = 3531246622190384257L;
	private final Configuration conf;
	private transient static HttpClient http;
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final HttpParameter OAUTH_SIGNATURE_METHOD = 
    		new HttpParameter("oauth_signature_method", "HMAC-SHA1");
	private String consumerKey;
	private String consumerSecret;
	private OAuthToken oAuthToken;
	private String realm;
	
	/**
	 * Create OAuthAuthorization with the Configuration.
	 * @param conf
	 */
	public OAuthAuthorization(Configuration conf) {
		this.conf = conf;
		http = new HttpClientImpl(conf);
		setOAuthConsumer(conf.getOAuthConsumerKey(), conf.getOAuthConsumerSecret());
	}
	
	private void setOAuthConsumer(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey != null ? consumerKey : "";
        this.consumerSecret = consumerSecret != null ? consumerSecret : "";
    }
	
	/**
	 * Set the OAuthToken to create OAuthAuthorizaiont. 
	 * If AccessToken and AccessTokenSecret provided by Ohloh.
	 * @param oAuthToken
	 */
	public void setOAuthToken(OAuthToken oAuthToken) {
		this.oAuthToken = oAuthToken;
	}
	
	/**
	 * Returns the configuration.
	 * @return the Configuration.
	 */
	public Configuration getConfiguration() {
		return conf;
	}
	
	/**
     * Retrieves a request token
     * @return generated request token.
	 * @throws OhlohException when network unavailable.
     * @throws IllegalStateException access token is already available
     * @see <a href="http://oauth.net/core/1.0a/#auth_step1">OAuth Core 1.0a - 6.1.  Obtaining an Unauthorized Request Token</a>
     */
	public OAuthRequestToken getOAuthRequestToken() throws OhlohException {
		if (oAuthToken instanceof OAuthAccessToken) {
            throw new IllegalStateException("Access token already available.");
        }
		HttpRequest httpRequest = 
				new HttpRequest(POST, conf.getOAuthRequestTokenUrl(), 
						null, this);
		return new OAuthRequestToken(http.request(httpRequest), conf);
	}
	
	/**
	 * Returns an access token associated with this instance.<br>
     * If no access token is associated with this instance, this will retrieve a new access token.
     * @return access token
     * @throws IllegalStateException when RequestToken has never been acquired
     * @throws OhlohException when network unavailable.
     * @see <a href="http://oauth.net/core/1.0a/#auth_step2">OAuth Core 1.0a - 6.2.  Obtaining User Authorization</a>
	 * @return OAuthAccessToken. 
	 */
	public OAuthAccessToken getOAuthAccessToken() throws OhlohException {
		ensureTokenIsAvailable();
		if (oAuthToken instanceof OAuthAccessToken) {
            return (OAuthAccessToken) oAuthToken;
        }
		HttpRequest httpRequest = 
				new HttpRequest(POST, conf.getOAuthAccessTokenUrl(), 
						null, this);
		oAuthToken = new OAuthAccessToken(http.request(httpRequest), conf);
		return (OAuthAccessToken)oAuthToken;
	}
	
	public String getAuthorizationHeader(HttpRequest req) {
        return generateAuthorizationHeader(req.getMethod().name(), 
        		req.getUrl(), req.getParams(), oAuthToken);
    }
	
	private void ensureTokenIsAvailable() {
        if (null == oAuthToken) {
            throw new IllegalStateException("No Token available.");
        }
    }

	/**
     * Sets the OAuth realm
     *
     * @param realm OAuth realm
     */
    public void setOAuthRealm(String realm) {
        this.realm = realm;
    }
    
	private static Random RAND = new Random();
	
	/*package*/ String generateAuthorizationHeader(String method, String url, HttpParameter[] params, OAuthToken token) {
        long timestamp = System.currentTimeMillis() / 1000;
        long nonce = timestamp + RAND.nextInt();
        return generateAuthorizationHeader(method, url, params, String.valueOf(nonce), String.valueOf(timestamp), token);
    }
	
	/*package*/ String generateAuthorizationHeader(String method, String url, HttpParameter[] params, String nonce, String timestamp, OAuthToken otoken) {
        if (null == params) {
            params = new HttpParameter[0];
        }
        List<HttpParameter> oauthHeaderParams = new ArrayList<HttpParameter>(5);
        oauthHeaderParams.add(new HttpParameter("oauth_consumer_key", consumerKey));
        oauthHeaderParams.add(OAUTH_SIGNATURE_METHOD);
        oauthHeaderParams.add(new HttpParameter("oauth_timestamp", timestamp));
        oauthHeaderParams.add(new HttpParameter("oauth_nonce", nonce));
        oauthHeaderParams.add(new HttpParameter("oauth_version", "1.0"));
        if (oAuthToken != null) {
            oauthHeaderParams.add(new HttpParameter("oauth_token", otoken.getToken()));
        }
        List<HttpParameter> signatureBaseParams = new ArrayList<HttpParameter>(oauthHeaderParams.size() + params.length);
        signatureBaseParams.addAll(oauthHeaderParams);
        signatureBaseParams.addAll(toParamList(params));
        parseGetParameters(url, signatureBaseParams);
        StringBuffer base = new StringBuffer(method).append("&")
                .append(HttpParameter.encode(constructRequestURL(url))).append("&");
        base.append(HttpParameter.encode(normalizeRequestParameters(signatureBaseParams)));
        String oauthBaseString = base.toString();
        String signature = generateSignature(oauthBaseString, otoken);
        
        oauthHeaderParams.add(new HttpParameter("oauth_signature", signature));

        // http://oauth.net/core/1.0/#rfc.section.9.1.1
        if (realm != null) {
            oauthHeaderParams.add(new HttpParameter("realm", realm));
        }
        return "OAuth " + encodeParameters(oauthHeaderParams, ",", true);
    }

	public static List<HttpParameter> toParamList(HttpParameter[] params) {
        List<HttpParameter> paramList = new ArrayList<HttpParameter>(params.length);
        paramList.addAll(Arrays.asList(params));
        return paramList;
    }
	
	private void parseGetParameters(String url, List<HttpParameter> signatureBaseParams) {
        int queryStart = url.indexOf("?");
        if (-1 != queryStart) {
            String[] queryStrs = StringUtils.split(url.substring(queryStart + 1), "&");
            try {
                for (String query : queryStrs) {
                    String[] split = StringUtils.split(query, "=");
                    if (split.length == 2) {
                        signatureBaseParams.add(
                                new HttpParameter(URLDecoder.decode(split[0],
                                        "UTF-8"), URLDecoder.decode(split[1],
                                        "UTF-8")));
                    } else {
                        signatureBaseParams.add(
                                new HttpParameter(URLDecoder.decode(split[0],
                                        "UTF-8"), ""));
                    }
                }
            } catch (UnsupportedEncodingException ignore) {
            }

        }

    }
	
	public static String constructRequestURL(String url) {
        int index = url.indexOf("?");
        if (-1 != index) {
            url = url.substring(0, index);
        }
        int slashIndex = url.indexOf("/", 8);
        String baseURL = url.substring(0, slashIndex).toLowerCase();
        int colonIndex = baseURL.indexOf(":", 8);
        if (-1 != colonIndex) {
            if (baseURL.startsWith("http://") && baseURL.endsWith(":80")) {
                baseURL = baseURL.substring(0, colonIndex);
            } else if (baseURL.startsWith("https://") && baseURL.endsWith(":443")) {
                baseURL = baseURL.substring(0, colonIndex);
            }
        }
        url = baseURL + url.substring(slashIndex);

        return url;
    }
	
	public static String normalizeRequestParameters(HttpParameter[] params) {
        return normalizeRequestParameters(toParamList(params));
    }

    public static String normalizeRequestParameters(List<HttpParameter> params) {
        Collections.sort(params);
        return encodeParameters(params);
    }
    
    public static String encodeParameters(List<HttpParameter> httpParams) {
        return encodeParameters(httpParams, "&", false);
    }
    
    public static String encodeParameters(List<HttpParameter> httpParams, String splitter, boolean quot) {
        StringBuffer buf = new StringBuffer();
        for (HttpParameter param : httpParams) {
        	if (buf.length() != 0) {
        		if (quot) {
        			buf.append("\"");
        		}
        		buf.append(splitter);
        	}
        	buf.append(HttpParameter.encode(param.getName())).append("=");
        	if (quot) {
        		buf.append("\"");
        	}
        	buf.append(HttpParameter.encode(param.getValue()));
        }
        if (buf.length() != 0) {
            if (quot) {
                buf.append("\"");
            }
        }
        return buf.toString();
    }
    
    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data  the data to be signed
     * @param token the token
     * @return signature
     * @see <a href="http://oauth.net/core/1.0a/#rfc.section.9.2.1">OAuth Core - 9.2.1.  Generating Signature</a>
     */
    /*package*/ String generateSignature(String data, OAuthToken token) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1);
            SecretKeySpec spec;
            if (null == token) {
                String oauthSignature = HttpParameter.encode(consumerSecret) + "&";
                spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
            } else {
                spec = token.getSecretKeySpec();
                if (null == spec) {
                    String oauthSignature = HttpParameter.encode(consumerSecret) + "&" + HttpParameter.encode(token.getTokenSecret());
                    spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
                    token.setSecretKeySpec(spec);
                }
            }            
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException ike) {
            throw new AssertionError(ike);
        } catch (NoSuchAlgorithmException nsae) {
            throw new AssertionError(nsae);
        }
        return StringUtils.encodeToBase64Encode(byteHMAC);
    }

    /*package*/String generateSignature(String data) {
        return generateSignature(data, null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuthSupport)) return false;

        OAuthAuthorization that = (OAuthAuthorization) o;

        if (consumerKey != null ? !consumerKey.equals(that.consumerKey) : that.consumerKey != null)
            return false;
        if (consumerSecret != null ? !consumerSecret.equals(that.consumerSecret) : that.consumerSecret != null)
            return false;
        if (oAuthToken != null ? !oAuthToken.equals(that.oAuthToken) : that.oAuthToken != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consumerKey != null ? consumerKey.hashCode() : 0;
        result = 31 * result + (consumerSecret != null ? consumerSecret.hashCode() : 0);
        result = 31 * result + (oAuthToken != null ? oAuthToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OAuthAuthorization{" +
                "consumerKey='" + consumerKey +
                ", oauthToken=" + oAuthToken +
                '}';
    }

}
