package com.ramesh.resource.ratemanagementservice.filter;

import org.apache.log4j.MDC;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author Ramesh.Yaleru on 5/29/2020
 */
@Component
@Order(1)
public class LoggingFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    private static String TZ_NAME_KEY = "timezoneOffset";
    private static String LOGIN_ID_KEY = "loginId";
    private static String TIMEZONE_KEY = "timezone";
    private static String REQUEST_ID_KEY = "requestId";
    private static String CLIENTIPADDRESS_KEY = "clientIPAddress";
    private static String HOSTNAME_KEY = "hostName";
    private static String LOCALE_LANG_KEY = "localeLang";
    private static String LOCALE_KEY = "locale";
    private static String HTTP_SESSIONID_KEY = "sessionId";
    private FilterConfig filterConfig;
    public static final String REQUEST_ID = "RequestId";

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * filter which will add additional meta information in every request.
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        TimeZone timeZone = TimeZone.getDefault();
        ThreadContext.put(TIMEZONE_KEY, timeZone.getDisplayName());
        populateAccessingUserInfo(request, response);
        filterChain.doFilter(servletRequest, servletResponse);
        // ThreadContext.clearAll();
    }

    /*
        Method to populateApiCommand necessary parameters to be displayed in logs.
     */
    private void populateAccessingUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        if (ip == null) {
            ip = request.getRemoteAddr();
        }


        String loginId = ThreadContext.get(LOGIN_ID_KEY);
        String requestId = loginId + System.currentTimeMillis();
        ThreadContext.put(REQUEST_ID_KEY, requestId);
        ThreadContext.put(CLIENTIPADDRESS_KEY, ip);
        ThreadContext.put(HOSTNAME_KEY, request.getServerName());
        ThreadContext.put(LOCALE_LANG_KEY, request.getLocale().getLanguage());
        ThreadContext.put(LOCALE_KEY, request.getLocale().getDisplayName());
        ThreadContext.put(HTTP_SESSIONID_KEY, request.getSession().getId());
        ThreadContext.put(REQUEST_ID, UUID.randomUUID().toString());
        MDC.put(REQUEST_ID, ThreadContext.get(REQUEST_ID));
        request.setAttribute("UserSessionId", ThreadContext.get(HTTP_SESSIONID_KEY));
        response.addHeader(REQUEST_ID_KEY, requestId);

    }

    public void destroy() {
        logger.info("######### filter - detroy() method is called #############");
    }
}
