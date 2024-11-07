/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package io.openliberty.telemetry.user.feature;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.ws.rs.ext.Provider;

import io.openliberty.microprofile.telemetry.spi.OpenTelemetryAccessor;
import io.openliberty.microprofile.telemetry.spi.OpenTelemetryInfo;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@Provider
public class UserFeatureServletFilter implements Filter {

    private static final String SPAN_NAME = "UserFeatureSpanName";
    private static final AttributeKey<String> SPAN_ATTRIBUTE_KEY = AttributeKey.stringKey("FromUserFeature");
    private static final String SPAN_ATTRIBUTE_VALUE = "True";

    @Override
    public void destroy() {
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("UserFeatureTest: Entering user feature  doFilter");

        //Test we can use an OpenTelemetry we got via the SPI by using it to create a span.
        OpenTelemetryInfo openTelemetryInfo = OpenTelemetryAccessor.getOpenTelemetryInfo();
        //It is not strictly necessary to call isEnabled, as if openTelemetry is not enabled this
        //code will no-op. However this is beneficial for performance.
        if (openTelemetryInfo.isEnabled()) {
            Tracer tracer = openTelemetryInfo.getOpenTelemetry().getTracer("user-feature-test", "1.0.0");
            Span span = tracer.spanBuilder(SPAN_NAME)
                            .setAttribute(SPAN_ATTRIBUTE_KEY, SPAN_ATTRIBUTE_VALUE)
                            .startSpan();
            try (Scope scope = span.makeCurrent()) {
                chain.doFilter(request, response);
            } finally {
                span.end();
            }
        } else {
            System.out.println("Open Telemetry was not enabled");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        //do nothing
    }

}