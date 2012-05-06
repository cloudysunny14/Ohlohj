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

/**
 * 
 * @author cloudysunny14
 *
 */
public class ConfigurationBuilder {

	private DefaultConfiguration configurationBean = new DefaultConfiguration();
	
	public ConfigurationBuilder setHttpProxyHost(String httpProxyHost) {
		checkNotBuilt();
		configurationBean.setHttpProxyHost(httpProxyHost);
		return this;
	}

	public ConfigurationBuilder setHttpProxyUser(String httpProxyUser) {
		checkNotBuilt();
		configurationBean.setHttpProxyUser(httpProxyUser);
		return this;
	}

	public ConfigurationBuilder setHttpProxyPassword(String httpProxyPassword) {
		checkNotBuilt();
		configurationBean.setHttpProxyPassword(httpProxyPassword);
		return this;
	}

	public ConfigurationBuilder setHttpProxyPort(int httpProxyPort) {
		checkNotBuilt();
		configurationBean.setHttpProxyPort(httpProxyPort);
		return this;
	}

	public ConfigurationBuilder setHttpConnectionTimeout(int httpConnectionTimeout) {
		checkNotBuilt();
		configurationBean.setHttpConnectionTimeout(httpConnectionTimeout);
		return this;
	}

	public ConfigurationBuilder setHttpReadTimeout(int httpReadTimeout) {
		checkNotBuilt();
		configurationBean.setHttpReadTimeout(httpReadTimeout);
		return this;
	}

	public ConfigurationBuilder setRetryCount(int retryCount) {
		checkNotBuilt();
		configurationBean.setRetryCount(retryCount);
		return this;
	}

	public ConfigurationBuilder setIntervalSeconds(int intervalSeconds) {
		checkNotBuilt();
		configurationBean.setIntervalSeconds(intervalSeconds);
		return this;
	}
	
	public ConfigurationBuilder setUserAgent(String userAgent) {
		checkNotBuilt();
		configurationBean.setUserAgent(userAgent);
		return this;
	}
	
	public ConfigurationBuilder setOAuthConsumerKey(String consumerKey) {
		checkNotBuilt();
		configurationBean.setOAuthConsumerKey(consumerKey);
		return this;
	}
	
	public ConfigurationBuilder setOAuthConsumerSecret(String consumerSecret) {
		checkNotBuilt();
		configurationBean.setOAuthConsumerSecret(consumerSecret);
		return this;
	}
	
	public ConfigurationBuilder setOAuthCallbackUrl(String callbackUrl) {
		checkNotBuilt();
		configurationBean.setOAuthCallbackUrl(callbackUrl);
		return this;
	}
	
	public ConfigurationBuilder setApiKey(String apiKey) {
		checkNotBuilt();
		configurationBean.setApiKey(apiKey);
		return this;
	}
	
	public Configuration build() {
        checkNotBuilt();
        try {
            return configurationBean;
        } finally {
            configurationBean = null;
        }
    }
	
	private void checkNotBuilt() {
        if (configurationBean == null) {
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }
}
