package com.chagen.exoplanet.summary.api.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter to handle the tracing id if present or create a new one
 *
 * @author Chad Hagen
 */
@Component
@Order(0)
public class TracingIdFilter extends OncePerRequestFilter {

	public static final String TRACING_ID_HEADER = "tracing-id";
	public static final String TRACING_ID_MDC_KEY = "tracing_id";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			String tracingId = request.getHeader(TRACING_ID_HEADER);
			if (StringUtils.isEmpty(tracingId)) {
				tracingId = UUID.randomUUID().toString();
			}
			MDC.put(TRACING_ID_MDC_KEY, tracingId);
			if (!StringUtils.isEmpty(TRACING_ID_HEADER)) {
				response.addHeader(TRACING_ID_HEADER, tracingId);
			}
			chain.doFilter(request, response);
		} finally {
			MDC.remove(TRACING_ID_MDC_KEY);
		}
	}
}
