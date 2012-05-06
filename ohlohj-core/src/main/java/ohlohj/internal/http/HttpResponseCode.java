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

/**
 * 
 * @author cloudysunny14
 *
 */
public interface HttpResponseCode {
    int OK = 200;// OK: Success!
    int MULTIPLE_CHOICES = 300;//Multiple Choices
    int MOVED_PERMANENTLY = 301;//Moved permanently
    int FOUND = 302;//Found
    int NOT_MODIFIED = 304;// Not Modified
    int BAD_REQUEST = 400;// Bad Request
    int UNAUTHORIZED = 401;// Not Authorized
    int FORBIDDEN = 403;// Forbidden
    int NOT_FOUND = 404;// Not Found
    int NOT_ACCEPTABLE = 406;// Not Acceptable
    int TOO_LONG = 413;// Not Acceptable
    int ENHANCE_YOUR_CLAIM = 420;// Enhance Your Calm
    int INTERNAL_SERVER_ERROR = 500;// Internal Server Error
    int BAD_GATEWAY = 502;// Bad Gateway
    int SERVICE_UNAVAILABLE = 503;// Service Unavailable
}
