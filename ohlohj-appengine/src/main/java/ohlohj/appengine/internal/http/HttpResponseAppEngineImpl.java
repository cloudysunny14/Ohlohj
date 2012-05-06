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
package ohlohj.appengine.internal.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPResponse;

import ohlohj.OhlohException;
import ohlohj.internal.http.HttpResponse;
import ohlohj.internal.http.HttpResponseCode;

/**
 * @author cloudysunny14
 *
 */
public class HttpResponseAppEngineImpl extends HttpResponse implements HttpResponseCode {
	
	private Future<HTTPResponse> future;
    private boolean responseGot;
    private Map<String, String> headers;
	
	HttpResponseAppEngineImpl(Future<HTTPResponse> futureResponse) {
        this.future = futureResponse;
    }
	
	/**
     * {@inheritDoc}
     */
    @Override
    public int getStatusCode() {
        ensureResponseEvaluated();
        return statusCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponseHeader(String name) {
        ensureResponseEvaluated();
        return headers.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<String>> getResponseHeaderFields() {
        ensureResponseEvaluated();
        Map<String, List<String>> ret = new TreeMap<String, List<String>>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            ret.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream asStream() {
        ensureResponseEvaluated();
        return super.asStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String asString() {
        ensureResponseEvaluated();
        return super.asString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Reader asReader() {
        ensureResponseEvaluated();
        return super.asReader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() throws IOException {
        if (!future.isDone() && !future.isCancelled()) {
            future.cancel(true);
        }
    }

    private Throwable th = null;

    private void ensureResponseEvaluated() {
        if (th != null) {
            throw new IllegalStateException(th);
        }
        if (responseGot) {
            return;
        }
        responseGot = true;
        if (future.isCancelled()) {
        	throw new IllegalStateException("HttpResponse already disconnected.");
        }
        try {
            HTTPResponse res = future.get();
            statusCode = res.getResponseCode();
            headers = new HashMap<String, String>();
            for (HTTPHeader header : res.getHeaders()) {
                headers.put(header.getName(), header.getValue());
            }
            byte[] content = res.getContent();
            is = new ByteArrayInputStream(content);
            if ("gzip".equals(headers.get("Content-Encoding"))) {
                // the response is gzipped
                try {
                    is = new GZIPInputStream(is);
                } catch (IOException ioe) {
                	throw new RuntimeException(ioe.getMessage(), ioe);
                }
            }
            responseAsString = inputStreamToString(is);
            if (statusCode < OK || (statusCode != FOUND && MULTIPLE_CHOICES <= statusCode)) {
                if (statusCode == ENHANCE_YOUR_CLAIM ||
                        statusCode == BAD_REQUEST ||
                        statusCode < INTERNAL_SERVER_ERROR) {
                	th = new OhlohException(responseAsString);
                	throw new RuntimeException(th);
                }
            }
        } catch (ExecutionException e) {
            th = e.getCause();
        } catch (InterruptedException e) {
            th = e.getCause();
        }
        if (th != null) {
        	throw new RuntimeException(th);
        }
    }

    private String inputStreamToString(InputStream is) {
        if (responseAsString == null) {
            StringBuffer buf = new StringBuffer();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    buf.append(line);
                }
            } catch (IOException e) {
                return null;
            }
            responseAsString = buf.toString();
        }
        return responseAsString;
    }

    @Override
    public String toString() {
        return "HttpResponseAppEngineImpl{" +
                "future=" + future +
                ", responseGot=" + responseGot +
                ", headers=" + headers +
                '}';
    }
}
