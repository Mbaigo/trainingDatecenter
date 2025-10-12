package com.mbaigo.trainingtools.training_tools.security;

import jakarta.servlet.http.HttpServletRequest;

public final class UserAgentUtils {
    private UserAgentUtils() {
        // Private constructor to prevent instantiation
    }

    public static String getDeviceInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return "Unknown device";
        }

        // Détection simple (pour du logging)
        if (userAgent.contains("Windows")) return "Windows PC";
        if (userAgent.contains("Mac")) return "MacOS";
        if (userAgent.contains("X11")) return "Unix";
        if (userAgent.contains("Android")) return "Android device";
        if (userAgent.contains("iPhone")) return "iPhone";
        if (userAgent.contains("iPad")) return "iPad";
        if (userAgent.contains("Linux")) return "Linux machine";

        return "Other (" + userAgent + ")";
    }
}
