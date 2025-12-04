package com.julioceno.qrcodeservice.adapters.in.filter;

import com.julioceno.qrcodeservice.infrastructure.logger.CorrelationId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CorrelationIdFilterTest {

    @InjectMocks
    private CorrelationIdFilter filter;

    @AfterEach
    void afterEach() {
        MDC.clear();
    }

    @Test
    void shouldUseHeaderCorrelationId() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        String provided = "test-correlation-id";
        request.addHeader(CorrelationId.HEADER_NAME, provided);

        AtomicReference<String> captured = new AtomicReference<>();

        FilterChain chain = new FilterChain() {

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse){
                captured.set(MDC.get(CorrelationId.HEADER_NAME));
            }
        };

        filter.doFilter(request, response, chain);

        assertEquals(provided, captured.get(), "should contain correlation-id header");
        assertNull(response.getHeader(CorrelationId.HEADER_NAME));
        assertNull(MDC.get(CorrelationId.HEADER_NAME));
    }

    @Test
    void shouldGenerateCorrelationId_When_Missing() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        AtomicReference<String> captured = new AtomicReference<>();

        FilterChain chain = new FilterChain() {
            @Override
            public void doFilter(ServletRequest req, ServletResponse res) {
                captured.set(MDC.get(CorrelationId.HEADER_NAME));
            }
        };

        filter.doFilter(request, response, chain);

        String generated = captured.get();
        assertNotNull(generated, "Should generate an correlation id when the header does not exists");
        assertEquals(generated, response.getHeader(CorrelationId.HEADER_NAME));
        assertNull(MDC.get(CorrelationId.HEADER_NAME));
    }
}