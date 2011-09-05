/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.nio.protocol;

import java.io.IOException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.protocol.HttpContext;

/**
 * HTTP request execution handler can be used by client-side protocol handlers
 * to trigger the submission of a new HTTP request and the processing of an
 * HTTP response.
 *
 * @since 4.0
 *
 * @deprecated use {@link HttpAsyncClientProtocolHandler} and {@link HttpAsyncRequestExecutor}
 */
@Deprecated
public interface HttpRequestExecutionHandler {

    /**
     * Triggered when a new connection has been established and the
     * HTTP context needs to be initialized.
     *
     * <p>The attachment object is the same object which was passed
     * to the connecting I/O reactor when the connection request was
     * made. The attachment may optionally contain some state information
     * required in order to correctly initialize the HTTP context.
     *
     * @see ConnectingIOReactor#connect
     *
     * @param context the actual HTTP context
     * @param attachment the object passed to the connecting I/O reactor
     *   upon the request for a new connection.
     */
    void initalizeContext(HttpContext context, Object attachment);

    /**
     * Triggered when the underlying connection is ready to send a new
     * HTTP request to the target host. This method may return
     * <code>null</null> if the client is not yet ready to send a
     * request. In this case the connection will remain open and
     * can be activated at a later point.
     *
     * @param context the actual HTTP context
     * @return an HTTP request to be sent or <code>null</null> if no
     *   request needs to be sent
     */
    HttpRequest submitRequest(HttpContext context);

    /**
     * Triggered when an HTTP response is ready to be processed.
     *
     * @param response the HTTP response to be processed
     * @param context the actual HTTP context
     */
    void handleResponse(HttpResponse response, HttpContext context)
        throws IOException;

    /**
     * Triggered when the connection is terminated. This event can be used
     * to release objects stored in the context or perform some other kind
     * of cleanup.
     *
     * @param context the actual HTTP context
     */
    void finalizeContext(HttpContext context);

}
