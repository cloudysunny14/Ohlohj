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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author cloudysunny14
 *
 */
public abstract class HttpResponse {

    protected int statusCode;
    protected String responseAsString = null;
    protected InputStream is;
    private boolean streamConsumed = false;
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public abstract String getResponseHeader(String name);

    public abstract Map<String, List<String>> getResponseHeaderFields();

    /**
     * Returns the response stream.<br>
     * This method cannot be called after calling asString() or asDcoument()<br>
     * It is suggested to call disconnect() after consuming the stream.
     * <p/>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body stream
     * @see #disconnect()
     */
    public InputStream asStream() {
        if (streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }

    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     *
     * @return response body
     */
    public String asString() {
        if (null == responseAsString) {
            BufferedReader br = null;
            InputStream stream = null;
            try {
                stream = asStream();
                if (null == stream) {
                    return null;
                }
                br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                StringBuffer buf = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    buf.append(line).append("\n");
                }
                this.responseAsString = buf.toString();
                stream.close();
                streamConsumed = true;
            } catch (IOException ignore) {
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ignore) {
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ignore) {
                    }
                }
                disconnectForcibly();
            }
        }
        return responseAsString;
    }

    public Reader asReader() {
        try {
            return new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException uee) {
            return new InputStreamReader(is);
        }
    }

    private void disconnectForcibly() {
        try {
            disconnect();
        } catch (Exception ignore) {
        }
    }

    public abstract void disconnect() throws IOException;

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", responseAsString='" + responseAsString + '\'' +
                ", is=" + is +
                ", streamConsumed=" + streamConsumed +
                '}';
    }

}
