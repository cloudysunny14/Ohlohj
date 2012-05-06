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
package ohlohj.appengine;

import java.util.HashMap;
import java.util.Map;

import com.google.appengine.api.NamespaceManager;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Environment;

/**
 * @author cloudysunny14
 *
 */
public class TestEnvironment implements ApiProxy.Environment {
	
	String appID = "Test Development";
	String versionId = "1.0.0";
	String authDomain = "cloudysunny14.org";
	String email = "cloudysunny14@gmail.com";
	boolean admin = false;
	Map<String, Object> attributes = new HashMap<String, Object>();
	
	public TestEnvironment(Environment env) throws NullPointerException {
        if (env == null) {
            throw new NullPointerException(
                "The other parameter must not be null.");
        }
        appID = env.getAppId();
        versionId = env.getVersionId();
        authDomain = env.getAuthDomain();
        email = env.getEmail();
        admin = env.isAdmin();
        attributes = env.getAttributes();
    }
	
	public TestEnvironment() {
		attributes.put("com.google.appengine.server_url_key", "dummy");
	}

	public String getAppId() {
		return this.appID;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public String getAuthDomain() {
		return authDomain;
	}

	public String getEmail() {
		return email;
	}

	public String getRequestNamespace() {
		return NamespaceManager.get();
	}

	public String getVersionId() {
		return versionId;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isLoggedIn() {
		return email!=null;
	}
	
	protected void assertNotProduction() throws IllegalStateException {
		if (AppEngineTestUtil.isProduction()) {
			throw new IllegalStateException(
					 "This feature is not supported on production server.");
	    }
	}
}
