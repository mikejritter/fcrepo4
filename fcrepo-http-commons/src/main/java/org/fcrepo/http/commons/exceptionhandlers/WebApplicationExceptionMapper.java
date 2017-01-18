/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.http.commons.exceptionhandlers;

import static javax.ws.rs.core.Response.fromResponse;
import static org.slf4j.LoggerFactory.getLogger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;

/**
 * Handle Jersey WebApplicationException
 *
 * @author lsitu
 */
@Provider
public class WebApplicationExceptionMapper implements
        ExceptionMapper<WebApplicationException>, ExceptionDebugLogging {

    private static final Logger LOGGER =
        getLogger(WebApplicationExceptionMapper.class);

    @Override
    public Response toResponse(final WebApplicationException e) {
        LOGGER.warn(
                "WebApplicationException intercepted by WebApplicationExceptionMapper: {}\n", e.getMessage());
        debugException(this, e, LOGGER);
        final String msg = null == e.getCause() ? e.getMessage() : e.getCause().getMessage();
        return fromResponse(e.getResponse()).entity(msg).build();
    }
}
