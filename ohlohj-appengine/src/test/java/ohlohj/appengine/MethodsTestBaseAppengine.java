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


import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.*;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.*;

/**
 * @author cloudysunny14
 *
 */
public class MethodsTestBaseAppengine {
	protected static final String API_PROXY_LOCAL_IMPL_CLASS_NAME =
	        "com.google.appengine.tools.development.ApiProxyLocalImpl";
	protected static final String LOCAL_SERVER_ENVIRONMENT_CLASS_NAME =
	        "com.google.appengine.tools.development.LocalServerEnvironment";
	public static boolean SIMULATE_PRODUCTION_LATENCIES = false;
	public static boolean ENFORCE_API_DEADLINES = false;
	private static final String RUNTIME = "com.google.appengine.runtime";
	private static final String ENVIRONMENT_KEY = RUNTIME + ".environment";
    public static final String PRODUCTION = "Production";
    public static final String DEVELOPMENT = "Development";
    protected static final String IMPL_DIR_NAME = "impl";
    protected static final String LOCAL_RUNTIME_LIB_NAME =
            "appengine-local-runtime.jar";
    
	protected URLFetchDelegate delegate;
	protected Delegate<Environment> originalDelegate;
	protected Environment environment;
	protected Environment originalEnvironment;
	protected static Delegate<Environment> apiProxyLocalImpl;
	
	static {
		if (!AppEngineTestUtil.isServer()) {
			ClassLoader loader = loadLibraries();
			prepareLocalServices(loader);
		}
	}
	
	protected static ClassLoader loadLibraries() {
        ClassLoader loader = MethodsTestBaseAppengine.class.getClassLoader();
        if (loader instanceof URLClassLoader) {
            File libDir = getLibDir();
            File implDir = new File(libDir, IMPL_DIR_NAME);
            List<URL> urls = new ArrayList<URL>();
            try {
                loader.loadClass(API_PROXY_LOCAL_IMPL_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                urls.add(getLibraryURL(implDir, LOCAL_RUNTIME_LIB_NAME));
            }
            loadLibraries(loader, urls);
        }
        return loader;
    }
	
    protected static URL getLibraryURL(File dir, String libName) {
        File file = new File(dir, libName);
        if (!file.exists()) {
            throw new IllegalArgumentException("The library("
                + libName
                + ") is not found in the directory("
                + dir.getAbsolutePath()
                + ").");
        }
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
        	return null;
        }
    }
    
	protected static void loadLibraries(ClassLoader loader, List<URL> urls) {
        if (urls.size() > 0) {
            try {
                Method m =
                    URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                m.setAccessible(true);
                for (URL url : urls) {
                    m.invoke(loader, url);
                }
            } catch (Throwable cause) {
            }
        }
    }
	protected static File getLibDir() {
		try {
        	return new File(URLDecoder.decode(ApiProxy.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getFile(), "UTF-8")).getParentFile().getParentFile();
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
    protected static void prepareLocalServices(ClassLoader loader) {
        try {
            Class<?> apiProxyLocalImplClass =
                loader.loadClass(API_PROXY_LOCAL_IMPL_CLASS_NAME);
            Class<?> localServerEnvironmentClass =
                loader.loadClass(LOCAL_SERVER_ENVIRONMENT_CLASS_NAME);
            Constructor<?> con =
                apiProxyLocalImplClass
                    .getDeclaredConstructor(localServerEnvironmentClass);
            con.setAccessible(true);
            InvocationHandler ih = new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    if (method.getName().equals("getAppDir")) {
                        // return new File("build/test-classes");
                        return new File("www-test");
                    }
                    if (method.getName().equals("getPort")) {
                        return 0;
                    }
                    if (method.getName().equals("simulateProductionLatencies")) {
                        return SIMULATE_PRODUCTION_LATENCIES;
                    }
                    if (method.getName().equals("enforceApiDeadlines")) {
                        return ENFORCE_API_DEADLINES;
                    }
                    Class<?> clazz = method.getReturnType();
                    if (clazz.isPrimitive()) {
                        if (clazz == boolean.class) {
                            return true;
                        }
                        if (clazz == char.class) {
                            return '\0';
                        }
                        if (clazz == byte.class) {
                            return (byte) 0;
                        }
                        if (clazz == short.class) {
                            return (short) 0;
                        }
                        if (clazz == int.class) {
                            return 0;
                        }
                        if (clazz == long.class) {
                            return (long) 0;
                        }
                        if (clazz == float.class) {
                            return (float) 0;
                        }
                        if (clazz == double.class) {
                            return (double) 0;
                        }
                    }
                    // if (method.getName().equals("enforceApiDeadlines")) {
                    // return true;
                    // }
                    return null;
                }
            };
            Object localServerEnvironment =
                Proxy.newProxyInstance(
                    loader,
                    new Class<?>[] { localServerEnvironmentClass },
                    ih);
            apiProxyLocalImpl =
                (Delegate<Environment>) con.newInstance(localServerEnvironment);
        } catch (Throwable cause) {
        }
    }
	
	protected Properties p = new Properties();
    protected String apiKey;
    protected String oAuthConsumerKey;
    protected String oAuthConsumerSec;
    protected String oAuthAccessToken;
    protected String oAuthAccessTokenSec;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		InputStream is = MethodsTestBaseAppengine.class.getResourceAsStream("test_configuration.properties");
		p.load(is);
		is.close();
		apiKey = p.getProperty("api_key");
		oAuthConsumerKey = p.getProperty("consumerkey");
		oAuthConsumerSec = p.getProperty("consumersec");
		oAuthAccessToken = p.getProperty("accesstoken");
		oAuthAccessTokenSec = p.getProperty("accesstokensec");
		
		originalDelegate = ApiProxy.getDelegate();
		originalEnvironment = ApiProxy.getCurrentEnvironment();
        if (isServer()) {
        	delegate = new URLFetchDelegate(originalDelegate);
            environment = new TestEnvironment(originalEnvironment);
        } else {
        	delegate = new URLFetchDelegate(originalDelegate != null ? originalDelegate : apiProxyLocalImpl);
            environment = new TestEnvironment();
        }
        ApiProxy.setDelegate(delegate);
        if (isProduction()) {
            ApiProxy.setEnvironmentForCurrentThread(environment);
        }
        else{
        	ApiProxy.setEnvironmentForCurrentThread(environment);
        }
	}
	
	@After
	public void tearDown() {
	    @SuppressWarnings("unchecked")
	    Delegate<Environment> delegate = ApiProxy.getDelegate();
	    if (delegate instanceof URLFetchDelegate) {
	      ApiProxy.setDelegate(((URLFetchDelegate) delegate).apiProxyLocal);
	    }
	    tearDownAppEngine();
	}
	
	private void tearDownAppEngine() {
	    ApiProxyLocal apiProxyLocal = (ApiProxyLocal) ApiProxy.getDelegate();
	    if (apiProxyLocal != null) {
	      apiProxyLocal.stop();
	    }
	    ApiProxy.setDelegate(originalDelegate);
	    ApiProxy.setEnvironmentForCurrentThread(originalEnvironment);
	}
	
    static boolean isServer() {
        return System.getProperty(ENVIRONMENT_KEY) != null;
    }
    
    static boolean isProduction() {
        return PRODUCTION.equals(System.getProperty(ENVIRONMENT_KEY));
    }
}
