package com.chagen.exoplanet.summary.api.interceptor;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.chagen.exoplanet.summary.api.filter.TracingIdFilter;

/**
 * Add tracing id to all outgoing rest template calls
 *
 * @author Chad Hagen
 */
public class RestTemplateTracingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().add(TracingIdFilter.TRACING_ID_HEADER, MDC.get(TracingIdFilter.TRACING_ID_MDC_KEY));
		return execution.execute(request, body);
	}
}
