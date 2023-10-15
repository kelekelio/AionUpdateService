package com.aionpowerbook.aionupdateservice.config.db;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServerRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            String parameter = request.getParameter("server");

            return parameter != null ? parameter : "0";
        }
        return "0";
    }
}
