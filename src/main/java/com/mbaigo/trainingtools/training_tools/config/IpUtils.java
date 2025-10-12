package com.mbaigo.trainingtools.training_tools.config;

import jakarta.servlet.http.HttpServletRequest;

public final class IpUtils {
    private IpUtils() {}
    public static String getClientIp(HttpServletRequest request) {
        // Vérifie les headers HTTP standards souvent ajoutés par des proxys ou load balancers
        String[] headerCandidates = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR"
        };

        for (String header : headerCandidates) {
            String ipList = request.getHeader(header);
            if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
                // si plusieurs IP (séparées par des virgules), on prend la première
                return ipList.split(",")[0];
            }
        }

        return request.getRemoteAddr();
    }
}
