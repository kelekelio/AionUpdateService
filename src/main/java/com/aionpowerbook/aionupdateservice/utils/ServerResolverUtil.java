package com.aionpowerbook.aionupdateservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class ServerResolverUtil {
    public static String resolveServer() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            String parameter = request.getParameter("server");

            return parameter != null ? parameter : "0";
        }
        return "0";
    }

    public static Long resolveServerId() {
        return Long.parseLong(resolveServer());
    }
}
